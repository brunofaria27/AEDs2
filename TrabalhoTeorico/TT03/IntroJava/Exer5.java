import java.util.*;

public class Exer5 {
    
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Digite a nota máxima da prova: ");
        int notaMax = entrada.nextInt();

        double[] notaAlunos = new double[20];

        for(int i = 0; i < notaAlunos.length; i++) {
            System.out.print("Digite a nota do aluno " + (i + 1) + ": ");
            notaAlunos[i] = entrada.nextDouble();

            while(notaAlunos[i] < 0 || notaAlunos[i] > notaMax) {
                System.out.print("Digite a nota do aluno " + (i + 1) + ", novamente: ");
                notaAlunos[i] = entrada.nextDouble();
            }
        }

        // Média da turma
        double soma = 0.0;
        for(int i = 0; i < notaAlunos.length; i++) {
            soma += notaAlunos[i];
        }

        System.out.println("A média das notas é " + soma/20);

        // Alunos abaixo da média
        int count = 0;
        for(int i = 0; i < notaAlunos.length; i++) {
            if(notaAlunos[i] < (notaMax * 0.6)) {
                count++;
            }
        }

        System.out.println("A quantidade de alunos abaixo da média é: " + count);

        // Alunos conceito A
        count = 0;
        for(int i = 0; i < notaAlunos.length; i++) {
            if(notaAlunos[i] > (notaMax * 0.9)) {
                count++;
            }
        }

        System.out.println("A quantidade de alunos conceito A é: " + count);

        entrada.close();
    }
}
