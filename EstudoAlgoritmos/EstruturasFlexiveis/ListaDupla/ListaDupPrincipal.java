
public class ListaDupPrincipal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CListaDup LD = new CListaDup();
		LD.insereFim(0);
		LD.insereFim(7);		
		LD.insereFim(3);
		LD.insereFim(9);
		LD.insereFim(8);
		LD.imprime();
		System.out.println(LD.retornaItem());
	}
}
