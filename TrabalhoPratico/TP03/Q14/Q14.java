import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;

class Serie {
    private String nome;
    private String formato;
    private String duracao;
    private String paisOrigem;
    private String idioma;
    private String emissoraOriginal;
    private String transmissaoOriginal;
    private int numeroTemporadas;
    private int numeroEpisodios;

    Serie() {
        this.nome = "";
        this.formato = "";
        this.duracao = "";
        this.paisOrigem = "";
        this.idioma = "";
        this.emissoraOriginal = "";
        this.transmissaoOriginal = "";
        this.numeroTemporadas = 0;
        this.numeroEpisodios = 0;
    }

    Serie(String nome, String formato, String duracao, String paisOrigem, String idioma, String emissoraOriginal,
            String transmissaoOriginal, int numeroTemporadas, int numeroEpisodios) {
        this.nome = nome;
        this.formato = formato;
        this.duracao = duracao;
        this.paisOrigem = paisOrigem;
        this.idioma = idioma;
        this.emissoraOriginal = emissoraOriginal;
        this.transmissaoOriginal = transmissaoOriginal;
        this.numeroTemporadas = numeroTemporadas;
        this.numeroEpisodios = numeroEpisodios;
    }

    public Serie Clone() {
        Serie clone = new Serie();
        clone.nome = this.nome;
        clone.formato = this.formato;
        clone.duracao = this.duracao;
        clone.paisOrigem = this.paisOrigem;
        clone.idioma = this.idioma;
        clone.emissoraOriginal = this.emissoraOriginal;
        clone.transmissaoOriginal = this.transmissaoOriginal;
        clone.numeroTemporadas = this.numeroTemporadas;
        clone.numeroEpisodios = this.numeroEpisodios;

        return clone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getEmissoraOriginal() {
        return emissoraOriginal;
    }

    public void setEmissoraOriginal(String emissoraOriginal) {
        this.emissoraOriginal = emissoraOriginal;
    }

    public String getTransmissaoOriginal() {
        return transmissaoOriginal;
    }

    public void setTransmissaoOriginal(String transmissaoOriginal) {
        this.transmissaoOriginal = transmissaoOriginal;
    }

    public int getNumeroTemporadas() {
        return numeroTemporadas;
    }

    public void setNumeroTemporadas(int numeroTemporadas) {
        this.numeroTemporadas = numeroTemporadas;
    }

    public int getNumeroEpisodios() {
        return numeroEpisodios;
    }

    public void setNumeroEpisodios(int numeroEpisodios) {
        this.numeroEpisodios = numeroEpisodios;
    }

    String removeTags(String line) {
        String newLine = "";

        int i = 0;
        while (i < line.length()) {
            if (line.charAt(i) == '<') {
                i++;
                while (line.charAt(i) != '>') {
                    i++;
                }
            } else {
                newLine += line.charAt(i);
            }
            i++;
        }

        return newLine;
    }

    public void ler(String nomeArquivo) throws Exception {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(nomeArquivo));
        BufferedReader br = new BufferedReader(isr);

        int posPonto = 0;
        for (int i = 0; i < nomeArquivo.length(); i++) {
            if (nomeArquivo.charAt(i) == '.') {
                posPonto = i;
            }
        }
        String nome = nomeArquivo.substring(12, posPonto);
        nome = nome.replace('_', ' ');
        this.nome = nome;

        while (!br.readLine().contains("Formato"))
            ;
        this.formato = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while (!br.readLine().contains("Dura????o"))
            ;
        this.duracao = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while (!br.readLine().contains("Pa??s de origem"))
            ;
        this.paisOrigem = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while (!br.readLine().contains("Idioma original"))
            ;
        this.idioma = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while (!br.readLine().contains("Emissora de televis??o original"))
            ;
        this.emissoraOriginal = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while (!br.readLine().contains("Transmiss??o original"))
            ;
        this.transmissaoOriginal = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while (!br.readLine().contains("N.?? de temporadas"))
            ;
        String numTe = removeTags(br.readLine()).trim();
        String guardaNum = "";
        for (int i = 0; i < numTe.length(); i++) {
            if (numTe.charAt(i) == '0' || numTe.charAt(i) == '1' || numTe.charAt(i) == '2' || numTe.charAt(i) == '3'
                    || numTe.charAt(i) == '4' || numTe.charAt(i) == '5' || numTe.charAt(i) == '6'
                    || numTe.charAt(i) == '7' || numTe.charAt(i) == '8' || numTe.charAt(i) == '9') {
                guardaNum = guardaNum + numTe.charAt(i);
            } else if (numTe.charAt(i) == '(' || numTe.charAt(i) == '-' || numTe.charAt(i) == '+'
                    || numTe.charAt(i) == ' ') {
                break;
            }
        }
        int num = Integer.parseInt(guardaNum);
        this.numeroTemporadas = num;

        while (!br.readLine().contains("N.?? de epis??dios"))
            ;
        String numEp = removeTags(br.readLine()).trim();
        guardaNum = "";
        for (int i = 0; i < numEp.length(); i++) {
            if (numEp.charAt(i) == '0' || numEp.charAt(i) == '1' || numEp.charAt(i) == '2' || numEp.charAt(i) == '3'
                    || numEp.charAt(i) == '4' || numEp.charAt(i) == '5' || numEp.charAt(i) == '6'
                    || numEp.charAt(i) == '7' || numEp.charAt(i) == '8' || numEp.charAt(i) == '9') {
                guardaNum = guardaNum + numEp.charAt(i);
            } else if (numEp.charAt(i) == '(' || numEp.charAt(i) == '-' || numEp.charAt(i) == '+'
                    || numEp.charAt(i) == ' ') {
                break;
            }
        }
        num = Integer.parseInt(guardaNum);
        this.numeroEpisodios = num;

        br.close();
        // System.out.println(this.toString());
    }

