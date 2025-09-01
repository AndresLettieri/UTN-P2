package tp4;

/**
 *
 * @author Andres
 */
public class TP4 {

    public static void main(String[] args) {
        Empleado emp1 = new Empleado("Andres","Programador");
        Empleado emp2 = new Empleado( "Matias", "Developer",2000);
        
        //Actualiza por porcentaje
        emp1.actualizarSalario(50);
        
        //actualiza por monto
        emp2.actualizarSalario(5000.0);
        
        System.out.println(emp1.toString());
        System.out.println(emp2.toString());        
        
        Empleado.mostrarTotalEmpleados();
    }
    
}
