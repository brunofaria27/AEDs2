import java.util.*;

public class Motivacao1 {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Quantas notas quer ler: ");
        int n = entrada.nextInt();

        double[] notas = new double[n];

        for(int i = 0; i < notas.length; i++) {
            System.out.print("Digite a nota do aluno " + (i + 1) + ": ");
            notas[i] = entrada.nextDouble();
        }

        // Soma
        double soma = 0;
        for(int i = 0; i < notas.length; i++) {
            soma += notas[i];
        }
        double media = soma / 5;

        for(int i = 0; i < notas.length; i++) {
            if(notas[i] > media) {
                System.out.println(notas[i]);
            }
        }

        entrada.close();
    }
}
