/**
 *
 * @author Rodrigo Richard Gomes
 * @version 1.00 2018/3/16
 * 
 */
public class CPilha {
	private CCelula topo = null;
	private int qtde;

	/**
	 * Fun��o construtora.
	 */
	public CPilha() {
		// nenhum c�digo
	}

	/**
	 * Verifica se a Pilha est� vazia.
	 * 
	 * @return Retorna TRUE se a PILHA estiver vazia e FALSE caso contr�rio.
	 */
	public boolean vazia() {
		return topo == null; // Se o topo for igual a NULL quer dizer que a PILHA está vazia
	}

	/**
	 * Insere o novo item no topo da Pilha
	 * 
	 * @param valorItem: Um Object contendo o item a ser inserido no topo da Pilha
	 */
	public void empilha(Object valorItem) {
		topo = new CCelula(valorItem, topo); // Cria uma nova Celula no topo da pilha e referencia o topo anterior
		qtde++;	// Aumenta a quantidade, pois voce empilhou uma celula na pilha
	}

	/**
	 * Retira e retorna o item do topo da Pilha.
	 * 
	 * @return Retorna um Object contendo o item retirado do topo da Pilha. Caso a
	 *         Pilha esteja vazia retorna null.
	 */
	public Object desempilha() {
		Object item = null;
		if (topo != null) { // Se topo for diferente de null quer dizer que tem celulas na pilha 
			item = topo.item;	// Pega o item que estava no topo e guarda em item
			topo = topo.prox;	// Fala que o topo agora e a celula que estava abaixo do topo
			qtde--;	// Retira um da quantidade para fazer a remocao fisica
		}
		return item;	// Retorna o item removido da pilha
	}

	/**
	 * Verifica se o item passado como par�metro est� contido na lista.
	 * 
	 * @param O par�metro "valorItem" � um object contendo o item a ser localizado.
	 * @return Retorna TRUE caso o item esteja presente na pilha.
	 */
	public boolean contem(Object valorItem) {
		CCelula aux = topo;  // A celula aux e igual ao topo
		while (aux != null) {	// Se esse while for verdadeiro quer dizer que tem item na pilha
			if (aux.item.equals(valorItem))	// Se o item de aux for igual ao procurado retorna true
				return true;
			aux = aux.prox; // Se nao cair no If quer dizer que voce deve pegar a proxima celula sempre de cima para baixo (Pilha)
		}
		return false; // Se nao achar o item retorna false
	}

	/**
	 * Verifica se o item passado como par�metro est� contido na pilha. (Obs: usa o
	 * comando FOR)
	 * 
	 * @param valorItem O par�metro "valorItem" � um object contendo o item a ser
	 *                  localizado.
	 * @return Retorna TRUE caso o item esteja presente na pilha.
	 */
	public boolean contemFor(Object valorItem) {
		for (CCelula aux = topo; aux != null; aux = aux.prox)
			if (aux.item.equals(valorItem))
				return true;
		return false;
	}

	/**
	 * Retorna um Object contendo o item do topo da Pilha.
	 * 
	 * @return Retorna um Object contendo o item do topo da Pilha. Caso a Pilha
	 *         esteja vazia retorna null.
	 */
	public Object peek() {
		return (topo != null) ? topo.item : null; // Se topo for diferente de null quer dizer que tem item na pilha entao retorna o topo, senao vai retornar null
	}

	/**
	 * Imprime os elementos da pilha
	 */
	public void mostra() {
		System.out.println("\ntopo");
		System.out.println("  | ");
		System.out.println("  v ");
		for (CCelula c = topo; c != null; c = c.prox)
			System.out.println("[ " + c.item + " ]");
		System.out.println("  | ");
		System.out.println("  v ");
		System.out.println("null\n");
	}

	/**
	 * M�todo que retorna a quantidade de itens da Pilha. Getter
	 */
	public int quantidade() {
		return qtde; // Retorna a quantidade de itens que tem na Pilha
	}

}