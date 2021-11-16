class ArvoreBinaria {
	private No raiz; // Raiz da arvore.

    public ArvoreBinaria() {
        raiz = null;
    }

    /* INICIO METODO DE PESQUISA */
    /**
    * Metodo publico iterativo para pesquisar elemento.
    * @param x Elemento que sera procurado.
    * @return <code>true</code> se o elemento existir,
    * <code>false</code> em caso contrario.
    */
    public boolean pesquisar(int x) {
        return pesquisar(x, raiz);
    }

    /**
    * Metodo privado recursivo para pesquisar elemento.
    * @param x Elemento que sera procurado.
    * @param i No em analise.
    * @return <code>true</code> se o elemento existir,
    * <code>false</code> em caso contrario.
    */
	private boolean pesquisar(int x, No i) {
        boolean resp;

        if (i == null) {
            resp = false;
        } else if (x == i.elemento) {
            resp = true;
        } else if (x < i.elemento) {
            resp = pesquisar(x, i.esq);
        } else {
            resp = pesquisar(x, i.dir);
        }

      return resp;
	}
    /* FIM METÓDO DE PESQUISA */

    /* INICIO METÓDOS DE CAMINHAMENTO */
	private void caminharCentral(No i) {
        if(i != null) {
            caminharCentral(i.esq); // Elementos da esquerda.
            System.out.print(i.elemento + " "); // Conteudo do no.
            caminharCentral(i.dir); // Elementos da direita.
        }
	}

	private void caminharPre(No i) {
        if(i != null) {
            System.out.print(i.elemento + " "); // Conteudo do no.
            caminharPre(i.esq); // Elementos da esquerda.
            caminharPre(i.dir); // Elementos da direita.
        }
	}

	private void caminharPos(No i) {
        if(i != null) {
            caminharPos(i.esq); // Elementos da esquerda.
            caminharPos(i.dir); // Elementos da direita.
            System.out.print(i.elemento + " "); // Conteudo do no.
        }
	}
    /* FIM METÓDOS DE CAMINHAMENTO */

    /* INICIO METÓDO DE INSERÇÃO SEM PAI */
	public void inserir(int x) throws Exception {
		raiz = inserir(x, raiz);
	}

	private No inserir(int x, No i) throws Exception {
        if (i == null) {
            i = new No(x);
        } else if (x < i.elemento) {
            i.esq = inserir(x, i.esq);
        } else if (x > i.elemento) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Erro ao inserir!");
        }

	    return i;
	}
    /* FIM METÓDO DE INSERÇÃO SEM PAI */

    /* INICIO METÓDO DE INSERÇÃO COM PAI */
	public void inserirPai(int x) throws Exception {
        if(raiz == null){
            raiz = new No(x);
        } else if(x < raiz.elemento){
		    inserirPai(x, raiz.esq, raiz);
        } else if(x > raiz.elemento){
		    inserirPai(x, raiz.dir, raiz);
        } else {
            throw new Exception("Erro ao inserirPai!");
        }
	}

	private void inserirPai(int x, No i, No pai) throws Exception {
		if (i == null) {
         if(x < pai.elemento){
            pai.esq = new No(x);
         } else {
            pai.dir = new No(x);
         }
      } else if (x < i.elemento) {
         inserirPai(x, i.esq, i);
      } else if (x > i.elemento) {
         inserirPai(x, i.dir, i);
      } else {
         throw new Exception("Erro ao inserirPai!");
      }
	}
    /* FIM METÓDO DE INSERÇÃO COM PAI */

    /* INICIO METÓDO DE REMOÇÃO SEM PAI */
    /**
    * Metodo publico iterativo para remover elemento.
    * @param x Elemento a ser removido.
    * @throws Exception Se nao encontrar elemento.
    */
    public void remover(int x) throws Exception {
        raiz = remover(x, raiz);
    }

    /** 
    * Metodo privado recursivo para remover elemento.
    * @param x Elemento a ser removido.
    * @param i No em analise.
    * @return No em analise, alterado ou nao.
    * @throws Exception Se nao encontrar elemento.
    */
    private No remover(int x, No i) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao remover!");
        } else if (x < i.elemento) {
            i.esq = remover(x, i.esq);
        } else if (x > i.elemento) {
            i.dir = remover(x, i.dir);
        // Sem no a direita.
        } else if (i.dir == null) {
            i = i.esq;
        // Sem no a esquerda.
        } else if (i.esq == null) {
            i = i.dir;
        // No a esquerda e no a direita.
        } else {
            i.esq = maiorEsq(i, i.esq);
        }

        return i;
    }

	/**
	* Metodo para trocar o elemento "removido" pelo maior da esquerda, usado tanto no sem pai, como no com pai.
	* @param i No que teve o elemento removido.
	* @param j No da subarvore esquerda.
	* @return No em analise, alterado ou nao.
	*/
	private No maiorEsq(No i, No j) {
        // Encontrou o maximo da subarvore esquerda.
        if (j.dir == null) {
            i.elemento = j.elemento; // Substitui i por j.
            j = j.esq; // Substitui j por j.ESQ.
        // Existe no a direita.
        } else {
            // Caminha para direita.
            j.dir = maiorEsq(i, j.dir);
        }

        return j;
    }
    /* FIM METÓDO DE REMOÇÃO SEM PAI */

    /* INICIO METÓDO DE REMOÇÃO COM PAI */
	public void remover2(int x) throws Exception {
      if (raiz == null) {
         throw new Exception("Erro ao remover2!");
      } else if(x < raiz.elemento){
         remover2(x, raiz.esq, raiz);
      } else if (x > raiz.elemento){
         remover2(x, raiz.dir, raiz);
      } else if (raiz.dir == null) {
         raiz = raiz.esq;
      } else if (raiz.esq == null) {
         raiz = raiz.dir;
      } else {
         raiz.esq = maiorEsq(raiz, raiz.esq);
      }
   }

	/**
	 * Metodo privado recursivo para remover elemento.
	 * @param x Elemento a ser removido.
	 * @param i No em analise.
	 * @param pai do No em analise.
	 * @throws Exception Se nao encontrar elemento.
	 */
	private void remover2(int x, No i, No pai) throws Exception {
	    if (i == null) {
            throw new Exception("Erro ao remover2!");
        } else if (x < i.elemento) {
            remover2(x, i.esq, i);
        } else if (x > i.elemento) {
            remover2(x, i.dir, i);
        } else if (i.dir == null) {
            pai = i.esq;
        } else if (i.esq == null) {
            pai = i.dir;
        } else {
            i.esq = maiorEsq(i, i.esq);
        }
    }
    /* FIM METÓDO DE REMOÇÃO SEM PAI */

    /* INICIO METÓDOS DE PEGAR MAIOR E MENOR ELEMENTOS */
    /**
    * Metodo que retorna o maior elemento da árvore
    * @return int maior elemento da árvore
    */
    public int getMaior() {
        int resp = -1;

        // Para pegar o maior basta ir para o elemento mais a direita
        if(raiz != null) {
            No i;
            for(i = raiz; i.dir != null; i = i.dir);
            resp = i.elemento;
        }

        return resp;
    }

    /**
   * Metodo que retorna o menor elemento da árvore
   * @return int menor elemento da árvore
   */
    public int getMenor() {
        int resp = -1;

        // Para pegar o menor basta ir para o elemento mais a esquerda
        if(raiz != null){
            No i;
            for(i = raiz; i.esq != null; i = i.esq);
            resp = i.elemento;
        }

        return resp;
    }
    /* FIM METÓDOS DE PEGAR MAIOR E MENOR ELEMENTOS */

    /* INICIO METÓDO DE PEGAR ALTURA DA ÁRVORE */
    /**
   * Metodo que retorna a altura da árvore
   * @return int altura da árvore
   */
    public int getAltura() {
        return getAltura(raiz, 0);
    }

    /**
   * Metodo que retorna a altura da árvore
   * @return int altura da árvore
   */
    public int getAltura(No i, int altura){
        if(i == null){
            altura--;
        } else {
            int alturaEsq = getAltura(i.esq, altura + 1);
            int alturaDir = getAltura(i.dir, altura + 1);
            altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
        }

        return altura;
    }
    /* FIM METÓDO DE PEGAR ALTURA DA ÁRVORE */

    /* INICIO METÓDO DE PEGAR A RAIZ */
    public int getRaiz() throws Exception {
        return raiz.elemento;
    }
    /*FIM METÓDO DE PEGAR A RAIZ */
}