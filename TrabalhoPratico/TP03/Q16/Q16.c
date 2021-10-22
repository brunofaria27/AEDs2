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

void cloneEntrePonteiros(Serie *tmp, Serie *serie2) {
    strcpy(tmp->nome, serie2->nome);
    strcpy(tmp->formato, serie2->formato);
    strcpy(tmp->duracao, serie2->duracao);
    strcpy(tmp->pais, serie2->pais);
    strcpy(tmp->idioma, serie2->idioma);
    strcpy(tmp->emissora, serie2->emissora);
    strcpy(tmp->transmissao, serie2->transmissao);
    tmp->num_temporadas = serie2->num_temporadas;
    tmp->num_episodios = serie2->num_episodios;
}

#define MAX_LINE_SIZE 250
#define PREFIXO "/tmp/series/"
// #define PREFIXO "../entrada e saida/tp02/series/"

int isFim(char line[]) {
    return line[0] == 'F' && line[1] == 'I' && line[2] == 'M';
}

/* CELULA INICIO */
typedef struct CelulaDupla {
    Serie elemento;
    struct CelulaDupla *prox, *ant;
} CelulaDupla;

CelulaDupla* new_celula_dupla(Serie *elemento) {
    CelulaDupla *temp = (CelulaDupla*)malloc(sizeof(CelulaDupla));

    temp->elemento = *elemento;
    temp->prox = NULL;
    temp->ant = NULL;

    return temp;
}
/* CELULA FIM */

/* LISTA DUPLA INICIO */
typedef struct ListaDupla {
    struct CelulaDupla *primeiro, *ultimo;
    int size;
} ListaDupla;

ListaDupla* new_lista_dupla(ListaDupla *temp) {
    Serie series;
    temp->primeiro = new_celula_dupla(&series);
    temp->ultimo = temp->primeiro;
    temp->size = 0;

    return temp;
}

int tamanhoListaDupla(ListaDupla *temp) {
    return temp->size;
}

void inserirFimDupla(ListaDupla *lista, Serie *elemento) {
    Serie series;
    series = clonar(elemento);
    lista->ultimo->prox = new_celula_dupla(&series);
    lista->ultimo->prox->ant = lista->ultimo;
    lista->ultimo = lista->ultimo->prox;
    lista->size++;
}

void mostrar(ListaDupla *lista) {
    CelulaDupla *i;

    for (i = lista->primeiro->prox; i != NULL; i = i->prox) {
        printf("%s %s %s %s %s %s %s %i %i\n", i->elemento.nome,
                                               i->elemento.formato,
                                               i->elemento.duracao,
                                               i->elemento.pais,
                                               i->elemento.idioma,
                                               i->elemento.emissora,
                                               i->elemento.transmissao,
                                               i->elemento.num_temporadas,
                                               i->elemento.num_episodios);
    }

}
/* LISTA DUPLA FIM */

int comparacoes = 0;

Serie lerDados(char s[]) {
    Serie series;

    char *html = ler_html(s);
    ler_serie(&series, html);

    return series;
}

Serie* pesquisarElementoListaDupla(ListaDupla *lista, int posicao) {
    Serie *music = (Serie*) malloc(sizeof(Serie));

    if (posicao < 0 || posicao > lista->size) {
        printf("Erro !!! a posicao e menor que zero ou maior que o tamanho da lista\n");
        printf("posicao (%d/ tamanho = %d) invalida\n", posicao, lista->size);
    } else if (posicao == 0) {
        comparacoes += 2;
        music = &lista->primeiro->prox->elemento;
        return music;
    } else if (posicao == lista->size) {
        comparacoes += 3;
        music = &lista->ultimo->elemento;
        return music;
    } else {
        comparacoes += 3;
        CelulaDupla *ant = lista->primeiro;
        for (int i = 0; i < posicao; i++) {
            ant = ant->prox;
        }

        music = &ant->elemento;

        return music;
    }
}

//Metodo para trocar um elemento de posicao
void trocarListaDupla(ListaDupla *lista, Serie *music1, Serie *music2) {
    Serie *temp = (Serie*) malloc(sizeof(Serie));

    cloneEntrePonteiros(temp, music1);
    cloneEntrePonteiros(music1, music2);
    cloneEntrePonteiros(music2, temp);
}

void sortByQuickSortDoublyList(ListaDupla *lista, int esq, int dir) {
    Serie *pivo = pesquisarElementoListaDupla(lista, ((esq + dir) / 2));
    int i = esq, j = dir;

    while (i <= j) {
        Serie *music1;
        music1 = pesquisarElementoListaDupla(lista, i);

        comparacoes++;
        while (strcmp(music1->pais , pivo->pais) < 0 || (strcmp(music1->pais , pivo->pais) == 0 && strcmp(music1->nome , pivo->pais) < 0 )) {
            i++;
            music1 = pesquisarElementoListaDupla(lista, i);
        }

        Serie *music2;
        music2 = pesquisarElementoListaDupla(lista, j);

        comparacoes++;
        while (strcmp(music2->pais , pivo->pais) > 0 || (strcmp(music2->pais , pivo->pais) == 0 && strcmp(music2->nome , pivo->pais) > 0 )) {
            j--;
            music2 = pesquisarElementoListaDupla(lista, j);
        }

        comparacoes++;
        if (i <= j) {
            trocarListaDupla(lista, music1, music2);
            i++;
            j--;
        }

        comparacoes++;
        if (esq < j) {
            sortByQuickSortDoublyList(lista, esq, j);
        }

        comparacoes++;
        if (i < dir) {
            sortByQuickSortDoublyList(lista, i, dir);
        }
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

    ListaDupla *listadupla;
    listadupla = (ListaDupla*)malloc(sizeof(ListaDupla));

    listadupla = new_lista_dupla(listadupla);

    while (!isFim(line + tam_prefixo)) {
        char *html = ler_html(line);
        ler_serie(&serie, html);
        inserirFimDupla(listadupla, &serie);
        free(html);
        readline(line + tam_prefixo, MAX_LINE_SIZE);
        numEntrada++;
    }

    inicio = clock();
    sortByQuickSortDoublyList(listadupla, 0, numEntrada - 1);
    fim = clock();

    mostrar(listadupla);

    double tempo =  ((fim - inicio) / (double)CLOCKS_PER_SEC) / 1000.0;

    FILE *arq;
    arq = fopen("matricula_quicksort2.txt","w");

    fprintf(arq, "Matricula : 655537 \t Tempo de execução : %fs\t Numero de Comparações : %i ", tempo, comparacoes);
    fclose(arq); 
    
    return EXIT_SUCCESS;
}