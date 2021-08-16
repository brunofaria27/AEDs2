class Ponto{
    //Atributos
    private double x;
    private double y;
    private int id;
    private static int nextID = 0;

    // Construtores
    public Ponto() {
        this.x = 0;
        this.y = 0;
        this.id = Ponto.nextID;
        Ponto.nextID++;
    }

    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
        this.id = Ponto.nextID;
        Ponto.nextID++;
    }

    // SETS
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    // GETS
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getID() {
        return this.id;
    }

    public static int getNextID() {
        return nextID;
    }

    public double getAreaRetangulo(Ponto p){
        return 0;
    }


    //METODOS

    public double dist(Ponto p){
        double dx = Math.pow( (this.x - p.getX()) , 2);
        double dy = Math.pow( (this.y - p.getY()) , 2); 
        return Math.sqrt(dx + dy);
    }

    public double dist(double x, double y){
        double dx = Math.pow( (this.x - x) , 2);
        double dy = Math.pow( (this.y - y) , 2);  
        return Math.sqrt(dx + dy);
    }

    public static double dist(double x1, double y1, double x2, double y2){
        double dx = Math.pow( (x1 - x2) , 2);
        double dy = Math.pow( (y1 - y2) , 2);  
        return Math.sqrt(dx + dy);
    }

    // para verificar se é triangulo , jogar pontos em uma matriz
    // se sua determinante for == 0, pontos pertencem a mesma reta
    // isTriangulo == true caso determinante != 0
    public static boolean isTriangulo(Ponto p1, Ponto p2, Ponto p3){
        double diag1 = p1.getX() * p2.getY() *1; // diagonal primaria
        double diag2 = p1.getY() * 1 * p3.getX(); // diagonal primaria
        double diag3 = 1 * p2.getX()* p3.getY(); // diagonal primaria
        double diag4 = p1.getY()* p2.getX() * 1; // diagonal secundaria
        double diag5 = p1.getX() * 1 * p3.getY(); // diagonal secundaria
        double diag6 = 1 * p2.getY() * p3.getX(); //  // diagonal secundaria

        double determinante = diag1 + diag2 + diag3 - diag4 - diag5 - diag6;

        return (determinante != 0);
    }

}

public class LixaoPonto {
    public static void main (String[] args){
        Ponto p1 = new Ponto(4,3);
        Ponto p2 = new Ponto(8,5);
        Ponto p3 = new Ponto(9.2,10);
        
        System.out.println("Distancia p1 entre e p2: " + p1.dist(p2));
        System.out.println("Distancia p1 entre e (5,2): " + p1.dist(5,2));
        System.out.println("Distancia (4,3) entre e (5,2): " + Ponto.dist(4,3,5,2));
        System.out.println("P1, P2, P3 sao triangulo:" + Ponto.isTriangulo(p1,p2,p3));
        System.out.println("Area retangulo:" + p1.getAreaRetangulo(p2));
        System.out.println("ID de P1: " + p1.getID());
        System.out.println("ID de P2: " + p2.getID());
        System.out.println("ID de P3: " + p3.getID());
        System.out.println("Next ID: " + Ponto.getNextID());
        }
}