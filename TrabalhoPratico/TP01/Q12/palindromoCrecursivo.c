#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

void saidaPalind(char s[]);
bool ehPalind(char s[], int i);

bool ePalind(char s[]) {
    return ehPalind(s,0);
}


bool ehPalind(char s[], int i) {
    bool resp;
    
    if(i >= strlen(s) / 2) {
        resp = true;
    } else if (s[i] != s[strlen(s)-1-i]) {
        resp = false;
    }  else {
        resp = ehPalind(s,i+1);
    } 

    return resp;
}

void saidaPalind(char s[]) {
    if( ePalind(s) == true ) {
        printf("SIM\n");
    } else {
        printf("NAO\n");
    }
}

bool isFim(char s[]) {
    return( strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M'); 
}


int main() { 
    char entrada[1000];

    scanf(" %[^\n]s",entrada);
    do {
        saidaPalind(entrada);
        scanf(" %[^\n]s",entrada);
    } while (isFim(entrada) == false);

    return 0;
}