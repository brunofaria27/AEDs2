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

class NoAN {
    public boolean cor;
    public Serie elemento;
    public NoAN esq, dir;
    
    public NoAN () {
        this(null);
    }

    public NoAN (Serie elemento) {
        this(elemento, false, null, null);
    }

    public NoAN (Serie elemento, boolean cor) {
        this(elemento, cor, null, null);
    }

    public NoAN (Serie elemento, boolean cor, NoAN esq, NoAN dir) {
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class Alvinegra {
    private NoAN raiz;
    public String caminho = "";
    public int comparacoes = 0;

    public Alvinegra() {
        raiz = null;
    }

    public boolean pesquisar(String x) {
        caminho += "raiz ";
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(String x, NoAN i) {
        boolean resp;
        if (i == null) {
            resp = false;
        } else if (x.equals(i.elemento.getNome())) {
            comparacoes++;
            resp = true;
        } else if (x.compareTo(i.elemento.getNome()) < 0) {
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

    private void emOrdem(NoAN i) {
        if (i != null) {
            emOrdem(i.esq);
            System.out.println(i.elemento);
            emOrdem(i.dir);
        }
    }

    public void inserir(Serie series) throws Exception {
        //Se a arvore estiver vazia
        if(raiz == null){
            raiz = new NoAN(series, false);
            //Senao, se a arvore tiver um elemento 
        } else if (raiz.esq == null && raiz.dir == null){
            if (raiz.elemento.getNome().compareTo(series.getNome()) > 0) {
                comparacoes++;
                raiz.esq = new NoAN(series, true);
            } else {
                raiz.dir = new NoAN(series, true);
            }
            //Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null){
            if(raiz.elemento.getNome().compareTo(series.getNome()) > 0) {
                comparacoes++;
                raiz.esq = new NoAN(series);
            } else if (raiz.dir.elemento.getNome().compareTo(series.getNome()) > 0) {
                comparacoes++;
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = series;
            } else {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = series;
            }
            raiz.esq.cor = raiz.dir.cor = false;
            //Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null){
            if(raiz.elemento.getNome().compareTo(series.getNome()) < 0) {
                comparacoes++;
                raiz.dir = new NoAN(series);
            } else if (raiz.esq.elemento.getNome().compareTo(series.getNome()) < 0) {
                comparacoes++;
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = series;
            } else {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = series;
            }
            comparacoes++;
            raiz.esq.cor = raiz.dir.cor = false;
            //Senao, a arvore tem tres ou mais elementos
        } else {
            inserir(series, null, null, null, raiz);
        }

        raiz.cor = false;
    }

    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if(pai.cor == true){
            // 4 tipos de reequilibrios e acoplamento
            if(pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0){ // rotacao a esquerda ou direita-esquerda
                comparacoes++;
                if(i.elemento.getNome().compareTo(pai.elemento.getNome()) > 0) {
                    comparacoes++;
                    avo = rotacaoEsq(avo);
                } else {
                    comparacoes++;
                    avo = rotacaoDirEsq(avo);
                }
            } else { // rotacao a direita ou esquerda-direita
                if( i.elemento.getNome().compareTo(pai.elemento.getNome()) < 0 ) {
                    comparacoes++;
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }

            if (bisavo == null){
                raiz = avo;
            } else {
                if(avo.elemento.getNome().compareTo(bisavo.elemento.getNome()) < 0) {
                    comparacoes++;
                    bisavo.esq = avo;
                } else {
                    bisavo.dir = avo;
                }
            }

            //reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        } // if(pai.cor == true)

    }

    private void inserir(Serie series, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
        if (i == null) {
            if(series.getNome().compareTo(pai.elemento.getNome()) < 0) {
                comparacoes++;
                i = pai.esq = new NoAN(series, true);
            } else {
                i = pai.dir = new NoAN(series, true);
            }

            if(pai.cor == true){
                balancear(bisavo, avo, pai, i);
            }

        } else {
            // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if(i == raiz){
                    i.cor = false;
                }
                else if(pai.cor == true){
                    balancear(bisavo, avo, pai, i);
                }
            }

            if (series.getNome().compareTo(i.elemento.getNome()) < 0) {
                comparacoes++;
                inserir(series, avo, pai, i, i.esq);
            }
            else if (series.getNome().compareTo(i.elemento.getNome()) > 0) {
                comparacoes++;
                inserir(series, avo, pai, i, i.dir);
            }
            else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }

        }
    }

    private NoAN rotacaoDir(NoAN no) {
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private NoAN rotacaoEsq(NoAN no) {
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoAN rotacaoDirEsq(NoAN no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private NoAN rotacaoEsqDir(NoAN no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }

    public void mostrar() {
        emOrdem(raiz);
    }

}

class Q04 {
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

    public static String pesquisaNomes(Alvinegra arvore, String seriesPesquisa) {
        String resp = "";

        if(arvore.pesquisar(seriesPesquisa) == true) {
            resp += arvore.caminho + "SIM";
        } else {
            resp += arvore.caminho + "NAO";
        }

        arvore.caminho = "";
        return resp;
    }

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        Alvinegra arvore = new Alvinegra();
        String[] entrada = new String[1000];
        int numEntrada = 0;

        // Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; // Desconsiderar a palavra FIM

        Serie[] series = lerDados(entrada, numEntrada);

        long inicio = now();
        for(int i = 0; i < numEntrada; i++){
            arvore.inserir(series[i]);
        }

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

        Arq.openWrite("matricula_arvoreAlvinegra.txt", "UTF-8");
        Arq.print("Matricula : 742238 \t");
        Arq.print("Tempo de execução : " + tempo + "s \t");
        Arq.print("Numero de Comparaçoes : " + arvore.comparacoes);
        Arq.close();

    }
}
