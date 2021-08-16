class exer10 {
	
	public static char toUpper(char c){
		return(c>='a' && c<= 'z')?((char)(c-32)):c;
	}

	
	public static boolean isVogal(char c){
		c= toUpper(c);
		return (c=='A' || c=='E' || c=='I' || c=='O' || c=='U');
							}

	public static int contMaiusculo (String s){
		return contMaiusculo (s, 0);

	}
	public static int contMaiusculo (String s, int i){
		int cont = 0;
		if (i < s.length()){
			if (isVogal(s.charAt(i)) == true){
				cont++;
			}
			cont += contMaiusculo (s, i + 1);
		}
		return cont;
	}

	public static void main(String[] args){
		String str = "Algoritmos";
		System.out.println(contMaiusculo(str));		
	}
}
