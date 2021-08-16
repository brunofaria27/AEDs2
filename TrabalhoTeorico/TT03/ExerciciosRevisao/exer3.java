public class exer3 {
    public static void maior(int[] array) {
        int maior = array[0];

        for(int i = 1; i < array.length; i++) {
            if(maior < array[i]) {
                maior = array[i];
            }
        }
        MyIO.println("Maior: " + maior);
    }

    public static void menor(int[] array) {
        int menor = array[0];

        for(int i = 1; i < array.length; i++) {
            if(menor > array[i]) {
                menor = array[i];
            }
        }
        MyIO.println("Menor: " + menor);
    }

    public static boolean isFim(String s) {
        return(s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) =='M'); 
    }

    public static void main(String[] args) {
        String[] entradas = new String[100];
        int numentrada = 0;

        MyIO.println("Entre com os valores do array: [FIM para parar] ");

        do {
            entradas[numentrada] = MyIO.readString();
        } while(isFim(entradas[numentrada++]) == false );
        numentrada--;

        int[] array = new int[numentrada];

        for(int i = 0; i < numentrada; i++){
            array[i] = Integer.parseInt(entradas[i]);
        }

        maior(array);
        menor(array);

    }
}