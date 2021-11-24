class No {
    public int elemento; // Conteudo do no.
    public No esq, dir;  // Filhos da esq e dir.

    public No(int elemento) {
        this(elemento, null, null);
    }

    public No(int elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}