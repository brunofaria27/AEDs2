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

void imprimir(Serie serie) {
    printf("%s %s %s %s %s %s %s %d %d\n",
        serie.nome,
        serie.formato,
        serie.duracao,
        serie.pais,
        serie.idioma,
        serie.emissora,
        serie.transmissao,
        serie.num_temporadas,
        serie.num_episodios
    );
}

void imprimir2(Serie *serie) {
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

Serie series[61];
int tam = 0;
int comparacoes = 0;

void inserirFim(Serie serie) {
    series[tam] = clonar(&serie);
    tam++;
}

int getMaior(int n) {
    int maior = series[0].num_temporadas;

    for (int i = 0; i < n; i++) {
        if(maior < series[i].num_temporadas){
            maior = series[i].num_temporadas;
        }
    }
    
    return maior;
}

void countingSort(int n) {
    int tamCount = (getMaior(n) + 1);
    int count[tamCount];
    Serie ordenado[n];

    for (int i = 0; i < tamCount; i++) {
        count[i] = 0;
    }

    for (int i = 0; i < n; i++) {
        count[series[i].num_temporadas]++;
    }

    for(int i = 1; i < tamCount; i++) {
        count[i] += count[i - 1];
    }

    
    for(int i = n - 1; i >= 0; i--) { 
        ordenado[count[series[i].num_temporadas] - 1] = series[i];
        count[series[i].num_temporadas]--;
        comparacoes++;
    }

    for(int i = 1; i < n; i++) {  
        Serie tmp = ordenado[i];
        int j = i - 1;
        
        while((j >= 0) && ordenado[j].num_temporadas == tmp.num_temporadas && strcmp(ordenado[j].nome, tmp.nome) > 0) {
            ordenado[j + 1] = ordenado[j];
            j--;
            comparacoes += 2;
        }

        ordenado[j + 1] = tmp;
    }

    for(int i = 0 ; i < n; i++) {
        series[i] = ordenado[i];
    }

}

int main() {
    Serie serie;
    clock_t inicio, fim;
    size_t tam_prefixo = strlen(PREFIXO);
    char line[MAX_LINE_SIZE];

    strcpy(line, PREFIXO);
    readline(line + tam_prefixo, MAX_LINE_SIZE);
    int numEntrada = 0;

    while (!isFim(line + tam_prefixo)) {
        char *html = ler_html(line);
        ler_serie(&serie, html);
        inserirFim(serie);
        free(html);
        readline(line + tam_prefixo, MAX_LINE_SIZE);
        numEntrada++;
    }

    inicio = clock();
    countingSort(numEntrada);
    fim = clock();

    for(int i = 0; i < 61; i++) {
        imprimir(series[i]);
    }

    double tempo =  ((fim - inicio) / (double)CLOCKS_PER_SEC);

    FILE *arq;
    arq = fopen("matricula_countingsort.txt","w");

    fprintf(arq, "Matricula : 742238 \t Tempo de execução : %fs\t Numero de Comparações : %i ", tempo, comparacoes);
    fclose(arq);

    return EXIT_SUCCESS;
}