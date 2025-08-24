
package tp3;

/**
 *
 * @author Andres
 */
public class Mascota {
    private String nombre;
    private String especie;
    private int edad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad >= 0){
            this.edad = edad;
        }
    }
    
    public void mostrarInfo(){
        System.out.println("Nombre: " + nombre + ". Especie: " + especie + ". Edad: " + edad);
    }
    
    public void cumplirAnios(){
        edad += 1;
    }
    
    
}
