public class exer5 {
    public static boolean ehPalind(String s){
        int tam = s.length()-1;
        for(int i=0,j=tam;i < j;i++,j--){
           if(s.charAt(i) != s.charAt(j)) {
              return false;
           }
        }
        return true;
     }
  
     public static void saida(String s){
        if(ehPalind(s) == true ){
           MyIO.println("SIM");
        } else {
           MyIO.println("NAO");
        }
     }   
  
     public static boolean isFim(String s){
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
     }

      public static void main (String[] args){
          MyIO.setCharset("UTF-8");
          String[] entrada = new String[1000];
          int numEntrada = 0;
    
          //Leitura da entrada padrao
          do {
             entrada[numEntrada] = MyIO.readLine();
          } while (isFim(entrada[numEntrada++]) == false);
          numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM
    
          //Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
          for(int i = 0; i < numEntrada; i++){
             saida(entrada[i]);
          }
       }
}