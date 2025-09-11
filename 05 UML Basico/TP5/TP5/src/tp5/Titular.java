package tp5;

public class Titular {
    private String nombre;
    private String dni;
    private Pasaporte pasaporte;
    
    public Titular(String nombre, String dni){
        this.nombre = nombre;
        this.dni = dni;
    }
    
    public void setPasaporte(Pasaporte pasaporte){
        this.pasaporte = pasaporte;
        if (pasaporte != null && pasaporte.getTitular() != this)
            pasaporte.setTitular(this);
    }

    @Override
    public String toString() {
        return "Titular{" + "nombre=" + nombre + ", dni=" + dni + ", pasaporte=" + pasaporte.toString() + '}';
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public Pasaporte getPasaporte() {
        return pasaporte;
    }

    public void setNombre(String nombre) {
        if (nombre != "")
            this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni != "")
            this.dni = dni;
    }
    
    
}
