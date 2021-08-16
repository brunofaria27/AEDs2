public class Q02 {
    public static boolean pesquisaBinaria(int x) {
        boolean resp = false;
        int array[] = {2, 3, 5, 7, 9, 11, 15, 17, 20, 21, 30, 43, 49, 70, 71, 82};
        int n = array.length;
        int dir = n - 1;    // Pegar a ultima posicao (n)
        int esq = 0;    // Pegar a primeira posicao(n)
        int meio = 0;   // Guardar a posicao do meio

        while(esq <= dir) { // Continuar fazendo enquanto as posicoes nao se cruzarem
            meio = (esq + dir) / 2; // Calcular posicao do meio
            if(array[meio] <= x) { /* Ver se o elemento que procura é maior que o elemento do meio */
                esq = meio + 1; /* Se for, o elemento esta na segunda metade */
            } else {
                dir = meio - 1; /* Senão está na primeira metade */
            }
        }

        // Ver se a primeira posicao esta no intervalo do array
        if(dir <= n - 1 && dir >= 0) {
            if(array[dir] == x) {   // Unica comparacao
                resp = true;
                return resp;
            }
        }
        return resp;
    }
    public static void main(String[] args) {
        int x = 35; // valor a ser pesquisado
        if(pesquisaBinaria(x) == true) {
            System.out.println("tem");
        } else {
            System.out.println("não tem");
        }
    }
}
