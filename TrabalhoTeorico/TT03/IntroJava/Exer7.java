import java.util.*;

public class Exer7 {
    
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String[] nome = new String[5];
        double[] notas = new double[5];

        for(int i = 0; i < 5; i++) {
            System.out.println("Digite o nome do aluno " + (i + 1));
            nome[i] = entrada.nextLine();
        }

        for(int i = 0; i < 5; i++) {
            System.out.println("Digite a nota do aluno " + (i + 1));
            notas[i] = entrada.nextDouble();
        }

        double media = 0.0;
        double soma = 0.0;
        for(int i = 0; i < 5; i++) {
            soma = soma + notas[i];
        }
        media = soma / 5;

        for(int i = 0; i < 5; i++) {
            if(notas[i] > media) {
                System.out.println("O aluno " + nome[i] + " tem uma boa média");
            }
        }
        entrada.close();
    }
}
