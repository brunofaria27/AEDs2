#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

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

/*
void guardarDados(struct Serie series) {
    FILE *fp = fopen("/tmp/series/Friends.html", "r");
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

    while(strstr(buf, "Idioma original") == NULL) {
        fgets(buf, 2000, fp);
    }
    fgets(buf, 2000, fp);
    strcpy(series.idioma, removeTags(buf));

    while(strstr(buf, "Emissora de televisão original") == NULL) {
        fgets(buf, 2000, fp);
    }
    fgets(buf, 2000, fp);
    strcpy(series.emissoraOriginal, removeTags(buf));

    while(strstr(buf, "Transmissão original") == NULL) {
        fgets(buf, 2000, fp);
    }
    fgets(buf, 2000, fp);
    strcpy(series.transmissaoOriginal, removeTags(buf));

    printf("%s %s %s %s %s %s", series.nome, series.formato, series.duracao, series.idioma, series.emissoraOriginal, series.transmissaoOriginal);
}

void imprimir() {
    struct Serie series;
    guardarDados(series);
}
*/

int main() {
    FILE *fp = fopen("/tmp/series/Friends.html", "r");
    char buf[2000];
    struct Serie series;
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

    while(strstr(buf, "Idioma original") == NULL) {
        fgets(buf, 2000, fp);
    }
    fgets(buf, 2000, fp);
    strcpy(series.idioma, removeTags(buf));

    printf("%s %s %s %s", series.nome, series.formato, series.duracao, series.idioma);

    return 0;
}