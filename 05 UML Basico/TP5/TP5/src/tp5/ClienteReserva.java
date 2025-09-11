package tp5;

public class ClienteReserva {
    private String nombre;
    private String telefono;
   
    public ClienteReserva(String nombre, String telefono){
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "ClienteReserva{" + "nombre=" + nombre + ", telefono=" + telefono + '}';
    }
    
    
}
