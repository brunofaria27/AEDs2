class Hash {
    int tabela[];
    int m1, m2, m, reserva;
    final int NULO = -1;
 
    public Hash() {
       this(4, 3);
    }
 
    public Hash(int m1, int m2) {
       this.m1 = m1;
       this.m2 = m2;
       this.m = m1 + m2;
       this.tabela = new int[this.m];
       for (int i = 0; i < m1; i++) {
          tabela[i] = NULO;
       }
       reserva = 0;
    }
 
    public int h(int elemento) {
       return elemento % m;
    }
 
    public boolean inserir(int elemento) {
       boolean resp = false;
       if (elemento != NULO) {
          int pos = h(elemento);
          if (tabela[pos] == NULO) {
             tabela[pos] = elemento;
             resp = true;
          } else if (reserva < m2) {
             tabela[m1 + reserva] = elemento;
             reserva++;
             resp = true;
          }
       }
       return resp;
    }
 
    public boolean pesquisar(int elemento) {
       boolean resp = false;
       int pos = h(elemento);
       if (tabela[pos] == elemento) {
          resp = true;
       } else if (tabela[pos] != NULO) {
          for (int i = 0; i < reserva; i++) {
             if (tabela[m1 + i] == elemento) {
                resp = true;
                i = reserva;
             }
          }
       }
       return resp;
    }
 
    public void mostrar(){
        System.out.println("-> TABELA HASH");
        for(int i=0; i < m ; i++){
            if(i == 4){ System.out.println("-> AREA DE RESERVA"); }
            System.out.println(tabela[i]);
        }
    }
 }

public class Reserva {
    public static void main(String[] args) {
        Hash hash = new Hash();
        hash.inserir(3);
        hash.inserir(7);
        hash.inserir(8);
        hash.inserir(1);
        hash.inserir(2);
        hash.inserir(5);
        hash.inserir(9);

        hash.mostrar();
    }
}
