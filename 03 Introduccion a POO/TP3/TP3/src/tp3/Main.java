
package tp3;


/**
 *
 * @author Andres
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("--------Ejercicio1--------");
        ejercicio1();
        System.out.println("--------Ejercicio2--------");
        ejercicio2();
        System.out.println("--------Ejercicio3--------");
        ejercicio3();
        System.out.println("--------Ejercicio4--------");
        ejercicio4();
        System.out.println("--------Ejercicio5--------");
        ejercicio5();
    }
    
    private static void ejercicio1(){
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Andres");
        estudiante.setApellido("Lettieri");
        estudiante.setCurso("Programacion 2");
        estudiante.mostrarInfo();
        estudiante.subirCalificacion(10);
        estudiante.mostrarInfo();
        estudiante.bajarCalificacion(0.5);
        estudiante.mostrarInfo();
    }
    
    private static void ejercicio2(){
        Mascota mascota = new Mascota();
        mascota.setNombre("Bobi");
        mascota.setEspecie("Perro");
        mascota.setEdad(4);
        mascota.mostrarInfo();
        mascota.cumplirAnios();
        mascota.mostrarInfo();
        mascota.cumplirAnios();
        mascota.mostrarInfo();
        mascota.cumplirAnios();
    }
    
    private static void ejercicio3(){
        Libro libro = new Libro();
        libro.setTitulo("El resplandor");
        libro.setAutor("Stephen King");
        libro.setAnioPublicacion(1980);
        System.out.println(libro.getAnioPublicacion());
        libro.setAnioPublicacion(2050);
        libro.setAnioPublicacion(1977);
        System.out.println(libro.getAnioPublicacion());

    }
        
    private static void ejercicio4(){
        Gallina gallina1 = new Gallina();
        Gallina gallina2 = new Gallina();
        gallina1.setIdGallina(1);
        gallina1.setEdad(3);
        gallina2.setIdGallina(2);
        gallina2.setEdad(4);
        gallina1.mostrarEstado();
        gallina2.mostrarEstado();
        gallina1.envejecer();
        gallina2.envejecer();
        gallina1.ponerHuevo();
        gallina1.ponerHuevo();
        gallina1.ponerHuevo();
        gallina2.ponerHuevo();
        gallina2.ponerHuevo();
        gallina1.mostrarEstado();
        gallina2.mostrarEstado();
        

    }
        
    private static void ejercicio5(){
        NaveEspacial nave = new NaveEspacial();
        nave.setNombre("Nodriza");
        nave.despegar();
        nave.avanzar(1);
        nave.recargarCombustible(50);
        nave.mostrarEstado();
        nave.avanzar(5);
        nave.avanzar(15);
        nave.avanzar(40);
        nave.mostrarEstado();

    }
    
}
