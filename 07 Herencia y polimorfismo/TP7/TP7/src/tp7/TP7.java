package tp7;

import java.util.ArrayList;


public class TP7 {

    public static void main(String[] args) {
        System.out.println("1. Vehículos y herencia básica\n");
        Auto auto = new Auto(4,"Fiat","128\n");
        System.out.println(auto.mostrarInfo());
        
        System.out.println("2. Figuras geométricas y métodos abstractos\n");
        
        ArrayList<Figura> figura = new ArrayList<>();
        figura.add(new Circulo(5));
        figura.add(new Rectangulo(2, 4.5));
        
        for (Figura f : figura){
            System.out.println("Area del " + f.getNombre() + ": " + f.calcularArea());
        }
        
        System.out.println("\n");
        System.out.println("3. Empleados y polimorfismo\n");
        
        ArrayList<Empleado> empleados = new ArrayList<>();

        empleados.add(new EmpleadoPlanta("Ana", 3000));
        empleados.add(new EmpleadoTemporal("Luis", 15));
        empleados.add(new EmpleadoPlanta("Carlos", 3500));
        empleados.add(new EmpleadoTemporal("Sofía", 18));

        for (Empleado e : empleados) {
            System.out.println(e.toString());
        }
        
        System.out.println("\n");
        System.out.println("4. Animales y comportamiento sobrescrito\n");
        
        ArrayList<Animal> animales = new ArrayList<>();
        
        animales.add(new Gato());
        animales.add(new Perro());
        animales.add(new Vaca());

        for (Animal a : animales){
            a.describirAnimal();
            a.hacerSonido();
        }
        
        System.out.println("\n");
        System.out.println("5. Sistema de pagos con polimorfismo y genéricos\n");
        
        ArrayList<Pagable> medioDePago = new ArrayList<>();
        medioDePago.add(new TarjetaCredito("123456789"));
        medioDePago.add(new Transferencia("alias.1234"));
        medioDePago.add(new Efectivo());

        for (Pagable m : medioDePago) {
            ProcesadorPago.procesarPago(m);
        }
        
    }
    
}
