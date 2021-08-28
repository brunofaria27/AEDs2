class Dona {
    public static boolean isZero(int idade) {
        return (idade == 0);
    }

    public static void main(String[] args) {
        int donaM = -1, filhoVelho = 0, filhoUm = 0, filhoDois = 0, filhoTres = 0;
        while(isZero(donaM) == false) {
            donaM = MyIO.readInt();

            if(isZero(donaM) == false) {
                filhoUm = MyIO.readInt();
                filhoDois = MyIO.readInt();

                filhoTres = donaM - (filhoUm + filhoDois);

                filhoVelho = filhoTres;
                if(filhoVelho < filhoDois) {
                    filhoVelho = filhoDois;
                } else if(filhoVelho < filhoUm) {
                    filhoVelho = filhoUm;
                }

                MyIO.println(filhoVelho);
            } else {
                System.exit(0);
            }

        }
    }
}
