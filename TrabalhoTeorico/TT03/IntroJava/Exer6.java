import java.util.*;

public class Exer6 {
    public static int fibonacci(int n) {
        if (n < 2) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int num = entrada.nextInt();

        for (int i = 0; i < num; i++) {
            System.out.println(fibonacci(i) + " ");
        }

        entrada.close();
    }
}