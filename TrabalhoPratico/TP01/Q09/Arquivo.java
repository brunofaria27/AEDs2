import java.io.*;
import java.util.*;

public class Arquivo {
    // função que abre o arquivo em modo de escrita e leitura escreve todas as entradas no file fecha o arquivo.
    public static void saveArquivo(int n, double d) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("exemplo.txt", "rw");
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            d = MyIO.readDouble();
            raf.writeDouble(d);
        }
        raf.close();
        input.close();
    }

    // printaContrario função que abre o arquivo em leitura binaria e vai pro final do arquivo le as entradas e printa na tela fecha o arquivo.
    public static void printArquivo(int n) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("exemplo.txt", "rw");
        int i = 0;
        double d = 0.00;

        for (int j = 0; j < n; n--) {
            raf.seek((n - 1) * 8);
            d = raf.readDouble();
            i = (int) d;
            if (d == (long) d)
                MyIO.println(i);
            else {
                MyIO.println(d);
            }
        }
        raf.close();
    }

    public static void main(final String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int n = 0;
        n = MyIO.readInt();
        double d = 0.0;
        saveArquivo(n, d);
        printArquivo(n);
        input.close();
    }
}