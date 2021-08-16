class exer4 {

	public static int fatorial (int n){
		int resp;
		if (n == 1){
			resp = 1;
		} else {
			resp = n * fatorial(n - 1);
		}
		return resp;

	}	
	public static void main(String[] args){
		int valor = fatorial(5);
		System.out.println(valor);
	}

}
