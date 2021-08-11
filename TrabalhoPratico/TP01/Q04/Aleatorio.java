import java.util.Random;

public class Aleatorio {
    public static String StringTrocada(String s, Random gerador) {
        char primeira = (char)('a' + Math.abs(gerador.nextInt() % 26));
        char segunda = (char)('a' + Math.abs(gerador.nextInt() % 26));

        String novaString = ""; // Deixar inicializada como vazia
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == primeira) {
                novaString += segunda;
            } else {
                novaString += s.charAt(i);
            }
        }
        return novaString;
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

        // Criar o Random para utilizar no sorteio()
        Random gerador = new Random();
        gerador.setSeed(4);

        //Para cada linha de entrada, gerando uma de saida com a String modificada
        for(int i = 0; i < numEntrada; i++){
            MyIO.println(StringTrocada(entrada[i], gerador));
        }
    }
}
