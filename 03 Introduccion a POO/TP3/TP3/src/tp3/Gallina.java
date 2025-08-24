package tp3;

/**
 *
 * @author Andres
 */
public class Gallina {
    private int idGallina;
    private int edad;
    private int huevosPuestos;

    public int getIdGallina() {
        return idGallina;
    }

    public void setIdGallina(int idGallina) {
        if (idGallina >= 0){
            this.idGallina = idGallina;
        }
    }
   
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad >= 0){
            this.edad = edad;
        }
    }

    public int getHuevosPuestos() {
        return huevosPuestos;
    }
    
    public void ponerHuevo(){
        huevosPuestos++;
    }
    
    public void envejecer(){
        edad++;
    }
    
    public void mostrarEstado(){
        System.out.println("Id: " + idGallina + ". Edad: " + edad + ". Huevos puestos: " + huevosPuestos);
    }
}
