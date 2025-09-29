package tp7;

public class Auto extends Vehiculo {
    private int cantidadPuertas;

    public Auto(int cantidadPuertas, String marca, String modelo) {
        super(marca, modelo);
        this.cantidadPuertas = cantidadPuertas;
    }

    public int getCantidadPuertas() {
        return cantidadPuertas;
    }

    public void setCantidadPuertas(int cantidadPuertas) {
        if (cantidadPuertas>0) this.cantidadPuertas = cantidadPuertas;
    }
    
    @Override
    public String mostrarInfo(){
        return super.mostrarInfo() + ". Cantidad de puertas: " + cantidadPuertas;
    }

    @Override
    public String toString() {
        return "Auto{" + "cantidadPuertas=" + cantidadPuertas + '}' + super.mostrarInfo();
    }
    
    
    
    
    
    
}
