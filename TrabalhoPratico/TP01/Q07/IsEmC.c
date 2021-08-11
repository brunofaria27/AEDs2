#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool isInteiro(char str[]) {
    bool x3 = true;

    for(int i = 0; str[i] != '\0' && x3 == true; i++){
        if(!((int)str[i] >= 48 && (int)str[i] <= 57)){
            x3 = false;
        }
    }

    return x3;
}

bool isReal(char str[]) {
    bool x4 = false;
    int qt = 0; // conta a quantidade de pontos ou virgulas na entrada, caso passe pela primeira verificação
      for(int i = 0; str[i] != '\0'; i++){
         if((int)str[i] >= 48 && (int)str[i] <= 57){
            x4 = true;
        }

        if((int)str[i] == 44)qt++;
        if((int)str[i] == 46)qt++; 
    }

    if(qt > 1)x4=false;

    return x4;
}

bool isVogal(char str[]) {
    bool x1 = true;

    for(int i = 0; str[i] != '\0' && x1 == true ;i++){
        if(str[i] != 'a' && str[i] != 'e' && str[i] != 'i' && str[i] != 'o' && str[i] != 'u'
        && str[i] != 'A'&& str[i] != 'E'&& str[i] != 'I'&& str[i] != 'O'&& str[i] != 'U'){
            x1 = false;      
        }
    }

    return x1;
}

bool isConsoante(char str[]) {
    bool x2 = true;
    char charAt[2];
    charAt[1] = '\0';
    for(int i = 0; str[i] != '\0' && x2 == true; i++){
        charAt[0] = str[i];
        if(isVogal(charAt) == true){
            x2 = false;
        }
        if(isReal(charAt) == true ){
            x2 = false;
        }
    }
    return x2;
}

void saida(char str[]) {
    char x[4][4];

    if(isVogal(str) == true) {
        strcpy(x[0],"SIM"); 
    } else { 
        strcpy(x[0],"NAO");
    }

    if(isConsoante(str) == true) {
        strcpy(x[1],"SIM");
    } else {
        strcpy(x[1],"NAO");
    }

    if(isInteiro(str) == true) {
        strcpy(x[2],"SIM");
    } else {
        strcpy(x[2],"NAO");
    }

    if(isReal(str) == true) {
        strcpy(x[3],"SIM");
    } else { 
        strcpy(x[3],"NAO");
    }

    printf("%s %s %s %s\n",x[0],x[1],x[2],x[3]);

}

bool isFim(char s[]) {
    return( strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M'); 
}

int main() {
    char entrada[1000];

    scanf(" %[^\n]s", entrada);
    do {
        saida(entrada);
        scanf("%[^\n]s", entrada);
    } while (isFim(entrada) == false);

    return 0;
}
