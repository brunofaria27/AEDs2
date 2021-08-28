#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

bool isZero(int numero) {
    if(numero == 0) {
        return true;
    } else {
        return false;
    }
}

int main() {
    int donaM = -1, filhoVelho = 0, filhoUm = 0, filhoDois = 0, filhoTres = 0;
    while(isZero(donaM) == false) {
        scanf("%d", &donaM);

        if(isZero(donaM) == false) {
            scanf("%d", &filhoUm);
            scanf("%d", &filhoDois);

            filhoTres = donaM - (filhoUm + filhoDois);
            
            filhoVelho = filhoTres;
            if(filhoVelho < filhoDois) {
                filhoVelho = filhoDois;
            } else if(filhoVelho < filhoUm) {
                filhoVelho = filhoUm;
            }

            printf("%d\n", filhoVelho);
        } else {
            exit(0);
        }

    }

    return 0;
}