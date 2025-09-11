
package tp5;

public class Libro {
    private String titulo;
    private String isbn;
    private Autor autor;
    private Editorial editorial;
    
    public Libro(String titulo, String isbn, Autor autor, Editorial editorial){
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.editorial = editorial;
    }
    
    public void mostrarEditorial(){
        if (editorial != null){
            System.out.println("El libro " + titulo + " pertenece a la editorial" + editorial.getNombre());
        }else{
            System.out.println("El libro " + titulo + " no tiene una editorial asignada.");
        }
    }
    
    public void setAutor(Autor autor){
        if (autor!= null)
            this.autor = autor;
    }
    
    public Autor getAutor(){
        return autor;
    }
    
    public void mostrarAutor(){
        if (autor != null){
            System.out.println("El autor del libro " + titulo + " es " + autor.getNombre());
        }else{
            System.out.println("El libro " + titulo + " no tiene un autor asignado.");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if(titulo!="")
            this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (isbn!="") 
            this.isbn = isbn;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "Libro{" + "titulo=" + titulo + ", isbn=" + isbn + ", autor=" + autor + ", editorial=" + editorial + '}';
    }
    
    
}
