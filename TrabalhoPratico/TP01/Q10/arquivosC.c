#include <stdlib.h>
#include <stdio.h>

int main() {
    int entrada;
    scanf("%i", &entrada);

    FILE *raf;
    raf = fopen("numeros.txt", "w+"); // abre arquivo para leitura e escrita

    float num;
    for (int i = 0; i < entrada; i++) {
        scanf("%f", &num);
        fwrite(&num, sizeof(float), 1, raf);
    }

    fclose(raf);

    FILE *arq;
    arq = fopen("numeros.txt", "r");
    
    int tam = -4;
    float aux;
    for (int i = 0; i < entrada; i++) {
        fseek(arq, tam, SEEK_END);
        fread(&aux, sizeof(float), 1, arq);
        printf("%g\n", aux);

        tam -= 4;
    }

    return 0;
}