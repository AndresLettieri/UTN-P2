package tp7;

public class EmpleadoPlanta extends Empleado {
    
    private double sueldo;
    
    public EmpleadoPlanta(String nombre, double sueldo) {
        super(nombre);
        this.sueldo = sueldo;
    }

    @Override
    public double calcularSueldo() {
        return sueldo;
    }
    

    
    
    
}
