
package tp3;


/**
 *
 * @author Andres
 */
public class Libro {
    
    private String titulo;
    private String autor;
    private int anioPublicacion;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        if (anioPublicacion <= java.time.Year.now().getValue() && anioPublicacion > 0 ){
            this.anioPublicacion = anioPublicacion;
        }
        else{
            System.out.println("Año invalido. No puede ser mayor al año en curso.");
        }
    }


}
