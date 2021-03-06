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

long tam_arquivo(FILE *file) {
    fseek(file, 0L, SEEK_END);
    long size = ftell(file);
    rewind(file);
    return size;
}

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

    ptr = strstr(ptr, "Dura????o");
    ptr = strstr(ptr, "<td");
    strcpy(serie->duracao, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Pa??s de origem");
    ptr = strstr(ptr, "<td");
    strcpy(serie->pais, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Idioma original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->idioma, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Emissora de televis??o original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->emissora, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Transmiss??o original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->transmissao, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "N.?? de temporadas");
    ptr = strstr(ptr, "<td");
    sscanf(extrair_texto(ptr, texto), "%d", &serie->num_temporadas);

    ptr = strstr(ptr, "N.?? de epis??dios");
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

// CELULA 
typedef struct Celula {
    Serie elemento; // Pode ter erro
    struct Celula *prox;
} Celula;

// Criar uma nova Celula
Celula* new_celula(Serie *elemento){
    Celula *temp = (Celula*)malloc(sizeof(Celula));
    temp->elemento = *elemento; // Pode ter erro
    temp->prox = NULL;
    return temp;
}
// FIM CELULA

// CLASSE PILHA
// Definindo a estrutura do tipo pilha flexivel
typedef struct PilhaFlexivel {
    struct Celula *topo;
    int size;
} PilhaFlexivel;

// Construtor para inicializar a pilha flexivel
PilhaFlexivel* new_PilhaFlexivel(PilhaFlexivel *pilha) {
    pilha->topo = NULL;
    pilha->size = 0;

    return pilha;
}

// Metodo para retornar o tamanho da pilha flexivel
int tamanhoPilha(PilhaFlexivel *pilha) {
    return pilha->size;
}

// Inserir um elemento na pilha flexivel
void inserirPilha(PilhaFlexivel *pilha, Serie *elemento) {
    Celula *temp = new_celula(elemento);
    temp->prox = pilha->topo;
    pilha->topo = temp;
    pilha->size++;
}

// Remover uma musica da pilha de musicas
Serie removerPilha(PilhaFlexivel *pilha) {
    // Verificar se a pilha esta vazia
    if (pilha->topo == NULL) {
        printf("\nA pilha esta vazia !!!\n");
        exit(0);
    }

    Serie resp = pilha->topo->elemento;
    Celula *temp = pilha->topo;
    pilha->topo = pilha->topo->prox;
    pilha->size--;
    free(temp);

    return resp;
}

void mostrarPilha(PilhaFlexivel *pilha) {
    Celula *i;
    int contador = 0;

    for (i = pilha->topo; i != NULL; i = i->prox) {
        printf("%s %s %s %s %s %s %s %i %i\n", i->elemento.nome,
                                               i->elemento.formato,
                                               i->elemento.duracao,
                                               i->elemento.pais,
                                               i->elemento.idioma,
                                               i->elemento.emissora,
                                               i->elemento.transmissao,
                                               i->elemento.num_temporadas,
                                               i->elemento.num_episodios);
        contador++;
    }
}
// FIM CLASSE PILHA

Serie removidos[20]; // series removidos
int r = 0; // contador removidos

Serie lerDados(char s[]) {
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

void trataEntradas(char s[], PilhaFlexivel *pilha){
    char *aux[2];
    char nomeArq[100] = {"/tmp/series/"};
    Serie objector;

    if(s[0] == 'I'){
        aux[0] = strtok(s, " "); 
        aux[1] = strtok(NULL, " ");
        strcat(nomeArq, aux[1]);
        objector = lerDados(nomeArq);
        inserirPilha(pilha, &objector);
    } else {
        removidos[r] = removerPilha(pilha);
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

    PilhaFlexivel *pilhaflexivel;
    pilhaflexivel = (PilhaFlexivel*)malloc(sizeof(PilhaFlexivel));

    pilhaflexivel = new_PilhaFlexivel(pilhaflexivel);

    while (!isFim(line + tam_prefixo)) {
        char *html = ler_html(line);
        ler_serie(&serie, html);
        inserirPilha(pilhaflexivel, &serie);
        free(html);
        readline(line + tam_prefixo, MAX_LINE_SIZE);
        numEntrada++;
        
    }

    int n;
    scanf("%i",&n);
    char comandos[n][MAX_LINE_SIZE];

    for(int i=0; i<n; i++){
        scanf(" %[^\n]s", comandos[i]); 
        trataEntradas(comandos[i], pilhaflexivel);
    }

    printaRemovidos();
    mostrarPilha(pilhaflexivel);

    return EXIT_SUCCESS;
}