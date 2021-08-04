class aquecimento {
   public static boolean isMaiuscula (char c){
      return (c >= 'A' && c <= 'Z');
   }

   public static boolean isFim(String s){
      return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }

   public static int contarLetrasMaiusculas (String s, int pos){
      // Se a minha posição for maior que o s.lenght eu posso verificar
      if(pos < s.length()) {
         // Se a letra for maiuscula
         if(isMaiuscula(s.charAt(pos)) == true) {
            // vou ter 1 maiuscula e terei que continuar verificando a string a frente
            return 1 + contarLetrasMaiusculas(s, pos + 1);
         } else {
            // Se n for maiuscula, apenas irei verificar a proxima letra
            return contarLetrasMaiusculas(s, pos + 1);
         }
      }
      return 0; // para qualquer coisa que eu retornar n faça diferença na soma 
   }

   public static void main (String[] args){
      String[] entrada = new String[1000];
      int numEntrada = 0;

      //Leitura da entrada padrao
      do {
         entrada[numEntrada] = MyIO.readLine();
      } while (isFim(entrada[numEntrada++]) == false);
      numEntrada--;   //Desconsiderar ultima linha contendo a palavra FIM

      //Para cada linha de entrada, gerando uma de saida contendo o numero de letras maiusculas da entrada
      for(int i = 0; i < numEntrada; i++){
         MyIO.println(contarLetrasMaiusculas(entrada[i], 0));
      }
   }
}
