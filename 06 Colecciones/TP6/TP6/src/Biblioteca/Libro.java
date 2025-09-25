package Biblioteca;

import java.time.LocalDate;
import java.time.Year;

public class Libro {
    private String isbn;
    private String titulo;
    private int anioPublicacion;
    private Autor autor;

    public Libro(String isbn, String titulo, int anioPublicacion, Autor autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (!isbn.isEmpty()){
            this.isbn = isbn;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if(!titulo.isEmpty()){
            this.titulo = titulo;
        }
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        if (anioPublicacion <= LocalDate.now().getYear())
            this.anioPublicacion = anioPublicacion;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        if (autor != null){
            this.autor = autor;
        }
    }

    public void mostrarInfo(){
        System.out.println("Titulo: " + titulo 
                + "\n Año publicación: " + anioPublicacion
                + "\n Autor: " + autor.getNombre()
                + "\n ISBN: " + isbn);
    }
    @Override
    public String toString() {
        return "Libro{" + "isbn=" + isbn + ", titulo=" + titulo + ", anioPublicacion=" + anioPublicacion + ", autor=" + autor + '}';
    }
    
    
    
    
}
