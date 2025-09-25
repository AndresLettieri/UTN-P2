package Biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Biblioteca {
    private String nombre;
    List<Libro> libros;

    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.libros = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (!nombre.isEmpty()){
            this.nombre = nombre;
        }
    }

    public List<Libro> getLibros() {
        return libros;
    }
    
    public void agregarLibro (String isbn, String titulo, int anioPublicacion, Autor autor){
        if (isbn.isEmpty() || titulo.isEmpty() || anioPublicacion > LocalDate.now().getYear() || autor == null){
            System.out.println("Verifique los datos ingresados para crear un nuevo libro.");
        }else{
            Libro libro = new Libro(isbn, titulo, anioPublicacion, autor);
            libros.add(libro);
            System.out.println("Libro ingresado con éxito.");
        }
    }
    
    public void listarLibros(){
        for (Libro l : libros){
            l.mostrarInfo();
        }
    }
    
    public Libro buscarLibroPorIsbn(String isbn){
        int i = 0;
        Libro libroEncontrado = null;
        while (i < libros.size() && !this.libros.get(i).getIsbn().equals(isbn)) {
            i++;
        }
        if (i < libros.size()) {
            libroEncontrado = this.libros.get(i);
        }
        return libroEncontrado;
    }
    
    public void eliminarLibro(String isbn){
        Libro libroABorrar = buscarLibroPorIsbn(isbn);
        if (libroABorrar != null){
        this.libros.remove(libroABorrar);
        System.out.println("Se elimino el libro " + libroABorrar.getTitulo());
        }else{
            System.out.println("No se encontro el libro a eliminar.");
        }
    }
    
    public int obtenerCantidadLibros(){
        return libros.size();
    }
    
    public ArrayList<Libro> filtrarLibrosPorAnio(int anio){
        ArrayList<Libro> librosFiltrados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getAnioPublicacion() == anio) {
                librosFiltrados.add(libro);
            }
        }
        return librosFiltrados;
    }
    
    public void mostrarAutoresDisponibles(){
         HashSet<String> autoresUnicos = new HashSet<>();
        
        for (Libro libro : libros) {
            autoresUnicos.add(libro.getAutor().getNombre());
        }

        System.out.println("Autores disponibles:");
        for (String autor : autoresUnicos) {
            System.out.println(autor);
        }
    }
    

    
    
    
    
    
    
}
