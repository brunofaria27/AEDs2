import java.util.*;

public class exer15 {
    private static void mover(int O, int D) {
        System.out.println(O + " -> " + D);
    }

    static void hanoi(int n, int Origem, int Destino, int Trabalho) {
        if (n > 0) {
            hanoi(n - 1, Origem, Trabalho, Destino);
            mover(Origem, Destino);
            hanoi(n - 1, Trabalho, Destino, Origem);
        }
    }

    public static void main(String[] args) {
        int n;
        Scanner entrada = new Scanner(System.in);

        System.out.println("Digite o número de discos: ");
        n = entrada.nextInt();

        hanoi(n, 1, 3, 2);
    }
}