class exer8 {
	
	public static int maior (int[] vet, int n){
		return maior (vet, n, 0);
	}
	public static int maior (int[] vet, int n, int i){
		int resp;
		if (i == n - 1){
			resp = vet[n - 1];
		} else {
			resp = maior(vet, n, i + 1);
			if (resp < vet[i]){
				resp = vet[i];
			}
		}
		return resp;
	}

	public static void main(String[] args){
		int[] vet = {
			0,1,2,3,4,5,6,7,8,9
		};

		System.out.println(maior(vet,vet.length));
	}

}
