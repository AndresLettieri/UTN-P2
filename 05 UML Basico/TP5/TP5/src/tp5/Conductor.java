package tp5;

public class Conductor {
    private String nombre;
    private String licencia;
    private Vehiculo vehiculo;
    
    public Conductor(String nombre, String licencia){
        this.nombre = nombre;
        this.licencia = licencia;
    }
    
    public void setVehiculo(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
        if (vehiculo != null && vehiculo.getConductor() != this)
            vehiculo.setConductor(this);
    }
    
    public Vehiculo GetVehiculo(){
        return vehiculo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre!="")
            this.nombre = nombre;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        if (licencia!="")
            this.licencia = licencia;
    }

    @Override
    public String toString() {
        return "Conductor{" + "nombre=" + nombre + ", licencia=" + licencia + ", vehiculo=" + vehiculo + '}';
    }
    
    
    
    
    
}
