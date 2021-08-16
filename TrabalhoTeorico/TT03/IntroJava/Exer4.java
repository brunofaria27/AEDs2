import java.util.*;

public class Exer4 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite um número: ");
        int num = entrada.nextInt();

        int i = 1;
        while(i <= num) {
            System.out.println(i);
            i = i + 4;
            System.out.println(i);
            i = i + 7;
        }

        entrada.close();
    }
}