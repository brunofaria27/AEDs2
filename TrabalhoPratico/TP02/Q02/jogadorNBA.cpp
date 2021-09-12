    #include <stdio.h>
    #include <stdlib.h>
    #include <stdbool.h>
    #include <string.h>

    #define tam 1000
    /*
    classe jogador
    */
    typedef struct {
        //atributos
        int id = 0;
        char nome[tam];
        int altura = 0;
        int peso = 0;
        char universidade[tam];
        char anoNascimento[tam];
        char cidadeNascimento[tam];
        char estadoNascimento[tam];
    }Jogador;

    //funçao ler
    //s = string contendo infos do objeto
    //*jogador = objeto a ser criado
    void ler(char s[], Jogador *jogador){
        s[strlen(s) - 2] = '\0'; // retira \n do final da string
        //s[strlen(s) - 1] = '\0'; // retira \n do final da string
        char atributos[8][tam];//recebe infos em ordem
        int i=0;
        char *aux = strsep(&s, ",");

        while(aux != NULL){
            strcpy(atributos[i],aux);
            //printf("[%i->%s] ",i,atributos[i]);
            i++;
            aux = strsep(&s,",");
        }
          
        int idint = atoi(atributos[0]);
        int alturaint = atoi(atributos[2]);
        int pesoint = atoi(atributos[3]);
        char *uni = atributos[4];
        char *cidade = atributos[6];
        char *estado = atributos[7];

        if(strlen(uni) == 0){ strcpy(uni,"nao informado"); }
        if(strlen(cidade) == 0){ strcpy(cidade,"nao informado"); }
        if(strlen(estado) == 0){ strcpy(estado,"nao informado"); }

        jogador->id = idint;
        strcpy(jogador->nome,atributos[1]);
        jogador->altura = alturaint;
        jogador->peso = pesoint;
        strcpy(jogador->anoNascimento,atributos[5]);
        strcpy(jogador->universidade,uni);
        strcpy(jogador->cidadeNascimento,cidade);
        strcpy(jogador->estadoNascimento,estado);
    }

void imprimir(Jogador *jogador){
    printf("[");
    printf("%i ## ",jogador->id);
    printf("%s ## ",jogador->nome);
    printf("%i ## ",jogador->altura);
    printf("%i ## ",jogador->peso);
    printf("%s ## ",jogador->anoNascimento);
    printf("%s ## ",jogador->universidade);
    printf("%s ## ",jogador->cidadeNascimento);
    printf("%s",jogador->estadoNascimento);
    printf("]");
    printf("\n");
}


void lerAndImprimir(char s[][tam], int numentrada){
    char arquivo[4000][tam];
    int i=0;
    FILE *arq;
    arq = fopen("/tmp/players.csv","r");
    while(fgets(arquivo[i],tam,arq)){  i++;  }//array de strings recebe dados do aqruivo
    fclose(arq);

    int idObj = 0;

    Jogador *jogador[numentrada];

    for(int i=0;i<numentrada;i++){
        idObj = atoi(s[i]);
        //printf("%i ->",idObj);
        jogador[i] = (Jogador*)malloc(sizeof(Jogador));
        ler(arquivo[idObj+1],jogador[i]);
    }

    for(int i=0;i<numentrada;i++){
        imprimir(jogador[i]);
    }


}

bool isFim(char s[]){
    return( strlen(s) == 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

int main(){
    int numentrada = 0;
    char entrada[tam][tam];

    do {
        fgets(entrada[numentrada],tam,stdin);
        //scanf(" %[^\n]s",entrada[numentrada]);isFim(entrada[numentrada++]) == true
    } while (strncmp(entrada[numentrada++],"FIM",3) != 0);
    numentrada--;

    lerAndImprimir(entrada, numentrada);

    return 0;
}
