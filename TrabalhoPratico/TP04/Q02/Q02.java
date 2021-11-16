import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509CertSelector;
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

        while(!br.readLine().contains("Duração"));
        this.duracao = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while(!br.readLine().contains("País de origem"));
        this.paisOrigem = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while(!br.readLine().contains("Idioma original"));
        this.idioma = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while(!br.readLine().contains("Emissora de televisão original"));
        this.emissoraOriginal = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while(!br.readLine().contains("Transmissão original"));
        this.transmissaoOriginal = removeTags(br.readLine()).replaceAll("&#160;", "").replaceAll("&nbsp;", "").trim();

        while(!br.readLine().contains("N.º de temporadas"));
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

        while(!br.readLine().contains("N.º de episódios"));
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
	public char chave; // Conteudo do no.
	public No esq; // No da esquerda.
	public No dir; // No da direita.
    public No2 outro;
	
	No(char chave) {
        this.chave = chave;
        this.esq = this.dir = null;
        this.outro = null;

    }

    No(char chave, No esq, No dir) {
        this.chave = chave;
        this.esq = esq;
        this.dir = dir;
        this.outro = null;

    }
}

class No2 {
	public String elemento; // Conteudo do no.
	public No2 esq; // No da esquerda.
	public No2 dir; // No da direita.
	
	No2(String elemento) {
		this.elemento = elemento;
		this.esq = this.dir = null;
	}

	No2(String elemento, No2 esq, No2 dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
	}
}

class ArvoreArvore {
    private No raiz;
    public String caminho = ""; // Printar o caminho da pesquisa
    public int comparacoes = 0;

    public ArvoreArvore() throws Exception {
        raiz = null;
        inserir1('D');
        inserir1('R');
        inserir1('Z');
        inserir1('X');
        inserir1('V');
        inserir1('B');
        inserir1('F');
        inserir1('P');
        inserir1('U');
        inserir1('I');
        inserir1('G');
        inserir1('E');
        inserir1('J');
        inserir1('L');
        inserir1('H');
        inserir1('T');
        inserir1('A');
        inserir1('W');
        inserir1('S');
        inserir1('O');
        inserir1('M');
        inserir1('N');
        inserir1('K');
        inserir1('C');
        inserir1('Y');
        inserir1('Q');
    }

    /* Inicio metódo de Inserir */
    public void inserir1(char chave) throws Exception {
        raiz = inserir1(chave, raiz);
    }

    private No inserir1(char chave, No i) {
        if(i == null){
            i = new No(chave);
        } else if(chave < i.chave){
            i.esq = inserir1(chave, i.esq);
        } else if(chave > i.chave){
            i.dir = inserir1(chave, i.dir);
        }

        return i;
    }

    public void inserir2(Serie series){
        raiz = inserir2(series, raiz);
    }

    public No inserir2(Serie series, No i){

        if(series.getNome().charAt(0) == i.chave) {
            i.outro = inserirNome(series.getNome(), i.outro);
        } else if(series.getNome().charAt(0) < i.chave) {
            i.esq = inserir2(series, i.esq); 
        } else if(series.getNome().charAt(0) > i.chave) {
            i.dir = inserir2(series, i.dir);
        }

        return i;
    }

    private No2 inserirNome(String nome, No2 i){
    
        if(i == null) {
            i = new No2(nome);
        } else if(nome.compareTo(i.elemento) < 0) {
            i.esq = inserirNome(nome, i.esq);
        } else if(nome.compareTo(i.elemento) > 0) {
            i.dir = inserirNome(nome, i.dir);
        } 

        return i;
    }
    /* Fim metódo de Inserir */

    /* Inicio metódos de caminhar */
    public void caminharPre() {
        MyIO.println("[ ");
        caminharPre(raiz);
        MyIO.println(" ]");
    }

    private void caminharPre(No i) {
        if(i != null){
            MyIO.println(i.chave + " ->");
            caminharNome(i.outro);
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }

    private void caminharNome(No2 no) {
        if(no != null){
            MyIO.println(no.elemento);
            caminharNome(no.esq);
            caminharNome(no.dir);
        }
    }
    /* Fim metódos de caminhar */

    /* Inicio metódos de pesquisa */
    public boolean pesquisar(String x){
        caminho += "raiz ";
        return pesquisar(raiz, x);
    }

    private boolean pesquisar(No no, String x) {
        boolean resp;
        if (no == null) { 
            resp = false;
        } else {
            resp = pesquisarSegundaArvore(no.outro,x);
            if(resp == false) {
                caminho += "esq ";
                resp = pesquisar(no.esq, x);

                if(resp == false){
                    caminho += "dir ";
                    resp = pesquisar(no.dir, x);
                }
            }

        }
        return resp;
    }

    private boolean pesquisarSegundaArvore(No2 no, String x) {
        boolean resp;
        if (no == null) { 
            resp = false;
        } else {
            if(x.equals(no.elemento)) {
                comparacoes++;
                resp = true;
            } else {
                caminho += "esq ";
                comparacoes++;
                resp = pesquisarSegundaArvore(no.esq, x);

                if(resp == false){
                    caminho += "dir ";
                    comparacoes++;
                    resp = pesquisarSegundaArvore(no.dir, x);
                }
            }
        }
        return resp;
    }
    /* Fim metódos de pesquisa */
}

class Q02 {
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

    public static String pesquisaNomes(ArvoreArvore arvore, String seriesPesquisa) {
        String resp = "";

        if(arvore.pesquisar(seriesPesquisa) == true) {
            resp += arvore.caminho + "SIM";
        } else {
            resp += arvore.caminho + "NAO";
        }

        arvore.caminho = "";
        return resp;
    }

    public static void tratarComando(String seriesEntrada, ArvoreArvore arvore) throws Exception {
        String[] aux = new String[2];

        if(seriesEntrada.charAt(0) == 'I') {
            aux = seriesEntrada.split(" ");
            arvore.inserir2(lerDados(aux[1]));
        } 
    }

    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        ArvoreArvore arvore = new ArvoreArvore();
        String[] entrada = new String[1000];
        int numEntrada = 0;

        // Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; // Desconsiderar a palavra FIM

        Serie[] series = lerDados(entrada, numEntrada);

        // Inserindo nomes na arvore 2
        for(int i = 0; i < numEntrada; i++){
            arvore.inserir2(series[i]);
        }

        // Inserções e remoções na árvore
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

        Arq.openWrite("matricula_arvoreArvore.txt", "UTF-8");
        Arq.print("Matricula : 742238 \t");
        Arq.print("Tempo de execução : " + tempo + "s \t");
        Arq.print("Numero de Comparaçoes : " + arvore.comparacoes);
        Arq.close();

    }
}