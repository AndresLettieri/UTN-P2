package tp3;

/**
 *
 * @author Andres
 */
public class NaveEspacial {
    private String nombre;
    private int combustible;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.trim() != ""){
            this.nombre = nombre;
        }
    }

    public int getCombustible() {
        return combustible;
    }
    

    public void despegar(){
        if (combustible > 0){
            System.out.println("Despegando!");
        }
        else{
            System.out.println("No alcanza el combustible. Debe recargar");
        }
    }
    
    public void avanzar(int distancia){
        if (alcanzaCombustible(distancia)){
        System.out.println("Se avanzó " + distancia + " espacios.");
        combustible -= distancia;
        }
        else{
            System.out.println("No alcanza el combustible. Disponible: " + combustible);
        }
    }
    
    private boolean alcanzaCombustible(int distancia){
        return combustible - distancia >= 0;
    }
    
    public void recargarCombustible(int cantidad){
        if (cantidad > 0 && limiteCombustible(cantidad)){
            combustible += cantidad;
        }
        else{
            System.out.println("No se pueden superar las 50 unidades de combustible!");
        }
    }
    
    private boolean limiteCombustible(int cantidad){
        return combustible + cantidad <= 50;
    }
    
    public void mostrarEstado(){
        System.out.println("Nombre: " + nombre + ". Combustible disponible: " + combustible);
    }
    
}
