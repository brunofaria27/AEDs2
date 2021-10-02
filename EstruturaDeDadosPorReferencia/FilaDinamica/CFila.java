/**
 * 
 * @author Rodrigo Richard Gomes
 * @version 1.00 2018/3/16
 * 
 */
public class CFila {
	private CCelula frente; // Celula cabeca.
	private CCelula tras; // Ultima celula.
	private int qtde;

	/**
	 * Fun��o construtora. Cria a c�lula cabe�a e faz as refer�ncias frente e tras
	 * apontarem para ela.
	 */
	public CFila() {
		frente = new CCelula(); 	// Inicializa uma célula cabeça referenciada pela frente e pelo tras
		tras = frente;
	}

	/**
	 * Verifica se a fila est� vazia.
	 * 
	 * @return Retorna TRUE se a fila estiver vazia e FALSE caso contr�rio.
	 */
	public boolean vazia() {
		return frente == tras;	// Se frente for igual o tras quer dizer que a fila está vazia porque so tem a célula cabeça
	}

	/**
	 * Imprime os elementos da fila
	 */
	public void mostra() {
		System.out.print("[ ");
		for (CCelula c = frente.prox; c != null; c = c.prox) // c vai ser igual o primeira referencia, se c for diferente de null vai continuar no for (pois a celula final sempre aponta para NULL)
			System.out.print(c.item + " ");					 // para incrementar ele pega a proxima referencia 
		System.out.println("] ");
	}

	/**
	 * Insere um novo Item no fim da fila.
	 * 
	 * @param valorItem O parametro "valorItem" � um Object contendo o elemento a
	 *                  ser inserido no final da fila.
	 */
	public void enfileira(Object valorItem) {
		tras.prox = new CCelula(valorItem);	// cria uma celular depois da ultuma e adiciona o valorItem que deve ser adicionado
		tras = tras.prox;	// referencia o tras como a ultima celula criada
		qtde++;	// aumenta o tamanho da fila, pois voce enfileirou um item a mais
	}

	/**
	 * Retira e retorna o primeiro elemento da fila.
	 * 
	 * @return Retorna um Object contendo o primeiro elemento da fila. Caso a fila
	 *         esteja vazia retorna null.
	 */
	public Object desenfileira() {
		Object item = null;
		if (frente != tras) {	// Se frente for diferente de tras quer dizer que a fila nao esta vazia
			frente = frente.prox;	// pega a referencia da celula cabeca
			item = frente.item;		// Object item guarda o dado armazenado na celula que a celula cabeca referencia
			qtde--;	// diminui a quantidade de itens para remocao fisica
		}
		return item;
	}

	// Id�ntico ao m�todo anterior, mas sem usar a vari�vel tempor�ria item
	public Object desenfileirav2() {
		if (frente != tras) {
			frente = frente.prox;
			qtde--;
			return frente.item;
		}
		return null;
	}

	/**
	 * Retorna o primeiro Item da fila sem remove-lo.
	 * 
	 * @return Retorna um Object contendo o primeiro Item da fila.
	 */
	public Object peek() {
		if (frente != tras)
			return frente.prox.item;
		else
			return null;
		// return (frente != tras)? frente.prox.item : null;
	}

	/**
	 * Verifica se o Item passado como parametro esta contido na fila.
	 * 
	 * @param valorItem O parametro "valorItem" e um object contendo o Item a ser
	 *                  localizado.
	 * @return O m�todo retorna TRUE caso o item esteja presente na fila.
	 */
	public boolean contem(Object valorItem) {
		CCelula aux = frente.prox;	// celula auxiliar que serve para pegar o primeiro item da fila 
		while (aux != null) { // Saber se a fila contem itens 
			if (aux.item.equals(valorItem))	// ver se a variavel auxiliar que tem o item e igual ao valor procurado
				return true;	// se for igual retorna true
			aux = aux.prox;	// se nap for igual incrementa para a proxima referencia
		}
		return false;	// Se nao contem na fila, retorna false
	}

	/**
	 * Verifica se o Item passado como parametro esta contido na fila. (Obs: usa o
	 * comando FOR)
	 * 
	 * @param (valorItem) Recebe como parametro um object contendo o Item a ser
	 *                    localizado.
	 * @return Retorna TRUE caso o Item esteja presente na fila.
	 */
	public boolean contemFor(Object valorItem) {
		for (CCelula aux = frente.prox; aux != null; aux = aux.prox) // Celula aux pega a referencia do primeiro item da fila, enquanto aux != NULL (pois a ultima celula referencia NULL) vai pegar a proxima referencia da fila
			if (aux.item.equals(valorItem))	// ver se o item que eles procuram esta na fila
				return true;	// se estiver na fila retorna true
		return false;	// se nao estiver na fila retorn false
	}

	/**
	 * Metodo que retorna a quantidade de itens da fila.
	 * 
	 * @return
	 */
	public int quantidade() {
		return qtde;	// retorna a quantidade de itens que tem na fila para uma consulta, por exemplo!
	}

}