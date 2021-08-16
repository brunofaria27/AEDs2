#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool ehPalind(char s[]){
    
    int tam = strlen(s) -1;
    for(int i = 0, j = tam; i < j; i++, j--){
        if(s[i] != s[j]){
            return false;
        }
    }
    return true;
}

void saidaPalind(char s[]){
    if( ehPalind(s) == true ){
        printf("SIM\n");
    }else {
        printf("NAO\n");
    }
}

bool isFim(char s[]){
    return(strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M'); 
}


int main(){ 
    char entrada[1000];

    scanf(" %[^\n]s",entrada);
    do {
        saidaPalind(entrada);
        scanf(" %[^\n]s",entrada);
    } while (isFim(entrada) == false);

    return 0;
}