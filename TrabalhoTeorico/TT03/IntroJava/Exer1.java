import java.util.*;

public class Exer1 {
    
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        double[] notas = new double[5];

        for(int i = 0; i < notas.length; i++) {
            System.out.print("Digite a nota do aluno " + (i + 1) + ": ");
            notas[i] = entrada.nextDouble();
        }

        // Soma
        double soma = 0;
        for(int i = 0; i < notas.length; i++) {
            soma += notas[i];
        }

        System.out.println("A média das notas é: " + soma/5);
        entrada.close();
    }
}
