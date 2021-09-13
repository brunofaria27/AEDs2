#include <string.h>
#include <stdio.h>
#include <stdlib.h>

struct Serie {
    char nome[50];
    char formato[50];
    char duracao[50];
    char paisOrigem[50];
    char idioma[50];
    char emissoraOriginal[50];
    char transmissaoOriginal[50];
    int numeroTemporadas;
    int numeroEpisodios;
};

char* removeTags(char* old) {
    char *newLine = (char*)malloc(sizeof(strlen(old)));
    
    int i = 0, j = 0;
    while(i < strlen(old)) {
        if(old[i] == '<') {
            i++;
            while(old[i] != '>') {
                i++;
            }
        } else {
            newLine[j] = old[i];
            j++;
        }
        i++;
    }
    newLine[j] = '\0';
    return newLine;
}

int main() {
    struct Serie series;
    
    char entrada[50];
    char diretorio[50] = "/tmp/series/";
    char arquivo[50];

    // Pegar nome do arquivo e concatenar para achar o diretorio
    fgets(entrada, 50, stdin);
    strcat(strcpy(arquivo, diretorio), entrada);

    FILE *fp;
    if((fp = fopen(arquivo, "r")) == NULL) {
        printf("Falha na abertura\n");
    } else {
        char buf[2000];
        fgets(buf, 2000, fp);

        while(strstr(buf, "infobox_v2") == NULL) {
            fgets(buf, 2000, fp);
        }
        fgets(buf, 2000, fp);
        fgets(buf, 2000, fp);
        strcpy(series.nome, removeTags(buf));

        while(strstr(buf, "Formato") == NULL) {
            fgets(buf, 2000, fp);
        }
        fgets(buf, 2000, fp);
        strcpy(series.formato, removeTags(buf));

        while(strstr(buf, "Duração") == NULL) {
            fgets(buf, 2000, fp);
        }
        fgets(buf, 2000, fp);
        strcpy(series.duracao, removeTags(buf));

        while(strstr(buf, "País de origem") == NULL) {
            fgets(buf, 2000, fp);
        }
        fgets(buf, 2000, fp);
        strcpy(series.paisOrigem, removeTags(buf));

        while(strstr(buf, "Idioma original") == NULL) {
            fgets(buf, 2000, fp);
        }
        fgets(buf, 2000, fp);
        strcpy(series.idioma, removeTags(buf));

        printf("%s %s %s %s %s", series.nome, series.formato, series.duracao, series.paisOrigem, series.idioma);
    }
    

    return 0;
}