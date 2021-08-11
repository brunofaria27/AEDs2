public class IsEmJava {
    public static boolean isVogal(String s){
        boolean x1 = true;
        s = s.toUpperCase();    // transformar todos os char da string para maiusculo

        for(int i = 0; i < s.length() && x1 == true; i++){
            if(s.charAt(i) != 'A' && s.charAt(i) != 'E' && s.charAt(i) != 'I' && s.charAt(i) != 'O' && s.charAt(i) != 'U'){
                x1 = false;
            }
        }
        return x1;
    }

    public static boolean isConsoante(String s){
        boolean x2 = true;

        for(int i = 0; i < s.length() && x2 == true; i++){
            if(isVogal(s.charAt(i) + "") == true){
                x2 = false;
            }
            if(isReal(s.charAt(i) + "") == true ){
                x2 = false;
            }
        }
        return x2;
    }

    public static boolean isInteiro(String s){
        boolean x3 = true;

        try {
            int x = Integer.parseInt(s) ; //exception caso str nao seja um numero
        } catch (Exception e) {
            x3 = false;
        }

        return x3;
    }

    public static boolean isReal(String s){
        boolean x4 = true;

        s = s.replace(",", ".");    // trocar , por . na string

        try {                                   
            double x = Double.parseDouble(s); //exception caso str nao seja um numero
        } catch (Exception e) {    
            x4 = false;
        }

        return x4;
    }

    public static void saida(String s) {
        String x1, x2, x3, x4;

        if(isVogal(s) == true) {
            x1 = "SIM";
        } else { 
            x1 = "NAO";
        }

        if(isConsoante(s) == true) {
            x2 = "SIM";
        } else {
            x2 = "NAO";
        }

        if(isInteiro(s) == true) {
            x3 = "SIM";
        } else { 
            x3 = "NAO";
        }

        if(isReal(s) == true) {
            x4 = "SIM";
        } else {
            x4 = "NAO";
        }

        MyIO.println(x1 + " " + x2 + " " + x3 + " " + x4);
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

        //Para cada linha de entrada, gerando uma de saida com a String modificada
        for(int i = 0; i < numEntrada; i++){
            saida(entrada[i]);
        }
    }
}
