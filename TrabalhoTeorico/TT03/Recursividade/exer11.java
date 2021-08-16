class exer11 {
	
	public static int[] ordenaInteiros(int[] array){
		return ordenaInteiros(array,0);
	}

	public static int[] ordenaInteiros(int[] array,int i){
		int aux;
		if(i+1 < array.length){
			if(array[i] > array[i+1]){
				aux = array[i];
				array[i] = array[i+1];
				array[i+1] = array[i];
			}
			
			return ordenaInteiros(array,i+1);

		}

		return array;
	}
		
	public static void main(String[] args){
		int[] array = {
			0,5,1,2,4,3,13,8,15
		};

		array = ordenaInteiros(array);

		for(int i=0;i<array.length;i++){
			System.out.println(array[i]);
		}


	}

}
