class Celula {
    public int elemento;
    public Celula inf, sup, esq, dir;

    public Celula() {
        this(0, null, null, null, null);
    }

    public Celula(int elemento) {
        this(elemento, null, null, null, null);
    }

    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir) {
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;

    }

} // fim Celula

class Matriz {
    private Celula inicio = new Celula();
    private Celula ultima, aux, embaixo;
    public int linha, coluna;

    /*
    * Construtor sem parametros da Matriz
    */
    public Matriz() {
        this(3, 3);
    }

    /*
    * Construtor com parametros da Matriz
    */
    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        // Primeiro for é para linha
        for (int i = 0; i < linha; i++) {
            // Segundo for é para coluna
            for (int j = 0; j < coluna; j++) {
                if (i == 0) {
                    if (j == 0) {
                        this.inicio = new Celula();
                        aux = inicio;
                        embaixo = inicio;
                    } else {
                        Celula celula = new Celula();
                        aux.dir = celula;
                        aux.dir.esq = aux;
                        aux = aux.dir;
                    }
                } else {
                    Celula celula = new Celula();
                    aux.inf = celula;
                    celula.sup = aux;
                    if (j != 0) {
                        aux.esq.inf.dir = celula;
                        celula.esq = aux.esq.inf;
                    }
                    if (aux.dir != null) {
                        aux = aux.dir;
                    }
                }
            }
            if (i != 0) {
                embaixo = embaixo.inf;
            }
            aux = embaixo;
        }
    }

    /*
    * Seta os elementos nas respectivas posições da Matriz
    */
    public void Set() {
        int elemento = 0;
        Celula coluna = new Celula();
        Celula linha = inicio;

        for (int i = 0; i < this.linha; i++, linha = linha.inf) {
            coluna = linha;
            for (int j = 0; j < this.coluna; j++, coluna = coluna.dir) {
                coluna.elemento = MyIO.readInt();
            }
        }
    }

    /*
    * Imprime todos os elementos da matriz
    */
    public void Imprimir() {
        Celula i;
        Celula p = inicio;
        for (int l = 0; l < linha; p = p.inf, l++) {
            i = p;
            for (int j = 0; j < coluna; i = i.dir, j++) {
                MyIO.print(i.elemento + " ");
            }

            MyIO.println("");
        }
    }

    /*
    * @method soma as matrizes dinamias M1 e M2
    * @return valor resultante das soma das matrizes
    */
    public Matriz Somar(Matriz matriz) {
        Matriz resp = new Matriz(this.linha, this.coluna);
        if (this.linha == matriz.linha && this.coluna == matriz.coluna) {
            Celula a0 = this.inicio;
            Celula a1 = a0;
            Celula b0 = matriz.inicio;
            Celula b1 = b0;
            Celula c0 = resp.inicio;
            Celula c1 = c0;

            for (int i = 0; i < this.linha; i++, a0 = a0.inf, b0 = b0.inf, c0 = c0.inf) {
                a1 = a0;
                b1 = b0;
                c1 = c0;
                for (int j = 0; j < this.coluna; j++, a1 = a1.dir, b1 = b1.dir, c1 = c1.dir) {
                    c1.elemento = a1.elemento + b1.elemento;
                }
            }
        }
        return resp;
    }

    /*
    * @method multiplica as matrizes dinamias M1 e M2
    * @return valor resultante das multiplicações das matrizes
    */
    public Matriz Multiplicar(Matriz matriz) {
        Matriz resp = new Matriz(this.linha, this.coluna);
        if (this.linha == matriz.linha && this.coluna == matriz.coluna) {
            Celula a = this.inicio;
            Celula i = a;
            Celula b = matriz.inicio;
            Celula j = b;
            Celula manterB = b;
            Celula c = resp.inicio;
            Celula manterC = c;
            Celula aux = c;

            int elemento = 0;
            for (int conti = 0; conti < this.linha; a = a.inf, b = manterB, c = manterC.inf, conti++) {
                manterB = b;
                manterC = c;
                for (int cont = 0; cont < this.coluna; cont++, b = b.dir, c = c.dir) {
                    i = a;
                    j = b;
                    aux = c;
                    elemento = 0;
                    for (/**/; i != null; i = i.dir, j = j.inf) {
                        elemento = elemento + (i.elemento * j.elemento);
                    }
                    aux.elemento = elemento;
                    aux = aux.dir;
                }
            }
        }
        return resp;
    }

    /*
     * Printa Diagonal principal
    */
    public void DiagonalPrincipal() {
        if (isQuadrada()) {
            for (Celula i = inicio; i != null; i = i.inf) {
                MyIO.print(i.elemento + " ");
                if (i.dir != null) {
                    i = i.dir;
                }
            }
            MyIO.println("");
        }
    }

    /*
    * @method imprime a diagonal secundária
    */
    public void DiagonalSecundaria() {
        if (isQuadrada()) {
            Celula i = inicio;
            for (/**/; i.dir != null; i = i.dir)
                ;
            for (/**/; i != null; i = i.inf) {
                MyIO.print(i.elemento + " ");
                if (i.esq != null) {
                    i = i.esq;
                }
            }
            MyIO.println("");
        }
    }

    /*
    * @method Verifica se matriz é quadrada
    * @return true caso linha == coluna
    * @return false faso linha != coluna
    */
    public boolean isQuadrada() {
        return (this.linha == this.coluna);
    }
}

public class Q17 {

    /*
    * @method le as linhas e colunas da matriz
    * @return resp Matriz criada
    */
    public static Matriz lerMatriz() {
        Matriz resp = new Matriz(MyIO.readInt(), MyIO.readInt());

        resp.Set();

        return resp;
    }

    public static void main(String[] args) {
        int numeroTestes = MyIO.readInt();

        for (int i = 0; i < numeroTestes; i++) {
            // lê a primeira Matriz
            Matriz m1 = lerMatriz();
            // lê a segunda Matriz
            Matriz m2 = lerMatriz();

            // Imprime matriz principal
            m1.DiagonalPrincipal();
            // Imprime matriz secundaria
            m1.DiagonalSecundaria();

            // Imprime matriz resultante da soma
            m1.Somar(m2).Imprimir();
            // Imprime matriz resultante da multiplicacao
            m1.Multiplicar(m2).Imprimir();
        }
    }
}