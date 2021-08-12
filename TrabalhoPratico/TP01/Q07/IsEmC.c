#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include<string.h>

/* PROCEDIMENTOS PARA FACILITAR A RESOLUÇÃO DO EXERCÍCIO */

int length(char palavra[]) {
    int contador = 0;
    int i = 0;

    while(palavra[i] != '\0') {
        contador++;
        i++;
    }
    
    return contador - 1;
}

char toUpper(char c) {
    return (c >= 'a' && c <= 'z') ? ((char) (c - 32)) : c;  // Transformar para maiusculo de acordo com ASCII
}

bool isNumber(char c) {
    if((c >= '0' && c <= '9') || (c == '.' || c == ',')) {
        return true;
    }

    return false;
}

/* END PROCEDIMENTOS PARA FACILITAR A RESOLUÇÃO DO EXERCÍCIO */

bool isVogal(char palavra[]) {
    char aux = '-';
    int tamanho = length(palavra);

    for(int i = 0; i < tamanho; i++) {
        aux = palavra[i]; // Colocar o caracter na variavel aux
        aux = toUpper(aux); // transformar para maiusculo
        if(aux != 'A' && aux != 'E' && aux != 'I' && aux != 'O' && aux != 'U') {
            return false;
        }
    }
    return true;
}

bool isConsoante(char palavra[]) {
    char aux = '-';
    int tamanho = length(palavra);
    
    for(int i = 0; i < tamanho; i++) {
        aux = palavra[i];
        aux = toUpper(aux);
        if(aux != 'B' && aux != 'C' && aux != 'D' && aux != 'F' && aux != 'G'&& aux != 'J' && aux != 'K' && aux != 'L' && aux != 'M' && aux != 'N' && aux != 'P' && aux != 'Q' && aux != 'R' && aux != 'S' && aux != 'T'&& aux != 'V' && aux != 'W' && aux != 'X' && aux != 'Z') {
            return false;
        }
    }
    return true;
}

bool isInt(char palavra[]) {
    char aux = '-';
    int tamanho = length(palavra);

    for(int i = 0; i < tamanho; i++) {
        aux = palavra[i];
        if(isNumber(aux)) {
            if (aux == '.' || aux == ',') {
                return false;
            }
        } else {
            return false;
        }
    }
    return true;
}

bool isReal(char palavra[]) {
    char aux = '-';
    int count = 0;
    int tamanho = length(palavra);

    for(int i = 0; i < tamanho; i++) {
        aux = palavra[i];
        if(isNumber(aux)) {
            if (aux == '.' || aux == ',') {
                count++;
            }
        } else {
            return false;
        }
    }

    if(count == 1 || count == 0) {
        return true;
    } else {
        return false;
    }
}

bool isFim(char palavra[]) {
    if(palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M') {
        return true;
    }
    return false;
}

void saida(char palavra[]) {
    if (isVogal(palavra)) {
        printf("SIM ");
    } else {
        printf("NAO ");
    }

    if (isConsoante(palavra)) {
        printf("SIM ");
    } else {
        printf("NAO ");
    }

    if (isInt(palavra)) {
        printf("SIM ");
    } else {
        printf("NAO ");
    }

    if (isReal(palavra)) {
        printf("SIM ");
    } else {
        printf("NAO ");
    }
}

int main(void) {
    char entrada[1000] = "";
    
    while(isFim(entrada) == false) {
        fgets(entrada, 1000, stdin);
        if(isFim(entrada) == false) {
            saida(entrada);
            printf("\n");
        }
    }

    return 0;
}