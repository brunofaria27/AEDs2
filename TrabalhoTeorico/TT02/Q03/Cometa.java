class Cometa {
    public static boolean isZero(int numero) {
        return (numero == 0);
    }

    public static int proximoAno(int ano) {
        int proxAno;
        int count = 0;

        // Calculo para descobrir o ano
        count = (ano - 10) / 76;
        count = count + 1;

        proxAno = count * 76 + 10;

        return proxAno;
    }

    public static void main(String[] args) {
        int[] entrada = new int[1000];
        int numEntrada = 0;

        //Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readInt();
        } while (isZero(entrada[numEntrada++]) == false);
        numEntrada--; // Desconsiderar o numero 0

        //Para cada linha de entrada, gerando uma de saida SIM (palindromo) - NAO (não palindromo)
        for(int i = 0; i < numEntrada; i++){
            MyIO.println(proximoAno(entrada[i]));
        }
    }
}
