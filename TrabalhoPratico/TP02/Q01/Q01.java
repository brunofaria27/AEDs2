class Series {
    private String nome;
    private String formato;
    private String duracao;
    private String paisOrigem;
    private String idioma;
    private String emissoraOriginal;
    private String transmissaoOriginal;
    private int numeroTemporadas;
    private int numeroEpisodios;

    public Series() {
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

    public Series(String nome, String formato, String duracao, String paisOrigem, String idioma,
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

    public String toString() {
        return nome + " " + formato + " " + duracao + " " + paisOrigem + " " + idioma + " " + emissoraOriginal + " "
                + transmissaoOriginal + " " + numeroTemporadas + " " + numeroEpisodios;
    }
}

class Q01 {
    public static int posInicio(String code) {
        int resp = 0;

        for(int i = 0; i < code.length(); i++) {
            if(code.charAt(i) == 'i' && code.charAt(i + 1) == 'n' && code.charAt(i + 2) == 'f' && code.charAt(i + 3) == 'o' &&
                code.charAt(i + 4) == 'b' && code.charAt(i + 5) == 'o' && code.charAt(i + 6) == 'x' ) {
                resp = i;
            }
        }

        return resp;
    }

    public static int posFim(String code, int inicio) {
        int resp = 0;

        for(int i = inicio; i < code.length(); i++) {
            if((int)code.charAt(i) == 105 && (int)code.charAt(i + 1) == 100 && (int)code.charAt(i + 2) == 61 && (int)code.charAt(i + 3) == 34 &&
                 (int)code.charAt(i + 4) == 116 && (int)code.charAt(i + 5) == 111 && (int)code.charAt(i + 6) == 99 && (int)code.charAt(i + 7) == 34) {
                    resp = i;
            }
        }

        return resp;
    }

    /*
    * @method le o arquivo HTML pegando apenas a tabela dos dados que deseja
    * @param recebe o nome do arquivo
    * @return void
    */
    public static void pegarTable(String file) {
        String code = Arq.openReadClose(file);
        String resp = "";

        int comeco = posInicio(code);
        int fim = posFim(code, comeco);

        for(int i = comeco; i < fim; i++) {
            resp = resp + code.charAt(i);
        }

        Arq.openWrite(file);
        Arq.print(resp);
        Arq.close();
    }

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
    public static void main(String[] args) {
        String[] entrada = new String[1000];
        int numEntrada = 0;

        //Leitura da entrada padrao
        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; // Desconsiderar a palavra FIM

        //Para cada linha de entrada, gerando uma de saida ciptografada
        for(int i = 0; i < numEntrada; i++){
            pegarTable(entrada[i]);
        }
    }
}

/*
* Teste para ver o funcionamento do toString
* Series serie = new Series("Anne", "Série", "44 minutos", "Canadá", "Inglês", "CBC Television", "19 de março de 2017 – atualmente", 1, 7);
* System.out.println(serie);
*/