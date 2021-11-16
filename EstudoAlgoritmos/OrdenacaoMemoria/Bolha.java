class Bolha extends Geracao {

    /**
    * Construtor.
    */
    public Bolha(){
      super();
   }

    /**
    * Construtor.
    * @param int tamanho do array de numeros inteiros.
    */
    public Bolha(int tamanho){
        super(tamanho);
    }

    /**
    * Algoritmo de ordenacao Bolha.
    */
    @Override
    public void sort() {
        for (int i = (n - 1); i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j+1);
                }
            }
        }
    }

}
