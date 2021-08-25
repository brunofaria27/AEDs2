public class AlgebraBooleana {
    public static boolean isAnd(String expressao, int i) {
        if(expressao.charAt(i) == 'a') {
            if (expressao.charAt(i + 1) == 'n') {
                if(expressao.charAt(i + 2) == 'd') {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isNot(String expressao, int i) {
        if (expressao.charAt(i) == 'n') {
            if(expressao.charAt(i + 1) == 'o') {
                if(expressao.charAt(i + 2) == 't') {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isOr(String expressao, int i) {
        if(expressao.charAt(i) == 'o') {
            if(expressao.charAt(i + 1) == 'r') {
                return true;
            }
        }
        return false;
    }

    public static boolean BooleanValue(int valor) {
        if(valor == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean CalcularExpressao(String expressao, int quantidade) {
        int[] array = new int[quantidade];
        int j = 2;
        boolean bool = false;

        for (int i = 0; i < quantidade; i++) {
            array[i] = ((int) expressao.charAt(j)) - 48;
            j = j + 2;
        }
        
        // Caso exista 2 variaveis
        if(quantidade == 2) {
            if(isAnd(expressao, j)) {
                if(isNot(expressao, j + 4)) {
                    if(isNot(expressao, j + 13)) {
                        bool = (!BooleanValue(array[0])) && (!BooleanValue(array[1]));
                        return bool;
                    }
                }
            }
            
            if(isNot(expressao, j)) {
                if(isAnd(expressao, j + 4)) {
                    bool = !(BooleanValue(array[0]) && BooleanValue(array[1]));
                    return bool;
                }
            }
        }

        // Caso exista 3 variaveis
        if(quantidade == 3) {
            if(isAnd(expressao, j)) {
                if(isOr(expressao, j + 4)) {
                    if(isNot(expressao, j + 16)) {
                        if(isAnd(expressao, j + 20)) {
                            bool = ((BooleanValue(array[0]) || BooleanValue(array[1])) && (!(BooleanValue(array[1]) && BooleanValue(array[2]))));
                            return bool;
                        }
                    }

                    if(expressao.charAt(j + 7) == 'A') {
                        bool = (BooleanValue(array[0]) || BooleanValue(array[1]) || (! (BooleanValue(array[2])))) && (BooleanValue(array[1]) || BooleanValue(array[2])) && (!BooleanValue(array[0]) || BooleanValue(array[2]));
                        return bool;
                    }
                }

                if(expressao.charAt(j + 4) == 'A'){
                    if(isOr(expressao, j + 8)) {
                        bool = (BooleanValue(array[0]) && (BooleanValue(array[1]) || BooleanValue(array[2])));
                        return bool;
                    }
                }
            }

            if(isOr(expressao, j)) {
                if(isAnd(expressao, j + 3)) {
                    if(isAnd(expressao, j + 16)) {
                        bool = ((BooleanValue(array[0]) && BooleanValue(array[1])) || (BooleanValue(array[0]) && BooleanValue(array[2])));
                        return bool;
                    }

                    if(isAnd(expressao, j + 15)) {
                        bool = ((BooleanValue(array[0]) && BooleanValue(array[1])) || (BooleanValue(array[0]) && BooleanValue(array[2])));
                        return bool;
                    }

                    if(isAnd(expressao, j + 20)) {
                        if(isOr(expressao, j + 24)) {
                            bool = (BooleanValue(array[0]) && BooleanValue(array[1]) && BooleanValue(array[2])) || ((BooleanValue(array[0]) || BooleanValue(array[1])) && BooleanValue(array[2]));
                            return bool;
                        }
                        if(expressao.charAt(j + 24) == 'A') {
                            if(isNot(expressao, j + 28)) {
                                if(expressao.charAt(j + 37) == 'C') {
                                    bool = (BooleanValue(array[0]) && BooleanValue(array[1]) && BooleanValue(array[2])) || (BooleanValue(array[0]) && (! (BooleanValue(array[1]))) && BooleanValue(array[2])) || ((!(BooleanValue(array[0]))) && (!(BooleanValue(array[1]))) && BooleanValue(array[2])) || ((!(BooleanValue(array[0]))) && (!(BooleanValue(array[1]))) && (!(BooleanValue(array[2]))));
                                    return bool;
                                }
                            }
                        }
                        if(isNot(expressao, j + 37)) {
                            bool = ((BooleanValue(array[0]) && BooleanValue(array[1]) && BooleanValue(array[2]))) || ((BooleanValue(array[0]) && (!(BooleanValue(array[1]))) && (!(!(BooleanValue(array[0])) && (!(BooleanValue(array[2])))))));
                            return bool;
                        }
                    }
                }

                if(expressao.charAt(j + 3) == 'A') {
                    if(isAnd(expressao, j + 7)) {
                        bool = (BooleanValue(array[0]) || (BooleanValue(array[1]) && BooleanValue(array[2])));
                        return bool;
                    }
                }

                if(isOr(expressao, j + 3)) {
                    if(isAnd(expressao, j + 6)) {
                        bool = (((! (BooleanValue(array[0]) && BooleanValue(array[1]))) && (!(BooleanValue(array[2])))) || ((!(BooleanValue(array[0]))) && BooleanValue(array[1]) && BooleanValue(array[2])) || (BooleanValue(array[0]) && BooleanValue(array[1]) && BooleanValue(array[2])) || (BooleanValue(array[0]) && (! (BooleanValue(array[1]))) && (!(BooleanValue(array[2]))))) || (BooleanValue(array[0]) && (!(BooleanValue(array[1]))) && BooleanValue(array[2]));
                        return bool;
                    }
                    bool =  (BooleanValue(array[0]) || BooleanValue(array[1])) || BooleanValue(array[2]);
                    return bool;
                }

                if(isNot(expressao, j + 3)) {
                    bool = !BooleanValue(array[0]) || BooleanValue(array[1]) || (BooleanValue(array[0]) && BooleanValue(array[1]) && (!BooleanValue(array[2])));
                    return bool;
                }
            }
        }
        return false;
    }

    public static boolean isFim(String s) {
        return (s.length() == 1 && s.charAt(0) == '0');
    }
    
    public static void main(String[] args) {
        String[] entrada = new String[1000];
        int numEntrada = 0;
        int quantidade = 0;
        boolean resp = false;

        // Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; // Desconsiderar ultima linha contendo o num "0"

        for (int i = 0; i < numEntrada; i++) {
            quantidade = ((int)entrada[i].charAt(0)) - 48;
            resp = CalcularExpressao(entrada[i], quantidade);
            if (resp == true) {
                MyIO.println(1);
            } else {
                MyIO.println(0);
            }
        }
    }
}
