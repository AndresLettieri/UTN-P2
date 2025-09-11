package tp5;
public class UsuarioQR {
    private String nombre;
    private String email;
    
    public UsuarioQR(String nombre, String email){
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != "")
            this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != "")
            this.email = email;
    }

    @Override
    public String toString() {
        return "UsuarioQR{" + "nombre=" + nombre + ", email=" + email + '}';
    }
    
    
}
