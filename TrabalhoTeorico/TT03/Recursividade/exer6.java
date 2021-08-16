class exer6 {
	
	public static int multiplicacao(int a, int b){
		int resp =0;

		if(b>0){
			resp = a + multiplicacao(a,b-1);
		}

		return resp;
	}
	
	public static void main(String[] args){
		System.out.println(multiplicacao(4,3));
	}
}
