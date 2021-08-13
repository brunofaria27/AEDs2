class CiframentoRecursivo {
    public static String Criptografa(String s, int pos, String resposta) {
        int aux;
        if (pos < s.length()){
            aux = (int)s.charAt(pos) + 3;
            resposta += (char)aux;
            resposta = Criptografa(s, pos + 1, resposta);   // Sempre salvar e atualizar a resposta para a proxima chamada
        }
        return resposta;
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
            MyIO.println(Criptografa(entrada[i], 0, ""));   // Inicializar instancias
        }
    }
}
