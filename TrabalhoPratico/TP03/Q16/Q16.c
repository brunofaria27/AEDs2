#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MAX_FIELD_SIZE 100
int comparacoes = 0;

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

Serie novaStructSerie() {
    Serie series;

    strcpy(series.duracao, "");
    strcpy(series.emissora, "");
    strcpy(series.nome, "");
    strcpy(series.formato, "");
    strcpy(series.pais, "");
    strcpy(series.idioma, "");
    strcpy(series.transmissao, "");
    series.num_episodios = 0;
    series.num_temporadas = 0;

    return series;
}

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

Serie* clonar(Serie s){
    Serie *clone = (Serie*) malloc(sizeof(Serie));

    strcpy(clone->duracao, s.duracao);
    strcpy(clone->emissora, s.emissora);
    strcpy(clone->nome, s.nome);
    strcpy(clone->formato, s.formato);
    strcpy(clone->pais, s.pais);
    strcpy(clone->idioma, s.idioma);
    strcpy(clone->transmissao, s.transmissao);

    clone->num_episodios = s.num_episodios;
    clone->num_temporadas = s.num_temporadas;

    return clone;
}

#define MAX_LINE_SIZE 250
#define PREFIXO "/tmp/series/"
// #define PREFIXO "../entrada e saida/tp02/series/"

int isFim(char line[]) {
    return line[0] == 'F' && line[1] == 'I' && line[2] == 'M';
}

/* CELULA INICIO */
typedef struct CelulaDupla {
    Serie *elemento;
    struct CelulaDupla *prox, *ant;
} CelulaDupla;

CelulaDupla* novaCelulaDupla(Serie series) {
    CelulaDupla *nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));

    nova->elemento = clonar(series);
    nova->ant = nova->prox = NULL;

    return nova;
}
/* CELULA FIM */

/* LISTA DUPLA INICIO */
typedef struct ListaDupla {
    struct CelulaDupla *primeiro, *ultimo;
    int size;
} ListaDupla;

ListaDupla novaListaDupla() {
    Serie series = novaStructSerie();
    ListaDupla temp;
    temp.primeiro = temp.ultimo = novaCelulaDupla(series);
    temp.size = 0;
    return temp;
}

void inserirFim(ListaDupla *lista, Serie series) {
    lista->ultimo->prox = novaCelulaDupla(series);
    lista->ultimo->prox->ant = lista->ultimo;
    lista->ultimo = lista->ultimo->prox;
    lista->size++;
}

int tamanho(ListaDupla *lista) {
    return lista->size;
}

Serie* getSeriePos(ListaDupla *lista, int pos) {
    CelulaDupla *temp = lista->primeiro->prox;
    for (int i = 0; (i < pos && temp->prox != NULL); temp = temp->prox, i++);
    return temp->elemento; 
}

CelulaDupla* getCelulaPos(ListaDupla *lista, int pos){
    CelulaDupla *i = lista->primeiro->prox;
    for(int j = 0; j < pos ; j++, i = i->prox);
    return i;
}

void swap(CelulaDupla *i, CelulaDupla *j){
    Serie *tmp = i->elemento;
    i->elemento = j->elemento;
    j->elemento = tmp;
}   

void quicksortRec(ListaDupla *lista, int esq, int dir) {
    int i = esq, j = dir;
    Serie *pivo = getSeriePos(lista, (esq + dir) / 2);

    while (i <= j) {

        while (strcmp(getSeriePos(lista, i)->pais , pivo->pais) < 0 || (strcmp(getSeriePos(lista, i)->pais , pivo->pais) == 0 && strcmp(getSeriePos(lista, i)->nome , pivo->pais) < 0)){
            ++i;
            comparacoes+=3;
        }

        while (strcmp(getSeriePos(lista, j)->pais , pivo->pais) < 0 || (strcmp(getSeriePos(lista, j)->pais , pivo->pais) == 0 && strcmp(getSeriePos(lista, j)->nome , pivo->pais) < 0)){ 
            --j;
            comparacoes+=3;
        }

        if (i <= j) {
            swap(getCelulaPos(lista, i),getCelulaPos(lista, j));
            ++i;
            --j;
        }
    }

    if (esq < j) {
        quicksortRec(lista, esq, j);
    }
    if (i < dir) {
        quicksortRec(lista, i, dir);
    }
}

void quicksort(ListaDupla *lista) {
    int n = tamanho(lista);
    quicksortRec(lista, 0, tamanho(lista) - 1);
}

void mostrar(ListaDupla *lista) {
    CelulaDupla *i;

    for (i = lista->primeiro->prox; i != NULL; i = i->prox) {
        printf("%s %s %s %s %s %s %s %i %i\n", i->elemento->nome,
                                               i->elemento->formato,
                                               i->elemento->duracao,
                                               i->elemento->pais,
                                               i->elemento->idioma,
                                               i->elemento->emissora,
                                               i->elemento->transmissao,
                                               i->elemento->num_temporadas,
                                               i->elemento->num_episodios);
    }

}
/* LISTA DUPLA FIM */

Serie lerDados(char s[]) {
    Serie series;

    char *html = ler_html(s);
    ler_serie(&series, html);

    return series;
}

int main() {
    Serie serie;
    clock_t inicio, fim;
    size_t tam_prefixo = strlen(PREFIXO);
    char line[MAX_LINE_SIZE];

    strcpy(line, PREFIXO);
    readline(line + tam_prefixo, MAX_LINE_SIZE);
    int numEntrada = 0;
    ListaDupla lista = novaListaDupla();

    while (!isFim(line + tam_prefixo)) {
        char *html = ler_html(line);
        ler_serie(&serie, html);
        inserirFim(&lista, serie);
        free(html);
        readline(line + tam_prefixo, MAX_LINE_SIZE);
        numEntrada++;
    }

    inicio = clock();
    quicksort(&lista);
    fim = clock();

    mostrar(&lista);

    double tempo =  ((fim - inicio) / (double)CLOCKS_PER_SEC) / 1000.0;

    FILE *arq;
    arq = fopen("matricula_quicksort2.txt","w");

    fprintf(arq, "Matricula : 742238 \t Tempo de execução : %fs\t Numero de Comparações : %i ", tempo, comparacoes);
    fclose(arq); 
    
    return EXIT_SUCCESS;
}