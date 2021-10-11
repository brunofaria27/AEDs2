#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MAX_FIELD_SIZE 100

typedef struct {
    char nome[MAX_FIELD_SIZE];
    char formato[MAX_FIELD_SIZE];
    char duracao[MAX_FIELD_SIZE];
    char pais[MAX_FIELD_SIZE];
    char idioma[MAX_FIELD_SIZE];
    char emissora[MAX_FIELD_SIZE];
    char transmissao[MAX_FIELD_SIZE];
    int num_temporadas;
    int num_episodios;
} Serie;

char *remove_line_break(char *line) {
    while (*line != '\r' && *line != '\n') line++;
    *line = '\0';
    return line;
}

char *freadline(char *line, int max_size, FILE *file) {
    return remove_line_break(fgets(line, max_size, file));
}

char *readline(char *line, int max_size) {
    return freadline(line, max_size, stdin);
}

void imprimir(Serie *serie) {
    printf("%s %s %s %s %s %s %s %d %d\n",
        serie->nome,
        serie->formato,
        serie->duracao,
        serie->pais,
        serie->idioma,
        serie->emissora,
        serie->transmissao,
        serie->num_temporadas,
        serie->num_episodios
    );
}

// Retorna o tamanho em bytes de um arquivo.
long tam_arquivo(FILE *file) {
    fseek(file, 0L, SEEK_END);
    long size = ftell(file);
    rewind(file);
    return size;
}

// Retorna todo o conteúdo do arquivo numa string.
char *ler_html(char filename[]) {
    FILE *file = fopen(filename, "r");

    if (!file) {
        fprintf(stderr, "Falha ao abrir arquivo %s\n", filename);
        exit(1);
    }

    long tam = tam_arquivo(file);
    char *html = (char *) malloc(sizeof(char) * (tam + 1));
    fread(html, 1, tam, file);
    fclose(file);
    html[tam] = '\0';

    return html;
}

char *extrair_texto(char *html, char *texto) {
    char *start = texto;
    int contagem = 0;
    
    while (*html != '\0') {
        if (*html == '<') {
            if (
                (*(html + 1) == 'p') ||
                (*(html + 1) == 'b' && *(html + 2) == 'r') ||
                (*(html + 1) == '/' && *(html + 2) == 'h' && *(html + 3) == '1') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'h') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'd')
            ) break;
            else contagem++;
        }
        else if (*html == '>') contagem--;
        else if (contagem == 0 && *html != '"') {
            if (*html == '&') html = strchr(html, ';');
            else if (*html != '\r' && *html != '\n') *texto++ = *html;
        }
        html++;
    }
    *texto = '\0';

    return *start == ' ' ? start + 1 : start;
}

void ler_serie(Serie *serie, char *html) {
    char texto[MAX_FIELD_SIZE];

    char *ptr = strstr(html, "<h1");
    extrair_texto(ptr, texto);

    char *parenteses_ptr = strchr(texto, '(');
    if (parenteses_ptr != NULL) *(parenteses_ptr - 1) = '\0';

    strcpy(serie->nome, texto);

    ptr = strstr(ptr, "<table class=\"infobox_v2\"");

    ptr = strstr(ptr, "Formato");
    ptr = strstr(ptr, "<td");
    strcpy(serie->formato, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Duração");
    ptr = strstr(ptr, "<td");
    strcpy(serie->duracao, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "País de origem");
    ptr = strstr(ptr, "<td");
    strcpy(serie->pais, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Idioma original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->idioma, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Emissora de televisão original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->emissora, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Transmissão original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->transmissao, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "N.º de temporadas");
    ptr = strstr(ptr, "<td");
    sscanf(extrair_texto(ptr, texto), "%d", &serie->num_temporadas);

    ptr = strstr(ptr, "N.º de episódios");
    ptr = strstr(ptr, "<td");
    sscanf(extrair_texto(ptr, texto), "%d", &serie->num_episodios);
}

Serie clonar(Serie *serie) {
    return *serie;
}

#define MAX_LINE_SIZE 250
#define PREFIXO "/tmp/series/"
// #define PREFIXO "../entrada e saida/tp02/series/"

int isFim(char line[]) {
    return line[0] == 'F' && line[1] == 'I' && line[2] == 'M';
}


/* MAIN */
int comparacoes = 0;

Serie lerDados(char s[]){
    Serie series;

    char *html = ler_html(s);
    ler_serie(&series, html);

    return series;
}

void sortBySelectionRecursive(Serie serie[], int n, int i) {
    if (i >= n - 1) {
        return;
    }

    int menor = i;

    for (int j = (i + 1); j < n; j++) {
        if (strcmp(serie[menor].pais, serie[j].pais) > 0 || strcmp(serie[menor].pais, serie[j].pais) == 0 && strcmp(serie[menor].nome, serie[j].nome) > 0) {
            menor = j;
        }
    }

    Serie temp[1];
    temp[0] = serie[i];
    serie[i] = serie[menor];
    serie[menor] = temp[0];

    sortBySelectionRecursive(serie, n, i + 1);
}

int main() {
    Serie *serie = (Serie*) malloc(61 * sizeof(Serie));
    clock_t inicio, fim;
    size_t tam_prefixo = strlen(PREFIXO);
    char line[MAX_LINE_SIZE];

    strcpy(line, PREFIXO);
    readline(line + tam_prefixo, MAX_LINE_SIZE);
    int numEntrada = 0;

    while (!isFim(line + tam_prefixo)) {
        *serie = lerDados(line);
        readline(line + tam_prefixo, MAX_LINE_SIZE);
        numEntrada++;
    }

    inicio = clock();
    sortBySelectionRecursive(serie, numEntrada, 0);
    fim = clock();

    for (int i = 0; i < 100; i++) {
        imprimir(&serie[i]);
    }

    double tempo =  ((fim - inicio) / (double)CLOCKS_PER_SEC);

    FILE *arq;
    arq = fopen("matricula_selecaoRecursiva.txt","w");

    fprintf(arq, "Matricula : 742238 \t Tempo de execução : %fs\t Numero de Comparações : %i ", tempo, comparacoes);
    fclose(arq); 

    return EXIT_SUCCESS;
}