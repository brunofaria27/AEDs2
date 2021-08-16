/*
 * Exc 01 Nivelamento
 * Verificar se numero x 
 * está contido no array	
*/

public class exer1 {
    public static boolean confirmacaoArray(int[] array, int x) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == x) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNegativo(int numero) {
        return(numero == -1); 
    }

    public static void main(String[] args) {
        MyIO.println("Entre com os valores do array: [-1 para parar] ");

        int[] array = new int[1000];
        int numentrada = 0;

        do {
            array[numentrada] = MyIO.readInt();
        } while(isNegativo(array[numentrada++]) == false );
        numentrada--;

        int x = MyIO.readInt("Entre com o valor de X: ");

        if(confirmacaoArray(array, x) == true ) {
            MyIO.println(x + " esta contido no array.");
        }
        else {
            MyIO.println(x + " nao esta contido no array.");
        }
    }
}
