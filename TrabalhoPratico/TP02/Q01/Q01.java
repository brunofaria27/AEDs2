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
    /*
    * @method le o arquivo HTML e conta quantas linhas tem nele
    * @param recebe o nome do arquivo
    * @return a quantidade de linhas
    */
    public static int qntLinhas(String file) {
        int linhas = 0;
        Arq.openRead(file);

        while(Arq.hasNext() == true) {
            Arq.readLine();
            linhas++;
        }

        Arq.close();
        return linhas;
    }

    /*
    * @method le o arquivo HTML pegando apenas a tabela dos dados que deseja
    * @param recebe o nome do arquivo
    * @return void
    */
    public static void pegarTable(String file) {
        String code = Arq.openReadClose(file);
        String resp = "";

        int comeco = code.indexOf("</head>");
        int fim = code.indexOf("toctitle", comeco);

        for(int i = comeco; i < fim; i++) {
            resp = resp + code.charAt(i);
        }

        Arq.openWrite(file);
        Arq.print(resp);
        Arq.close();
    }

    /*
    * @method tira todas as tags do HTML deixando apenas os textos
    * @param nome do arquivo e quantidade de linhas
    * @return void
    */
    public static void excluirTags(String file, int qntLinhas) {
        String[] linhas = new String[qntLinhas];  // Armazena cada linha do arquivo
        String resp = "";
        Arq.openRead(file);

        for(int i = 0; i < qntLinhas; i++) {
            linhas[i] = Arq.readLine(); 
        }

        boolean flag = false;
        for(int i = 0; i < qntLinhas; i++) {
            for(int j = 0; j < linhas[i].length() - 1; j++) {
                if(linhas[i].charAt(j) == '>' && linhas[i].charAt(j + 1) != '<') {
                    flag = true;
                } else if(linhas[i].charAt(j) == '<') {
                    flag = false;
                }

                if(flag) {
                    if(linhas[i].charAt(j + 1) != '<') {
                        resp = resp + linhas[i].charAt(j + 1);
                    }
                }
            }
            resp = resp + "\n";
        }

        Arq.openWrite(file);
        Arq.print(resp);
        Arq.close();
    }

    /*
    * @method excluir &nbsp; e &#160;
    * @param recebe o nome do arquivo
    * @return void
    */
    public static void excluirItens(String file, int qntLinhas) {
        String[] linhas = new String[qntLinhas];  // Armazena cada linha do arquivo
        String resp = "";
        Arq.openRead(file);

        for(int i = 0; i < qntLinhas; i++) {
            linhas[i] = Arq.readLine(); 
        }

        boolean flag = false;
        for(int i = 0; i < qntLinhas; i++) {
            for(int j = 0; j < linhas[i].length(); j++) {
                if(linhas[i].charAt(j) == '&') {
                    while(linhas[i].charAt(j) != ';') {
                        flag = false;
                        j++;
                    }
                } else {
                    flag = true;
                }

                if(flag) {
                    if(linhas[i].charAt(j) != ';') {
                        resp = resp + linhas[i].charAt(j);
                    }
                }
            }
            resp = resp + "\n";
        }

        Arq.openWrite(file);
        Arq.print(resp);
        Arq.close();
    }

    /*
    * @method pegar dados do HTML e criar um objeto
    * @param recebe o nome do arquivo e a quantidade de linhas
    * @return void
    */
    public static void pegarDados(String file, int qntLinhas) {
        String[] linhas = new String[qntLinhas];  // Armazena cada linha do arquivo
        Arq.openRead(file, "UTF-8");
        Series serie = new Series();

        for(int i = 0; i < qntLinhas; i++) {
            linhas[i] = Arq.readLine(); 
        }

        int posPonto = 0;
        for(int i = 0; i < file.length(); i++) {
            if(file.charAt(i) == '.') {
                posPonto = i;
            }
        }
        String nome = file.substring(12, posPonto);
        nome = nome.replace('_', ' ');
        serie.setNome(nome);

        for(int i = 0; i < qntLinhas; i++) {
            if(linhas[i].equals("Formato")) {
                serie.setFormato(linhas[i + 1]);
            } else if(linhas[i].equals("Duração")) {
                serie.setDuracao(linhas[i + 1]);
            } else if(linhas[i].equals("País de origem")) {
                String resp = "";
                for(int j = 0; j < linhas[i + 1].length() - 1; j++) {
                    if(linhas[i + 1].charAt(0) != ' ') {
                        resp = linhas[i + 1];
                    } else {
                        resp = resp + linhas[i + 1].charAt(j + 1);
                    }
                }
                serie.setPaisOrigem(resp);
            } else if(linhas[i].equals("Idioma original")) {
                String resp = "";
                for(int j = 0; j < linhas[i + 1].length() - 1; j++) {
                    if(linhas[i + 1].charAt(0) != ' ') {
                        resp = linhas[i + 1];
                    } else {
                        resp = resp + linhas[i + 1].charAt(j + 1);
                    }
                }
                serie.setIdioma(resp);
            } else if(linhas[i].equals("Emissora de televisão original")) {
                String resp = "";
                for(int j = 0; j < linhas[i + 1].length() - 1; j++) {
                    if(linhas[i + 1].charAt(0) != ' ') {
                        resp = linhas[i + 1];
                    } else {
                        resp = resp + linhas[i + 1].charAt(j + 1);
                    }
                }
                serie.setEmissoraOriginal(resp);
            } else if(linhas[i].equals("Transmissão original")) {
                String resp = "";
                for(int j = 0; j < linhas[i + 1].length() - 1; j++) {
                    if(linhas[i + 1].charAt(0) != ' ') {
                        resp = linhas[i + 1];
                    } else {
                        resp = resp + linhas[i + 1].charAt(j + 1);
                    }
                }
                serie.setTransmissaoOriginal(resp);
            } else if(linhas[i].equals("N.º de temporadas")) {
                String resp = "";
                for(int j = 0; j < linhas[i + 1].length(); j++) {
                    if(linhas[i + 1].charAt(j) == '0' || linhas[i + 1].charAt(j) == '1' || linhas[i + 1].charAt(j) == '2' || linhas[i + 1].charAt(j) == '3' || linhas[i + 1].charAt(j) == '4' ||
                    linhas[i + 1].charAt(j) == '5' || linhas[i + 1].charAt(j) == '6' || linhas[i + 1].charAt(j) == '7' || linhas[i + 1].charAt(j) == '8' || linhas[i + 1].charAt(j) == '9') {
                        resp = resp + linhas[i + 1].charAt(j);
                    } else if(linhas[i + 1].charAt(j) == '(' || linhas[i + 1].charAt(j) == '-' || linhas[i + 1].charAt(j) == '+' || linhas[i + 1].charAt(j) == ' ') {
                        break;
                    }
                }
                int num = Integer.parseInt(resp);
                serie.setNumeroTemporadas(num);
            } else if(linhas[i].equals("N.º de episódios")) {
                String resp = "";
                for(int j = 0; j < linhas[i + 1].length(); j++) {
                    if(linhas[i + 1].charAt(j) == '0' || linhas[i + 1].charAt(j) == '1' || linhas[i + 1].charAt(j) == '2' || linhas[i + 1].charAt(j) == '3' || linhas[i + 1].charAt(j) == '4' ||
                    linhas[i + 1].charAt(j) == '5' || linhas[i + 1].charAt(j) == '6' || linhas[i + 1].charAt(j) == '7' || linhas[i + 1].charAt(j) == '8' || linhas[i + 1].charAt(j) == '9') {
                        resp = resp + linhas[i + 1].charAt(j);
                    } else if(linhas[i + 1].charAt(j) == '(' || linhas[i + 1].charAt(j) == '-' || linhas[i + 1].charAt(j) == '+' || linhas[i + 1].charAt(j) == ' ') {
                        break;
                    }
                }
                int num = Integer.parseInt(resp);
                serie.setNumeroEpisodios(num);
            }
        }

        System.out.println(serie.toString());

        Arq.close();
    }

    /*
    * @method recebe todas as funcoes que irá tratar o codigo HTML para deixar o desejado
    * @param recebe o nome do arquivo
    * @return void
    */
    public static void tratarHTML(String file) {
        String arquivo = "/tmp/series/";
        arquivo += file; 
        pegarTable(arquivo);
        int qntLinhas = qntLinhas(arquivo);
        excluirTags(arquivo, qntLinhas);
        excluirItens(arquivo, qntLinhas);
        pegarDados(arquivo, qntLinhas);
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
            tratarHTML(entrada[i]);
        }
    }
}