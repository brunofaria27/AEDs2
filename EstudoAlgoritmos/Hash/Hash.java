class HashPrincipal {
    int tabela[];
    int m;
    final int NULO = -1;
 
    public HashPrincipal() {
       this(5);
    }
 
    public HashPrincipal(int m) {
       this.m = m;
       this.tabela = new int[this.m];
       for (int i = 0; i < m; i++) {
          tabela[i] = NULO;
       }
    }
 
    public int h(int elemento) {
       return elemento % 5;
    }
 
    public int reh(int elemento) {
       return ++elemento % 5;
    }
 
    public boolean inserir(int elemento) {
       boolean resp = false;
       if (elemento != NULO) {
          int pos = h(elemento);
          if (tabela[pos] == NULO) {
             tabela[pos] = elemento;
             resp = true;
          } else {
             pos = reh(elemento);
             if (tabela[pos] == NULO) {
                tabela[pos] = elemento;
                resp = true;
             }
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
          pos = reh(elemento);
          if (tabela[pos] == elemento) {
             resp = true;
          }
       }
       return resp;
    }

    public void mostrar(){
        System.out.println("-> TABELA HASH");
        for(int i=0; i < m ; i++){
            System.out.println(tabela[i]);
        }
    }
 }

public class Hash {
    public static void main(String[] args) {
        HashPrincipal hash = new HashPrincipal();
        hash.inserir(1);
        hash.inserir(6);
        hash.inserir(5);
        hash.inserir(10);
        hash.inserir(3);
        hash.inserir(4);

        hash.mostrar();
    }
}
