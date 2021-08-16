class exer4 {
	public static void main(String[] args){
		// Ler e escrever no arquivo
		String arquivo,frase;
		arquivo = MyIO.readString("Digite o nome do arquivo[.txt] : ");
		frase = MyIO.readLine("Digite a frase para salvar no arquivo : ");

		Arq.openWrite(arquivo);
		Arq.println(frase);

		Arq.close();
		
		// Conteudo na tela
		Arq.openRead(arquivo);
		String conteudo = Arq.readAll();
		MyIO.println("Conteudo do arquivo : ");
		MyIO.println(conteudo);
		Arq.close();

		// Conteudo para letras maiusculas
		Arq.openRead(arquivo);
		String conteudoMaiusculo = Arq.readAll();
		conteudoMaiusculo = conteudoMaiusculo.toUpperCase();
		MyIO.println("Conteudo do arquivo em maiusculo : ");
		MyIO.println(conteudoMaiusculo);
		Arq.close();
	}
}
