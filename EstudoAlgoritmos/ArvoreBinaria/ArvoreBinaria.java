class ArvoreBinaria {
	private No raiz; // Raiz da arvore.

    public ArvoreBinaria() {
        raiz = null;
    }

    /* INICIO METODO DE PESQUISA */
    public boolean pesquisar(int x) {
        return pesquisar(x, raiz);
    }

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
    public void remover(int x) throws Exception {
        raiz = remover(x, raiz);
    }

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

    /* INICIO METÓDO DE PEGAR A RAIZ */
    public int getRaiz() throws Exception {
        return raiz.elemento;
    }
    /* FIM METÓDO DE PEGAR A RAIZ */

    /*
    * EXERCÍCIOS DE ESTUDO PARA A PROVA 2 METÓDOS PARA UMA ÁRVORE BINÁRIA DE PESQUISA
    */

    /* INICIO METÓDO PEGAR ALTURA DE UM NÓ */
    public int getAlturaNo(int num) {
        return getAlturaNo(raiz, num);
    }

    private int getAlturaNo(No i, int num){
    
        if(i == null) {
            return -1;
        } else {
            if(pesquisar(num) == false) {
                return -1;
            } else {
                No armazena = pesquisarNo(num, i);
                return getAltura(armazena, 0);
            }
        }
    }

    private No pesquisarNo(int x, No i) {
        No pesquisado = raiz;

        if(i != null) {
            if (x == i.elemento) {
                pesquisado = i;
            } else if (x < i.elemento) {
                pesquisado = pesquisarNo(x, i.esq);
            } else {
                pesquisado = pesquisarNo(x, i.dir);
            }
        }

      return pesquisado;
	}
    /* FIM METÓDO PEGAR ALTURA DE UM NÓ */

    /* INICIO METÓDO PEGAR PROFUNDIDADE DE UM NÓ */
    public int getProfundidade(int num) {
        return getProfundidade(raiz, num, 0);
    }

    private int getProfundidade(No i, int num, int nível) {
        int result;

        if(i != null) {
            if(i.elemento == num) {
                return nível;
            } else {
                result = getProfundidade(i.esq, num, nível + 1);

                if(result >= 0) {
                    return result;
                } else {
                    return getProfundidade(i.dir, num, nível + 1);
                }
            }
        } else {
            return -1;
        }
    }
    /* FIM METÓDO PEGAR PROFUNDIDADE DE UM NÓ */

    /* INICIO METÓDO QUE RETORNA A ALTURA DE UMA ABP */
    public int getAltura() {
        return getAltura(raiz, 0);
    }

    public int getAltura(No i, int altura){
        if(i == null){
            altura--;
        } else {
            int alturaEsq = getAltura(i.esq, altura + 1);
            int alturaDir = getAltura(i.dir, altura + 1);

            if(alturaEsq > alturaDir) {
                altura = alturaEsq;
            } else {
                altura = alturaDir;
            }
        }

        return altura;
    }
    /* FIM METÓDO QUE RETORNA A ALTURA DE UMA ABP */

    /* INICIO METÓDO QUE RETORNA A QUANTIDADE DE NÓS FOLHAS EM UMA ÁRVORE */
    public int contarFolhas() {
        return contarFolhas(raiz);
    }

    private int contarFolhas(No i) {
        if(i == null) { 
            return 0;
        } else if(i.esq == null && i.dir == null) {
            return 1;
        }
        return contarFolhas(i.esq) + contarFolhas(i.dir);
    }
    /* FIM METÓDO QUE RETORNA A QUANTIDADE DE NÓS FOLHAS EM UMA ÁRVORE */

    /* INICIO METÓDO VALORES DOS NÓS EM ORDEM DECRESCENTE */
    private void caminharDecrescente(No i) {
        if(i != null) {
            caminharPre(i.dir); // Elementos da direita.
            System.out.print(i.elemento + " "); // Conteudo do no.
            caminharPre(i.esq); // Elementos da esquerda.
        }
	}

    public void mostraDecrescente() {
        caminharDecrescente(raiz);
    }
    /* FIM METÓDO VALORES DOS NÓS EM ORDEM DECRESCENTE */

    /* INICIO METÓDO QUE IMPRIME PARA CADA NÓ PROFUNDIDADE, VALORES DOS FILHOS EM CAMINHAMENTO PRE-ORDEM */
    public void getNoInfo() {
        caminharPre2(raiz);
    }

    private void caminharPre2(No i) {
        if(i != null) {
            getNoInfo(i);
            caminharPre2(i.esq); // Elementos da esquerda.
            caminharPre2(i.dir); // Elementos da direita.
        }
	}

    public void getNoInfo(No i) {
        if(i != null) {
            System.out.println("Elemento = " + i.elemento);
            System.out.println("Profundidade = " + getProfundidade(i.elemento));

            if(i.esq != null) {
                System.out.println("Esquerda = " + i.esq.elemento);
            } else {
                System.out.println("Esquerda = null");
            }

            if(i.dir != null) {
                System.out.println("Direita = " + i.dir.elemento + "\n");
            } else {
                System.out.println("Direita = null\n");
            }
        }
    }
    /* FIM METÓDO QUE IMPRIME PARA CADA NÓ PROFUNDIDADE, VALORES DOS FILHOS EM CAMINHAMENTO PRE-ORDEM */

    /* INICIO METÓDO QUE RETORNA MENOR ELEMENTO DA ÁRVORE */
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
    /* FIM METÓDO QUE RETORNA MENOR ELEMENTO DA ÁRVORE */

    /* INICIO METÓDO QUE RETORNA SEGUNDO MENOR ELEMENTO DA ÁRVORE */
    public int getSegundoMenor() {
        int resp = -1;

        if(raiz != null) {
            No pai = raiz;
            No i;

            for(i = raiz; i.esq != null; i = i.esq) {
                if(i.esq.elemento > getMenor()) {
                    pai = i.esq;
                }
            }

            resp = pai.elemento;
        }

        return resp;
    }
    /* FIM METÓDO QUE RETORNA SEGUNDO MENOR ELEMENTO DA ÁRVORE */

    /* INICIO METÓDO QUE RETORNA MAIOR ELEMENTO DA ÁRVORE */
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
    /* FIM METÓDO QUE RETORNA MAIOR ELEMENTO DA ÁRVORE */

    /* INICIO METÓDO QUE SOMA OS NÓS DE UMA ÁRVORE */
    public int soma() {
        return soma(raiz);
    }
  
    private int soma(No i) {
        int resp = 0;

        if(i != null){
            resp = i.elemento + soma(i.esq) + soma(i.dir);
        }

        return resp;
    }
    /* FIM METÓDO QUE SOMA OS NÓS DE UMA ÁRVORE */

    /* INICIO METÓDO QUE IMPRIMA OS NÓS-FOLHA DA ESQUERDA PARA A DIREITA */
    public void imprimeFolhasEsqDir() {
        imprimeFolhasEsqDir(raiz);
    }

    private void imprimeFolhasEsqDir(No i) {
        if(i != null) { 
            if(i.esq == null && i.dir == null) {
                System.out.println(i.elemento);
            } else {
                imprimeFolhasEsqDir(i.esq);
                imprimeFolhasEsqDir(i.dir);
            }
        }
    }
    /* FIM METÓDO QUE IMPRIMA OS NÓS-FOLHA DA ESQUERDA PARA A DIREITA */

    /* INICIO METÓDO QUE IMPRIMA OS NÓS-FOLHA DA DIREITA PARA A ESQUERDA */
    public void imprimeFolhasDirEsq() {
        imprimeFolhasDirEsq(raiz);
    }

    private void imprimeFolhasDirEsq(No i) {
        if(i != null) { 
            if(i.esq == null && i.dir == null) {
                System.out.println(i.elemento);
            } else {
                imprimeFolhasDirEsq(i.dir);
                imprimeFolhasDirEsq(i.esq);
            }
        }
    }
    /* FIM METÓDO QUE IMPRIMA OS NÓS-FOLHA DA DIREITA PARA A ESQUERDA */

    /* INICIO METÓDO QUE RETORNA A QUANTIDADE DE NÓS NA ÁRVORE */
    public int contarNos() {
        return contarNos(raiz);
    }

    private int contarNos(No i){
        if(i == null) {
            return 0;
        } else {
            return 1 + contarNos(i.esq) + contarNos(i.dir);
        }
     }
    /* FIM METÓDO QUE RETORNA A QUANTIDADE DE NÓS NA ÁRVORE */

}