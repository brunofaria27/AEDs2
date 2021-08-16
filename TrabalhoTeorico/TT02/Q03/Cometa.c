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
    int ano = -1, count = 0, proxAno;

    while(isZero(ano) == false) {
        scanf("%d", &ano);

        if(isZero(ano) == false) {
            // Calculo para descobrir o ano
            count = (ano - 10) / 76;
            count = count + 1;

            proxAno = count * 76 + 10;
            printf("%d\n", proxAno);
        } else {
            exit(0);
        }

    }

    return 0;
}