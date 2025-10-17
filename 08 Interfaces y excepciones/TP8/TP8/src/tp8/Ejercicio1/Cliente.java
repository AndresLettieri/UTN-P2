package tp8.Ejercicio1;
public class Cliente implements Notificable{
    private String nombre;

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public void notificarCambio(String mensaje) {
         System.out.println("Notificación para " + nombre + ": " + mensaje);
    }
    
    
}
