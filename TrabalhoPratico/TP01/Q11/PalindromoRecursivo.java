class PalindromoRecursivo {
    public static boolean ehPalind(String s, int pos){
        boolean resp;
        if(pos >= s.length() / 2) {
            resp = true;
        } else if(s.charAt(pos) != s.charAt(s.length() - 1 - pos)) {    // ver se a ultima posicao e igual a primeira e assim por diante
            resp = false;
        } else {
            resp = ehPalind(s, pos + 1);    // atualizar o valor sempre que nao descobrir
        }
        return resp;
    }

    public static void saida(String s, int pos){
        if(ehPalind(s, pos) == true ){
           MyIO.println("SIM");
        } else {
           MyIO.println("NAO");
        }
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

        //Para cada linha de entrada, gerando uma de saida SIM (palindromo) - NAO (não palindromo)
        for(int i = 0; i < numEntrada; i++){
            saida(entrada[i], 0);
        }
    }
}