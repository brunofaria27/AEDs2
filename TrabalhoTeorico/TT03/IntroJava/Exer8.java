import java.util.*;

public class Exer8 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int[] array = new int[1000];
      
        System.out.println("Digite quantos numeros deseja");
        int num = entrada.nextInt();

        System.out.println("Comece a digitar os numeros: ");
        for(int i = 0; i < num; i++) {
            array[i] = entrada.nextInt();
        }

        System.out.println("Os numeros armazenados foram: ");
        for(int i = 0; i < num; i++) {
            System.out.println(array[i]);
        }

        entrada.close();
    }
}
