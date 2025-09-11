package tp5;

public class Propietario {
    private String nombre;
    private String dni;
    private Computadora computadora;
    
    public Propietario(String nombre, String dni){
        this.nombre = nombre;
        this.dni = dni;
    }
    
    public void setComputadora(Computadora computadora){
        this.computadora = computadora;
        if (computadora != null && computadora.GetPropietario() != this)
            computadora.SetPropietario(this);
    }
    
        
    public Computadora getComputadora(){
        return computadora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre!="")
            this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if(dni!="")
            this.dni = dni;
    }

    @Override
    public String toString() {
        return "Propietario{" + "nombre=" + nombre + ", dni=" + dni + ", computadora=" + computadora + '}';
    }
    
    
    
}
