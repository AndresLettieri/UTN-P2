package tp5;

public class Bateria {
    private String marca;
    private double capacidad;
    
    public Bateria(String marca, double capacidad){
        this.marca = marca;
        this.capacidad = capacidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if (marca != "")
            this.marca = marca;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        if(capacidad >= 0)
            this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Bateria{" + "marca=" + marca + ", capacidad=" + capacidad + '}';
    }
    
    
}
