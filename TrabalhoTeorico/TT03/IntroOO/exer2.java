class Retangulo {
    //atributos
    private  double base;
    private  double altura;

    //Construtores
    public Retangulo(){
        this.base = 0;
        this.altura = 0;
    }

    public Retangulo(double base, double altura){
        this.base = base;
        this.altura = altura;
    }

    //Metodos sets
    public void setBase(double base){
        this.base = base;
    }

    public void setAltura(double altura){
        this.altura = altura;
    }

    //Metodos Get
    public double getArea(){
        return this.base*this.altura;
    }

    public double getPerimetro(){
        return 2*(this.base+this.altura);
    }

    public double getDiagonal(){
        return Math.sqrt( (Math.pow(this.base, 2) + (Math.pow(this.altura, 2)) ) );
    }


}

class exer2 {
    public static void main(String[] args) {
        Retangulo r1 = new Retangulo(7,3);
        Retangulo r2 = new Retangulo();
        r2.setBase(10);
        r2.setAltura(2);

        MyIO.println("Area retangulo 1 = "+r1.getArea());
        MyIO.println("Perimetro retangulo 1 = "+r1.getPerimetro());
        MyIO.println("Diagonal retangulo 1 = "+r1.getDiagonal());

        MyIO.println("Area retangulo 2 = "+r2.getArea());
        MyIO.println("Perimetro retangulo 2 = "+r2.getPerimetro());
        MyIO.println("Diagonal retangulo 2 = "+r2.getDiagonal());
    }
}