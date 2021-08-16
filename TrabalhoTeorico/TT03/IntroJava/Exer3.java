import java.util.*;

public class Exer3 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite um número: ");
        int num = entrada.nextInt();

        int i = 0;
        while(i <= num) {
            if(!(i % 2 == 0)) {
                System.out.println(i);
            }
            i++;
        }

        entrada.close();
    }
}
