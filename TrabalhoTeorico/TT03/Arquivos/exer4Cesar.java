class exer4Cesar {
	public static void main(String[] args){
		// Criptografar com Ciframento de Cesar
		String arq = MyIO.readString("Digite o nome do arquivo.txt: ");
		Arq.openRead(arq);
		String conteudo = Arq.readAll();
		String contCifrado = "";

		for(int i=0;i<conteudo.length();i++){
			int aux = (int)conteudo.charAt(i)+3;
			contCifrado += (char)aux;
		}

		MyIO.println("Conteudo do arquivo cifrado: ");
		MyIO.println(contCifrado);
		Arq.openWrite(arq + "-Cifrado");
		Arq.print(contCifrado);
		Arq.close();

		// Descriptografar com Ciframento de Cesar
		Arq.openRead(arq + "-Cifrado");
		String cifraCont = Arq.readAll();
		Arq.close();
		String descriptoCont = "";
		for(int i = 0; i < contCifrado.length(); i++){
			int aux = (int)cifraCont.charAt(i) - 3;
			descriptoCont += (char)aux;
		}
		
		MyIO.println("Conteudo do arquivo descriptografado");
		MyIO.println(descriptoCont);

	}
}
