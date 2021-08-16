import java.io.*;

class exer3B {
	public static void main (String[] args) throws Exception{
		RandomAccessFile raf = new RandomAccessFile("exemploRAF.txt", "rw");
		int inteiro = raf.readInt();
		double real = raf.readDouble();
		char caractere = raf.readChar();
		boolean boleano = raf.readBoolean();
		String str = raf.readLine();
		raf.close();
		System.out.println(inteiro + " " + real + " " + caractere + " " + boleano + " " + str);
	}
}
