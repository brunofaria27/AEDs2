public class IsRecursivo {
    public static boolean isNumber(char c) {
        if ((c >= '0' && c <= '9') || (c == '.' || c == ',')) {
            return true;
        }
        return false;
    }

    public static boolean vogais(char c) {
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            return true;
        }
        return false;
    }

    public static boolean consoantes(char c) {
        if (c == 'B' || c == 'C' || c == 'D' || c == 'F' || c == 'G' || c == 'J' || c == 'K' || c == 'L' || c == 'M' || c == 'N' || c == 'P' || c == 'Q' || c == 'R' || c == 'S' || c == 'T' || c == 'V' || c == 'W' || c == 'X' || c == 'Z') {
            return true;
        }
        return false;
    }

    public static boolean isVogal(String s, int i, boolean estado) {
        s = s.toUpperCase();

        if(i < s.length()) {
            if (vogais(s.charAt(i)) == false) {
                return false;
            } else {
                estado = isVogal(s, ++i, estado);
            }
        }
        return estado;
    }

    public static boolean isConsoante(String s, int i, boolean estado) {
        s = s.toUpperCase();

        if(i < s.length()) {
            if (consoantes(s.charAt(i)) == false) {
                return false;
            } else {
                estado = isConsoante(s, ++i, estado);
            }
        }
        return estado;
    }

    public static boolean isInt(String s, int i, boolean estado) {
        if (i < s.length()) {
            if (isNumber(s.charAt(i)) == false) {
                return false;
            } else {    
                if (s.charAt(i) == '.' || s.charAt(i) == ',') {
                    return false;
                } else {
                    estado = true;
                    estado = isInt(s, ++i, estado);
                }
            }
        }
        return estado;
    }

    public static int isReal(String s, int i, int count) {
        if (i < s.length()) {
            if (isNumber(s.charAt(i)) == false) {
                return -1;
            } else {
                if (s.charAt(i) == '.' || s.charAt(i) == ',') {
                    count = count + isReal(s, ++i, count++);
                } else {
                    count = count + isReal(s, ++i, count);
                }
            }
        }
        return count;
    }

    public static void saida(String s) {
        int count = 0;
        int i = 0;
        boolean resp = true;
        resp = isVogal(s, i, resp);
        String x1, x2, x3, x4;

        if(resp) {
            x1 = "SIM";
        } else {
            x1 = "NAO";
        }
        
        if (isConsoante(s, i, resp)) {
            x2 = "SIM";
        } else {
            x2 = "NAO";
        }
        
        if (isInt(s, i, resp)) {
            x3 = "SIM";
        } else {
            x3 = "NAO";
        }
        
        if (isReal(s, i, count) == 1 || isReal(s, i, count) == 0) {
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
        // MyIO.setCharset("ISO-8859-1");
        String[] entrada = new String[1000];
        int numEntrada = 0;

        // Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; // Desconsiderar ultima linha contendo a palavra FIM

        for (int i = 0; i < numEntrada; i++) {
            saida(entrada[i]);
        }
    }
}