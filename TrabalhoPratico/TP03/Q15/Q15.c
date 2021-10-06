#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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

/* PARTE LISTA */
Serie array[100];
int n = 0;

void inserirInicio(Serie serie) {
    int i;

    if(n >= 100) {
        printf("Erro ao inserir!\n");
        exit(1);
    }

    for(i = n; i > 0; i--) {
        array[i] = array[i - 1];
    }

    array[0] = serie;
    n++;
}

void inserir(int pos, Serie serie) {
    if(n >= 100 || pos < 0 || pos > n) {
        printf("Erro ao inserir!\n");
        exit(1);
    }
    
    for(int i = n; i > pos; i--) {
        array[i] = array[i - 1];
    }
    
    array[pos] = serie;
    n++;
}

void inserirFim(Serie serie) {
    if(n >= 100) {
        printf("Erro ao inserir!\n");
        exit(1);
    }

    array[n] = clonar(&serie);
    n++;
}

Serie removerInicio() {
    Serie resp;

    if(n == 0) {
        printf("Erro ao remover!\n");
        exit(1);
    }
    
    resp = array[0];
    n--;

    for(int i = 0; i < n; i++) {
        array[i] = array[i + 1];
    }

    return resp;
}

Serie remover(int pos) {
    if(n == 0 || pos < 0 || pos >= n) {
        printf("Erro ao remover!\n");
        exit(1);
    }
    
    Serie resp = array[pos];
    n--;

    for(int i = pos; i < n; i++) {
        array[i] = array[i + 1];
    }

    return resp;
}

Serie removerFim() {
    if(n == 0) {
        printf("\nErro ao remover!");
        exit(1);
    }

    return array[--n];
}

void mostrar() {
    for(int i = 0; i < n; i++) {
        printf("%s %s %s %s %s %s %s %i %i\n", array[i].nome,
                                               array[i].formato,
                                               array[i].duracao,
                                               array[i].pais,
                                               array[i].idioma,
                                               array[i].emissora,
                                               array[i].transmissao,
                                               array[i].num_temporadas,
                                               array[i].num_episodios);
    }
}

/* END PARTE LISTA */

Serie removidos[20]; // series removidos
int r = 0; // contador removidos

Serie lerDados(char s[]){
    Serie series;

    char *html = ler_html(s);
    ler_serie(&series, html);

    return series;
}

void printaRemovidos() {
    for(int i = 0; i < r; i++){
        printf("(R) %s\n", removidos[i].nome);
    }
}

void trataEntradas(char *s) {
    char *aux[3];
    char nomeArq[100] = {"/tmp/series/"};

    if(s[0] == 'I' && s[1] == 'I') {
        aux[0] = strtok(s, " "); 
        aux[1] = strtok(NULL, " ");
        strcat(nomeArq, aux[1]);
        inserirInicio(lerDados(nomeArq));
    } else if(s[0] == 'I' && s[1] == 'F') {
       aux[0] = strtok(s," "); 
       aux[1] = strtok(NULL," "); 
       strcat(nomeArq, aux[1]);
       inserirFim(lerDados(nomeArq));
    } else if(s[0] == 'I' && s[1] == '*') {
        aux[0] = strtok(s," "); 
        aux[1] = strtok(NULL," ");
        aux[2] = strtok(NULL," ");
        strcat(nomeArq, aux[2]);
        inserir(atoi(aux[1]), lerDados(nomeArq));
    } else if(s[0] == 'R' && s[1] == '*') {
        aux[0] = strtok(s," "); 
        aux[1] = strtok(NULL," ");
        removidos[r] = remover(atoi(aux[1]));
        r++;
    } else if(s[0] == 'R' && s[1] == 'I') {
        removidos[r] =  removerInicio();
        r++;
    } else if(s[0] == 'R' && s[1] == 'F') {
        removidos[r] = removerFim();
        r++;
    }
}

int main() {
    Serie serie;
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

    int n;
    scanf("%i",&n);
    char comandos[n][MAX_LINE_SIZE];

    for(int i=0; i<n; i++){
        scanf(" %[^\n]s", comandos[i]); 
        trataEntradas(comandos[i]);
    }

    printaRemovidos();
    mostrar();

    return EXIT_SUCCESS;
}