    public String toString() {
        return nome + " " + formato + " " + duracao + " " + paisOrigem + " " + idioma + " " + emissoraOriginal + " "
                + transmissaoOriginal + " " + numeroTemporadas + " " + numeroEpisodios;
    }
}

class CCelulaDup {
	public Serie item;
	public CCelulaDup ant;
	public CCelulaDup prox;

	public CCelulaDup() {
		item = null;
		ant = null;
		prox = null;
	}

	public CCelulaDup(Serie valorItem) {
		item = valorItem.Clone();
		ant = null;
		prox = null;
	}

	public CCelulaDup(Serie valorItem, CCelulaDup celulaAnt, CCelulaDup proxCelula) {
		item = valorItem.Clone();
		ant = celulaAnt;
		prox = proxCelula;
	}
}

class CListaDup {
	private CCelulaDup primeira;
	private CCelulaDup ultima;
	private int qtde;
    private int comparacoes = 0;

	public CListaDup() {
		primeira = new CCelulaDup();
		ultima = primeira;
	}

	public boolean vazia() {
		return primeira == ultima;
	}

	public void insereFim(Serie valorItem) {
		ultima.prox = new CCelulaDup(valorItem, ultima, null);
		ultima = ultima.prox;
		qtde++;
	}

	public void insereComeco(Serie valorItem) {
		if (primeira == ultima) {
			ultima.prox = new CCelulaDup(valorItem, ultima, null);
			ultima = ultima.prox;
		} else {
			primeira.prox = new CCelulaDup(valorItem, primeira, primeira.prox);
			primeira.prox.prox.ant = primeira.prox;
		}
		qtde++;
	}

	public void mostra() {
		CCelulaDup aux = primeira.prox;
		while (aux != null) {
			System.out.println(aux.item);
			aux = aux.prox;
		}
	}

	public int quantidade() {
		return qtde;
	}

    public int pegarPosicao(CCelulaDup posicao) {
        CCelulaDup i = primeira.prox;
        int contador = 0;

        for(i = primeira.prox; i.item.getPaisOrigem() != posicao.item.getPaisOrigem(); i = i.prox, contador++);

        return contador;
    }

    public Serie getSeriePos(int pos) {
        CCelulaDup i = primeira.prox;
        for(int j = 0; j < pos; i = i.prox, j++);
        return i.item;
    }

    public CCelulaDup getCelulaPos(int pos) {
        CCelulaDup i = primeira.prox;
        for(int j = 0; j < pos; i = i.prox, j++);
        return i;
    }

    public void swap(CCelulaDup i, CCelulaDup j) {
        Serie tmp = i.item;
        i.item = j.item;
        j.item = tmp;
    }

    public void quicksort() {
        int n = quantidade();
        quicksortRec(0 , n - 1, getSeriePos((0 + (n - 1)) / 2) );
    }

    public void quicksortRec(int esq, int dir, Serie pivo) {
        int i = esq;
        int j = dir;

        while(i <= j) {
            while(getSeriePos(i).getPaisOrigem().compareTo(pivo.getPaisOrigem()) < 0 || (getSeriePos(i).getPaisOrigem().equals(pivo.getPaisOrigem()) && getSeriePos(i).getNome().compareTo(pivo.getNome()) < 0)) {
                i++;
                comparacoes += 3;
            }

            while(getSeriePos(j).getPaisOrigem().compareTo(pivo.getPaisOrigem()) > 0 || (getSeriePos(j).getPaisOrigem().equals(pivo.getPaisOrigem()) && getSeriePos(j).getNome().compareTo(pivo.getNome()) > 0)) {
                j--;
                comparacoes += 3;
            }

            if(i <= j) {
                swap(getCelulaPos(i), getCelulaPos(j));
                i++;
                j--;
            }
        }

        if(esq < j) {
            quicksortRec(esq, j, getSeriePos((dir + esq) / 2));
        }

        if(i < dir) {
            quicksortRec(i, dir, getSeriePos((dir + esq) / 2));
        }

    }

    public int getComparacoes() {
        return comparacoes;
    }

}

class Q14 {
    public static Serie lerDados(String entrada) throws Exception {
        Serie serie = new Serie(); 
        String arquivo = "";

        arquivo = "/tmp/series/";
        arquivo += entrada; 

        serie = new Serie();
        serie.ler(arquivo);

        return serie;
    }

    public static long now() {
        return new Date().getTime();
    }

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        String[] entrada = new String[1000];
        CListaDup listaDupla = new CListaDup();
        int numEntrada = 0;

        // Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; // Desconsiderar a palavra FIM

        for(int i = 0; i < numEntrada; i++) {
            listaDupla.insereFim(lerDados(entrada[i]));
        }


        long inicio = now();
        listaDupla.quicksort();
        long fim = now();

        listaDupla.mostra();

        double tempo = (fim - inicio) / 1000.0;

        Arq.openWrite("matricula_quicksort2.txt", "UTF-8");
        Arq.print("Matricula : 742238 \t");
        Arq.print("Tempo de execu????o : " + tempo + "s \t");
        Arq.print("Numero de Compara??oes : " + listaDupla.getComparacoes());
        Arq.close();

    }
}