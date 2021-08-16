import java.io.*;

class exer3A {
	public static void main (String[] args) throws Exception{
		RandomAccessFile raf = new RandomAccessFile("exemploRAF.txt", "rw");
		raf.writeInt(1);
		raf.writeDouble(5.3);
		raf.writeChar('X');
		raf.writeBoolean(true);
		raf.writeBytes("Algoritmos");
		raf.close();
	}
}
