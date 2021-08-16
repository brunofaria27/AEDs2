public class exer2 {
    public static boolean pesquisaBinaria(int x) {
        boolean resp = false;
        int array[] = {2, 3, 5, 7, 9, 11, 15, 17, 20, 21, 30, 43, 49, 70, 71, 82};
        int n = array.length;
        int dir = n - 1;
        int esq = 0;
        int meio = 0;

        while(esq <= dir) {
            meio = (esq + dir) / 2;
            if(array[meio] <= x) {
                esq = meio + 1;
            } else {
                dir = meio - 1;
            }
        }

        if(dir <= n - 1 && dir >= 0) {
            if(array[dir] == x) {
                resp = true;
                return resp;
            }
        }
        return resp;
    }

    public static boolean isNegativo(int numero) {
        return(numero == -1); 
    }

    public static void main(String[] args) {
        int x = MyIO.readInt("Entre com o valor de X, que deseja pesquisar no array PRE DEFINIDO: ");

        if(pesquisaBinaria(x) == true) {
            MyIO.println(x + " esta contido no array.");
        }
        else {
            MyIO.println(x + " nao esta contido no array.");
        }
    }
}
