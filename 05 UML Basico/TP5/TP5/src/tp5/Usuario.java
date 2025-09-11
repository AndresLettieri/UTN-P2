
package tp5;

public class Usuario {
    private String nombre;
    private String dni;
    private Celular celular;
    
    public Usuario(String nombre, String dni){
        this.nombre = nombre;
        this.dni = dni;
    }
    
    public void setCelular(Celular celular){
        this.celular = celular;
        if (celular != null && celular.getUsuario() != this)
            celular.setUsuario(this);
    }
        
        
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Celular getCelular() {
        return celular;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", dni=" + dni + ", celular=" + celular.toString() + '}';
    }

    
    
}
