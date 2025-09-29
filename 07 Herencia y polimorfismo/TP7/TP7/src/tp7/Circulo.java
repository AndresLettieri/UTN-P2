package tp7;

public class Circulo extends Figura {

    private double radio;
    public Circulo( int radio) {
        super("Circulo");
        this.radio = radio;
    }
    
    @Override
    public double calcularArea(){
        return Math.pow(radio,2) * Math.PI;
    }
    
}
