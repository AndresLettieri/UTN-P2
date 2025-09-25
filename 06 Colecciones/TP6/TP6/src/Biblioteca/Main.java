
package Biblioteca;

public class Main {

    public static void main(String[] args) {
        
        // 1. Crear una biblioteca
        Biblioteca biblioteca = new Biblioteca("Alejandria");

        // 2. Crear al menos tres autores
        Autor autor1 = new Autor("1","Jorge Luis Borges","Argentina");
        Autor autor2 = new Autor("2","Paulo Coelho", "Brasil");
        Autor autor3 = new Autor("3","Stephen King", "Estados Unidos");

        // 3. Agregar 5 libros a la biblioteca
        Libro libro1 = new Libro("123456","La cifra", 1981, autor1);
        Libro libro2 = new Libro("333333","El alquimista", 1988, autor2);
        Libro libro3 = new Libro("456456","Brida", 1990, autor2);
        Libro libro4 = new Libro("888888","El resplandor", 1977, autor3);
        Libro libro5 = new Libro("444111","IT", 1986, autor3);


        // Agregar libros a la biblioteca

        biblioteca.agregarLibro(libro1.getIsbn(),libro1.getTitulo(), libro1.getAnioPublicacion(), libro1.getAutor());
        biblioteca.agregarLibro(libro2.getIsbn(),libro2.getTitulo(), libro2.getAnioPublicacion(), libro2.getAutor());
        biblioteca.agregarLibro(libro3.getIsbn(),libro3.getTitulo(), libro3.getAnioPublicacion(), libro3.getAutor());
        biblioteca.agregarLibro(libro4.getIsbn(),libro4.getTitulo(), libro4.getAnioPublicacion(), libro4.getAutor());
        biblioteca.agregarLibro(libro5.getIsbn(),libro5.getTitulo(), libro5.getAnioPublicacion(), libro5.getAutor());

        
        // 4. Listar todos los libros con su información
        System.out.println("Listado de libros:");
        biblioteca.listarLibros();

        // 5. Buscar un libro por su ISBN
        System.out.println("Buscando libro por ISBN:");
        Libro libroEncontrado = biblioteca.buscarLibroPorIsbn("888888");
        if (libroEncontrado != null) {
            libroEncontrado.mostrarInfo();
        } else {
            System.out.println("Libro no encontrado.");
        }

        // 6. Filtrar y mostrar los libros publicados en un año específico
        System.out.println("Libros del año 1986");
        for (Libro libroFiltrado : biblioteca.filtrarLibrosPorAnio(1986)){
            libroFiltrado.mostrarInfo();
        }


        // 7. Eliminar un libro por su ISBN y listar los libros restantes
        System.out.println("Eliminar libro ISBN: 123456");
        biblioteca.eliminarLibro("123456");
        biblioteca.listarLibros();

        // 8. Mostrar la cantidad total de libros en la biblioteca
        System.out.println("Cantidad total de libros en la biblioteca: " + biblioteca.obtenerCantidadLibros());

        // 9. Listar todos los autores de los libros disponibles
        biblioteca.mostrarAutoresDisponibles();
    }
}