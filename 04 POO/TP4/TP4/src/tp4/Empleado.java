
package tp4;

/**
 *
 * @author Andres
 */
public class Empleado {
    private int id;
    private String nombre;
    private String puesto;
    private double salario;
    private static int totalEmpleados;

    public Empleado(String nombre, String puesto, double salario) {
        id = totalEmpleados + 1;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        totalEmpleados++;
    }
    
    public Empleado(String nombre, String puesto){
        id = totalEmpleados + 1;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = 1000;
        totalEmpleados++;
    }
    
    public void actualizarSalario(int porcentaje){
        this.salario += this.salario * porcentaje /100; 
    }
    
    public void actualizarSalario(double monto){
        this.salario += monto;
    }
    
    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre=" + nombre + ", puesto=" + puesto + ", salario=" + salario + '}';
    }
    
    public static void mostrarTotalEmpleados(){
        System.out.println("Total de emplados hasta el momento: " + totalEmpleados);
    }

}
