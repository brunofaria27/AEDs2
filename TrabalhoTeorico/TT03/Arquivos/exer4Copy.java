class exer4Copy {
	public static void main(String[] args){
		// Ler 2 txt e copiar o conteudo do 1 para o ultimo
		String arq01,arq02;
		arq01 = MyIO.readString("Digite o nome do arquivo a ser copiado : ");
		arq02 = MyIO.readString("Digite o nome do arquivo a ser substituido : ");

		Arq.openRead(arq01);
		String copia = Arq.readAll();
		Arq.close();

		Arq.openWrite(arq02);
		Arq.print(copia);
		Arq.close();
		//questao 05
		Arq.openWrite(arq02 + "-Maiusculo");
		Arq.print(copia.toUpperCase());
		Arq.close();

		// Copiar para maiuscula e salvar no segundo
		String copiaInverso = "";
		for(int i = arq02.length() - 1; i >= 0; i--){
			copiaInverso += copia.charAt(i);
		}

		Arq.openWrite(arq02 + "-Inverso");
		Arq.print(copiaInverso);
		Arq.close();	
	}
}
