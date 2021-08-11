class Ciframento {
    public static String Criptografa(String s) {
        String str = "";
        int auxiliar;

        for(int i = 0; i < s.length(); i++) {
            auxiliar = (int)s.charAt(i) + 3;
            str += (char)auxiliar;
        }
        return str;
    }

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) {
        String[] entrada = new String[1000];
        int numEntrada = 0;

        //Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; // Desconsiderar a palavra FIM

        //Para cada linha de entrada, gerando uma de saida ciptografada
        for(int i = 0; i < numEntrada; i++){
            MyIO.println(Criptografa(entrada[i]));
        }
    }
}
