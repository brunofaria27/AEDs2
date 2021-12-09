public class Principal {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria arvore = new ArvoreBinaria();

        arvore.inserir(5);
        arvore.inserir(8);
        arvore.inserir(45);
        arvore.inserir(18);
        arvore.inserir(2);
        arvore.inserir(16);
        arvore.inserir(9);
        arvore.inserir(31);
        arvore.inserir(23);
        arvore.inserir(40);

        System.out.println(arvore.misterio());
    }
}
