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
//        System.out.println(this.toString());
    }

    public String toString() {
        return nome + " " + formato + " " + duracao + " " + paisOrigem + " " + idioma + " " + emissoraOriginal + " "
                + transmissaoOriginal + " " + numeroTemporadas + " " + numeroEpisodios;
    }
}

class Lista {
    private Serie[] array;
    private int n;
    public int comparacoes = 0;

    public Lista() {
        this(61);
    }

    public Lista(int tamanho) {
        array = new Serie[tamanho];
        n = 0;
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

    public void swap(int i, int j, Lista series) {
        Serie temp = series.array[i];
        series.array[i] = series.array[j];
        series.array[j] = temp;
    }

    public void heapify(Lista series, int n, int i) {
        int maior = i;
        int esq = 2 * i ;
        int dir = 2 * i + 1;

        comparacoes++;
        if (esq < n && (series.array[esq].getFormato().compareTo(series.array[maior].getFormato()) == 0)) {
            comparacoes++;
            if (series.array[esq].getNome().compareTo(series.array[maior].getNome()) > 0) {
                maior = esq;
            }
        } else {
            comparacoes++;
            if (esq < n && series.array[esq].getFormato().compareTo(series.array[maior].getFormato()) > 0) {
                maior = esq;
            }
        }

        comparacoes++;
        if (dir < n && series.array[dir].getFormato().compareTo(series.array[maior].getFormato()) == 0) {
            comparacoes++;
            if (series.array[dir].getNome().compareTo(series.array[maior].getNome()) > 0) {
                maior = dir;
            }
        } else {
            comparacoes++;
            if (dir < n && series.array[dir].getFormato().compareTo(series.array[maior].getFormato()) > 0) {
                maior = dir;
            }
        }

        comparacoes++;
        if(maior != i) {
            swap(i, maior, series);
            heapify(series, n, maior);
        }
    }

    public void HeapSort(Lista series, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            comparacoes++;
            heapify(series, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            comparacoes++;
            swap(0, i, series);
            heapify(series, i, 0);
        }
    }

    public void mostrar() {
        for(int i = 0; i < n; i++) {
            System.out.println(array[i]);
        }
    }
    
}

class Q05 {
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

    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        Lista lista = new Lista();

        String[] entrada = new String[1000];
        int numEntrada = 0;

        //Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; // Desconsiderar a palavra FIM

        Serie[] series = lerDados(entrada, numEntrada);

        for(int i = 0; i < numEntrada; i++){
            lista.inserirFim(series[i]);
        }

        long inicio = now();
        lista.HeapSort(lista, numEntrada);
        long fim = now();

        lista.mostrar();

        double tempo = (fim - inicio) / 1000.0;

        Arq.openWrite("matricula_heapsort.txt", "UTF-8");
        Arq.print("Matricula : 742238 \t");
        Arq.print("Tempo de execução : " + tempo + "s \t");
        Arq.print("Numero de Comparaçoes : " + lista.comparacoes);
        Arq.close();

    }
}