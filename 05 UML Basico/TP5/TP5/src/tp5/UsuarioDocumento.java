package tp5;

public class UsuarioDocumento {
    private String nombre;
    private String email;
    
    public UsuarioDocumento(String nombre, String email){
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UsuarioDocumento{" + "nombre=" + nombre + ", email=" + email + '}';
    }
    
    
}
