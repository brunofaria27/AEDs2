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

    Serie(String nome, String formato, String duracao, String paisOrigem, String idioma,
            String emissoraOriginal, String transmissaoOriginal, int numeroTemporadas, int numeroEpisodios) {
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

    public Serie Clone(){
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
        while(i < line.length()) {
            if(line.charAt(i) == '<') {
                i++;
                while(line.charAt(i) != '>') {
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
        for(int i = 0; i < nomeArquivo.length(); i++) {
            if(nomeArquivo.charAt(i) == '.') {
                posPonto = i;
            }
        }
        String nome = nomeArquivo.substring(12, posPonto);
        nome = nome.replace('_', ' ');
        this.nome = nome.trim();

        while(!br.readLine().contains("Formato"));
        this.formato = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while(!br.readLine().contains("Dura????o"));
        this.duracao = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while(!br.readLine().contains("Pa??s de origem"));
        this.paisOrigem = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while(!br.readLine().contains("Idioma original"));
        this.idioma = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while(!br.readLine().contains("Emissora de televis??o original"));
        this.emissoraOriginal = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while(!br.readLine().contains("Transmiss??o original"));
        this.transmissaoOriginal = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while(!br.readLine().contains("N.?? de temporadas"));
        String numTe = removeTags(br.readLine()).trim();
        String guardaNum = "";
        for(int i = 0; i < numTe.length(); i++) {
            if(numTe.charAt(i) == '0' || numTe.charAt(i) == '1' || numTe.charAt(i) == '2' || numTe.charAt(i) == '3' || numTe.charAt(i) == '4' ||
            numTe.charAt(i) == '5' || numTe.charAt(i) == '6' || numTe.charAt(i) == '7' || numTe.charAt(i) == '8' || numTe.charAt(i) == '9') {
                guardaNum = guardaNum + numTe.charAt(i);
            } else if(numTe.charAt(i) == '(' || numTe.charAt(i) == '-' || numTe.charAt(i) == '+' || numTe.charAt(i) == ' ') {
                break;
            }
        }
        int num = Integer.parseInt(guardaNum);
        this.numeroTemporadas = num;

        while(!br.readLine().contains("N.?? de epis??dios"));
        String numEp = removeTags(br.readLine()).trim();
        guardaNum = "";
        for(int i = 0; i < numEp.length(); i++) {
            if(numEp.charAt(i) == '0' || numEp.charAt(i) == '1' || numEp.charAt(i) == '2' || numEp.charAt(i) == '3' || numEp.charAt(i) == '4' ||
            numEp.charAt(i) == '5' || numEp.charAt(i) == '6' || numEp.charAt(i) == '7' || numEp.charAt(i) == '8' || numEp.charAt(i) == '9') {
                guardaNum = guardaNum + numEp.charAt(i);
            } else if(numEp.charAt(i) == '(' || numEp.charAt(i) == '-' || numEp.charAt(i) == '+' || numEp.charAt(i) == ' ') {
                break;
            }
        }
        num = Integer.parseInt(guardaNum);
        this.numeroEpisodios = num;

        br.close();
//        System.out.println(this.toString());
    }

    public String toString() {
        return nome + " " + formato + " " + duracao + " " + paisOrigem + " " + idioma + " " + emissoraOriginal + " "
                + transmissaoOriginal + " " + numeroTemporadas + " " + numeroEpisodios;
    }
}

class No {
    public Serie elemento; 
    public No esq, dir; 

    public No(Serie elemento){
        this(elemento, null, null);
    }

    public No(Serie elemento, No esq, No dir){
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreBinaria {
    private No raiz;
    public String caminho = ""; // Printar o caminho da pesquisa
    public int comparacoes = 0;

    public ArvoreBinaria() {
        raiz = null;
    }

    /* Inicio met??do de Inserir */
    public void inserir(Serie series) throws Exception {
        raiz = inserir(series, raiz);
    }

    private No inserir(Serie series, No i) throws Exception {
        if (i == null) {
            i = new No(series);
        } else if (series.getNome().compareTo(i.elemento.getNome()) < 0) {
            i.esq = inserir(series, i.esq);
        } else if (series.getNome().compareTo(i.elemento.getNome()) > 0) {
            i.dir = inserir(series, i.dir);
        } else {
            throw new Exception("Erro ao inserir!");
        }
        return i;
    }
    /* Fim met??do de Inserir */

    /* Inicio met??do de remo????o */
    public void remover(Serie series) throws Exception {
        raiz = remover(series, raiz);
    }

    private No remover(Serie series, No i) throws Exception {
        if (i == null) {
            return i;   // retornando para nao acabar o programa caso nao tenha o que remover
        } else if (series.getNome().compareTo(i.elemento.getNome()) < 0) {
            i.esq = remover(series, i.esq);
        } else if (series.getNome().compareTo(i.elemento.getNome()) > 0) {
            i.dir = remover(series, i.dir);
        } else if (i.dir == null) {
            i = i.esq;
        } else if (i.esq == null) {
            i = i.dir;
        } else {
            i.esq = maiorEsq(i, i.esq);
        }

        return i;
    }

    private No maiorEsq(No i, No j) {
        if(j.dir == null) {
            i.elemento = j.elemento;
            j = j.esq;
        } else {
            j.dir = maiorEsq(i, j.dir);
        }

        return j;
    }
    /* Fim met??do de remo????o */

    /* Inicio remover 2 */
    public void remover2(Serie series) throws Exception {
        if (raiz == null) {
            throw new Exception("Erro ao remover2!");
        } else if(series.getNome().compareTo(raiz.elemento.getNome()) < 0) {
            remover2(series, raiz.esq, raiz);
        } else if (series.getNome().compareTo(raiz.elemento.getNome()) > 0) {
            remover2(series, raiz.dir, raiz);
        } else if (raiz.dir == null) {
            raiz = raiz.esq;
        } else if (raiz.esq == null) {
            raiz = raiz.dir;
        } else {
            raiz.esq = maiorEsq(raiz, raiz.esq);
        }
    }

    private void remover2(Serie series, No i, No pai) throws Exception {
        if (i == null) {
            throw new Exception("Erro ao remover2!");
        } else if (series.getNome().compareTo(i.elemento.getNome()) < 0) {
            remover2(series, i.esq, i);
        } else if (series.getNome().compareTo(i.elemento.getNome()) > 0) {
            remover2(series, i.dir, i);
        } else if (i.dir == null) {
            pai = i.esq;
        } else if (i.esq == null) {
            pai = i.dir;
        } else {
            i.esq = maiorEsq(i, i.esq);
        }
    }
    /* Fim remover 2 */

    /* Inicio met??dos de caminhar */
    public void caminharCentral(No i) {
        if(i != null) {
            caminharCentral(i.esq);
            System.out.println(i.elemento);
            caminharCentral(i.dir);
        }
    }

    public void caminharPre(No i) {
        if(i != null) {
            System.out.println(i.elemento);
            caminharPre(i.esq);
            caminharCentral(i.dir);
        }
    }

    public void caminharPos(No i) {
        if(i != null) {
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.println(i.elemento);
        }
    }
    /* Fim met??dos de caminhar */

    /* Inicio met??dos de pesquisa */
    public boolean pesquisar(String x){
        caminho += "raiz ";
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(String x, No i) { 
        boolean resp;
        if(i == null) {
            resp = false;
        } else if(x.equals(i.elemento.getNome())) {
            comparacoes++;
            resp = true;
        } else if(x.compareTo(i.elemento.getNome()) < 0) {
            comparacoes++;
            caminho += "esq ";
            resp = pesquisar(x, i.esq);
        } else {
            comparacoes++;
            caminho += "dir ";
            resp = pesquisar(x, i.dir);
        }

        return resp;
    }

    /* Fim met??dos de pesquisa */

    public Serie getRaiz() throws Exception {
        return raiz.elemento;
    }

    public void mostrar() {
        caminharCentral(raiz);
    }
}

class Q01 {
    public static Serie lerDados(String entrada) throws Exception {
        Serie serie = new Serie(); 
        String arquivo = "";

        arquivo = "/tmp/series/";
        arquivo += entrada; 

        serie = new Serie();
        serie.ler(arquivo);

        return serie;
    }

    public static Serie[] lerDados(String[] entrada, int numEntrada) throws Exception {
        Serie[] serie = new Serie[numEntrada]; 
        String[] arquivo = new String[100];

        for(int i = 0; i < numEntrada; i++) {
            arquivo[i] = "/tmp/series/";
            arquivo[i] += entrada[i]; 
        }

        for(int i = 0; i < numEntrada; i++){
            serie[i] = new Serie();
            serie[i].ler(arquivo[i]);
        }

       return serie;
    }

    public static long now() {
        return new Date().getTime();
    }  

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String pesquisaNomes(ArvoreBinaria arvore, String seriesPesquisa) {
        String resp = "";

        if(arvore.pesquisar(seriesPesquisa) == true) {
            resp += arvore.caminho + "SIM";
        } else {
            resp += arvore.caminho + "NAO";
        }

        arvore.caminho = "";
        return resp;
    }

    public static void tratarComando(String seriesEntrada, ArvoreBinaria arvore) throws Exception {
        String[] aux = new String[2];

        if(seriesEntrada.charAt(0) == 'I') {
            aux = seriesEntrada.split(" ");
            arvore.inserir(lerDados(aux[1]));
        } else if(seriesEntrada.charAt(0) == 'R') {
            aux = seriesEntrada.split(" ");
            if(aux[1].contains("Jessica") || aux[1].contains("Black")) {
                aux = seriesEntrada.split(" ");
                String resp = aux[1] + "_" + aux[2] + ".html";
                arvore.remover(lerDados(resp));
            } else {
                String resp = aux[1] + ".html";
                arvore.remover(lerDados(resp));
            }
        } 
    }

    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        ArvoreBinaria arvore = new ArvoreBinaria();
        String[] entrada = new String[1000];
        int numEntrada = 0;

        // Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; // Desconsiderar a palavra FIM

        Serie[] series = lerDados(entrada, numEntrada);

        for(int i = 0; i < numEntrada; i++){
            arvore.inserir(series[i]);
        }

        // Inser????es e remo????es na ??rvore
        int quantidade = MyIO.readInt();
        int contador = 0;
        String[] seriesEntrada = new String[quantidade];

        long inicio = now();
        do {
            seriesEntrada[contador] = MyIO.readLine();
            tratarComando(seriesEntrada[contador], arvore);
            contador++;
        } while (contador < quantidade);

        // Nomes a serem pesquisados
        String[] pesquisa = new String[1000];
        int numPesquisa = 0;
        
        do {
            pesquisa[numPesquisa] = MyIO.readLine();
        } while (isFim(pesquisa[numPesquisa++]) == false);
        numPesquisa--; // Desconsiderar a palavra FIM
        
        for(int i = 0; i < numPesquisa; i++){
            MyIO.println(pesquisaNomes(arvore, pesquisa[i]));
        }
        long fim = now();
        double tempo = (fim - inicio) / 1000.0;

        Arq.openWrite("matricula_arvoreBinaria.txt", "UTF-8");
        Arq.print("Matricula : 742238 \t");
        Arq.print("Tempo de execu????o : " + tempo + "s \t");
        Arq.print("Numero de Compara??oes : " + arvore.comparacoes);
        Arq.close();

    }
}