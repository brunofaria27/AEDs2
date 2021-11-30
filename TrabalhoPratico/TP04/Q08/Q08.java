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
        this.numeroTemporadas = -1;
        this.numeroEpisodios = -1;
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

class Celula {
    public Serie elemento;
    public Celula prox;

    public Celula(Serie elemento){
        this.elemento = elemento;
        this.prox = null;
    }   

    public Celula(Serie elemento, Celula prox){
        this.elemento = elemento;
        this.prox = prox;
    }

}

class Lista {
    public Celula primeiro;
    public Celula ultimo;

    public Lista(){
        Serie NULO = new Serie();
        primeiro = new Celula(NULO);
        ultimo = primeiro;
    }

    public void inserirInicio(Serie serie){
        Celula tmp = new Celula(serie);

        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;

        if(primeiro == ultimo){
            ultimo = tmp;
        }

        tmp = null;
    }

    public void inserirFim(Serie serie){
        Celula tmp = new Celula(serie);
        ultimo.prox = tmp;
        ultimo = ultimo.prox;
        tmp = null;
    }

    public Serie removerInicio() throws Exception {
        Serie resp = new Serie();

        if(primeiro == ultimo){
            throw new Exception("ERRO AO REMOVER(vazia)");
        }

        primeiro = primeiro.prox;
        resp = primeiro.elemento;
        
        return resp;
    }

    public Serie removerFim() throws Exception {
        Serie resp = new Serie();
        Celula i = null;

        if(primeiro == ultimo){
            throw new Exception("ERRO AO REMOVER(vazia)!");
        }

        resp = ultimo.elemento;

        for(i = primeiro; i.prox != ultimo; i = i.prox);
        ultimo = i;
        i = ultimo.prox = null;

        return resp;
    }

    public boolean pesquisar(String nome) {
        Hash hash = new Hash();
        boolean resp = false;
        
        for(Celula i = primeiro.prox; i!=null ; i = i.prox){
            resp = (i.elemento.getNome().equals(nome)) ? true : resp ;
            if(resp){ i = ultimo;  }
            hash.comparacoes++;
        }

        return resp;
    }

}

class Hash {
    Lista tabela[];
    int tamanho;
    final Serie NULO = new Serie();
    public int comparacoes = 0;

    public Hash(){
        this(21);
    }

    public Hash(int tamanho){
        this.tamanho = tamanho;
        tabela = new Lista[tamanho];
        for(int i = 0; i < tamanho; i++){ tabela[i] = new Lista(); }
    }

    public int h(String nome) {
        int nomeConverted = 0;
        for(int i = 0; i < nome.length(); ++i) {
            char c = nome.charAt(i);
            nomeConverted = (int) c;
        }
        return nomeConverted % tamanho;
    }

    public boolean pesquisar(String nome){
        boolean resp = false;

        for(int i = 0; i < tamanho; i++){
            resp = (tabela[i].pesquisar(nome));
            comparacoes++;
            if(resp){ i = tamanho; }
        }

        return resp;
    }

    public void inserir(Serie serie){
        int pos = h(serie.getNome());
        tabela[pos].inserirInicio(serie);
    }

    public void mostrar(){
        for(int i = 0; i < tamanho; i++){
            mostrar(tabela[i],i);
        }
    }

    public void mostrar(Lista lista,int n){
        MyIO.println("MOSTRANDO LISTA -> "+n);

        for(Celula i = lista.primeiro.prox; i != null; i = i.prox){
            MyIO.println(i.elemento.getNome());
        }
    }

}

public class Q08  {
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
        Hash hash = new Hash();
        String[] entrada = new String[1000];
        int numEntrada = 0;

        // Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; // Desconsiderar a palavra FIM

        Serie[] series = lerDados(entrada, numEntrada);

        long inicio = now();
        for(int i = 0; i < numEntrada; i++) {
            hash.inserir(series[i]);
        }

        // Nomes a serem pesquisados
        String[] pesquisa = new String[1000];
        int numPesquisa = 0;
        
        do {
            pesquisa[numPesquisa] = MyIO.readLine();
        } while (isFim(pesquisa[numPesquisa++]) == false);
        numPesquisa--;

        for(int i = 0; i < numPesquisa; i++){
            if(hash.pesquisar(pesquisa[i]) == true) {
                System.out.println(" SIM");
            } else if(hash.pesquisar(pesquisa[i]) == false)  {
                System.out.println(" NAO");
            }
        }

        long fim = now();
        double tempo = (fim - inicio) / 1000.0;

        Arq.openWrite("matricula_hashIndireta.txt", "UTF-8");
        Arq.print("Matricula : 742238 \t");
        Arq.print("Tempo de execução : " + tempo + "s \t");
        Arq.print("Numero de Comparaçoes : " + hash.comparacoes);
        Arq.close();

    }
}


