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
        this.nome = nome;

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
        //System.out.println(this.toString());
    }
    
    public String toString() {
        return nome + " " + formato + " " + duracao + " " + paisOrigem + " " + idioma + " " + emissoraOriginal + " "
                + transmissaoOriginal + " " + numeroTemporadas + " " + numeroEpisodios;
    }
}

class Lista {
    private Serie[] array;
    private int n;

    public Lista() {
        this(100);
    }

    public Lista(int tamanho) {
        array = new Serie[tamanho];
        n = 0;
    }

    public Serie[] getArray() {
        return this.array;
    }

    public int getTamanhoArray() {
        return this.array.length;
    }

    // Métodos importantes
    public void inserirFim(Serie serie) throws Exception {
        if(n >= array.length) {
            throw new Exception("Erro ao inserir objeto!");
        }

        array[n] = serie.Clone();
        n++;
    }

    public void inserirInicio(Serie serie) throws Exception {
        if(n >= array.length) {
            throw new Exception("Erro ao inserir objeto!");
        }

        for(int i = n; i > 0; i--) {
            array[i] = array[i - 1].Clone();
        }

        array[0] = serie.Clone();
        n++;
    }

    public void inserir(Serie serie, int posicao) throws Exception {
        if(n >= array.length || posicao < 0 || posicao > n) {
            throw new Exception("Erro ao inserir objeto na posicao: " + posicao);
        }

        for(int i = n; i > posicao; i--) {
            array[i] = array[i - 1].Clone();
        }

        array[posicao] = serie.Clone();
        n++;
    }

    public Serie removerInicio() throws Exception {
        if(n == 0) {
            throw new Exception("Erro ao remover, tamanho igual a zero!");
        }

        // Guarda o elemento da primeira posicao em resp, e diminui o tamanho do array para sinalizar que removeu
        Serie resp = array[0].Clone();
        n--;

        for(int i = 0; i < n; i++) {
            array[i] = array[i + 1].Clone();
        }

        return resp;
    }

    public Serie removerFim() throws Exception {
        if(n == 0) {
            throw new Exception("Erro ao remover do final!");
        }

        return array[--n];  // remover antes de enviar 
    }

    public Serie remover(int posicao) throws Exception {
        if(n == 0 || posicao < 0 || posicao >= n) {
            throw new Exception("Erro ao remover");
        }

        // Pega o elemento da posicao desejada e retira um do tamanho, para sinalizar que removeu
        Serie resp = array[posicao];
        n--;

        // Move os elementos para preencher o buraco do elemento removido
        for(int i = posicao; i < n; i++) {
            array[i] = array[i + 1].Clone();
        }

        return resp;
    }

    public void mostrar() {
        for(int i = 0; i < n; i++) {
            System.out.println(array[i]);
        }
    }
    
}

class Q05 {
    static Serie[] removidos = new Serie[20];   // Series removidas da lista
    static int r = 0;                           // contador removidos

    public static Serie lerDados(String entrada) throws Exception {
        Serie serie = new Serie(); 
        String arquivo = "";

        arquivo = "/tmp/series/";
        arquivo += entrada; 

        serie = new Serie();
        serie.ler(arquivo);

        return serie;
    }

    public static void printaRemovidos() {
        for(int i = 0; i < r; i++) {
            MyIO.println("(R) "+ removidos[i].getNome());
        }
    }

    public static void tratarComando(String seriesEntrada, Lista lista) throws Exception {
        String[] aux = new String[2];

        if(seriesEntrada.charAt(0) == 'I' && seriesEntrada.charAt(1) == 'I') {
            aux = seriesEntrada.split(" ");
            lista.inserirInicio(lerDados(aux[1]));
        } else if(seriesEntrada.charAt(0) == 'I' && seriesEntrada.charAt(1) == 'F') {
            aux = seriesEntrada.split(" ");
            lista.inserirFim(lerDados(aux[1]));
        } else if(seriesEntrada.charAt(0) == 'I' && seriesEntrada.charAt(1) == '*') {
            aux = seriesEntrada.split(" ");
            lista.inserir(lerDados(aux[2]), Integer.parseInt(aux[1]));
        } else if(seriesEntrada.charAt(0) == 'R' && seriesEntrada.charAt(1) == '*') {
            aux = seriesEntrada.split(" ");
            removidos[r] = lista.remover(Integer.parseInt(aux[1]) );
            r++;
        } else if(seriesEntrada.charAt(0) == 'R' && seriesEntrada.charAt(1) == 'I') {
            removidos[r] = lista.removerInicio();
            r++;
        } else if(seriesEntrada.charAt(0) == 'R' && seriesEntrada.charAt(1) == 'F') {
            removidos[r] = lista.removerFim();
            r++;
        }

    }

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        // Inicialização Objetos
        Serie series = new Serie();
        Lista listaSerie = new Lista(100);

        // Inicialização váriaveis
        String[] entrada = new String[1000];
        String[] seriesEntrada = new String[1000];
        int numEntrada = 0;
        int contador = 0;

        // Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; // Desconsiderar a palavra FIM

        // Criação Objetos
        String[] arquivo = new String[100];
        for(int i = 0; i < numEntrada; i++) {
            arquivo[i] = "/tmp/series/";
            arquivo[i] += entrada[i]; 
        }

        for(int i = 0; i < numEntrada; i++) {
            try {
                series.ler(arquivo[i]);
                listaSerie.inserirFim(series);
            } catch (Exception e) {
            }
        }

        int quantidade = MyIO.readInt();

        // Ler as demais entradas
        do {
            seriesEntrada[contador] = MyIO.readLine();
            tratarComando(seriesEntrada[contador], listaSerie);
            contador++;
        } while (contador < quantidade);

        printaRemovidos();
        listaSerie.mostrar();

    }
}