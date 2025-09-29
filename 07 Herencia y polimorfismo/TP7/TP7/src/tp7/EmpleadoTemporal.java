package tp7;

public class EmpleadoTemporal extends Empleado {
    
    final double SUELDO_POR_DIA = 25.5;
    private int cantDiasTrabajados;

    public EmpleadoTemporal(String nombre, int cantDiasTrabajados) {
        super(nombre);
        this.cantDiasTrabajados = cantDiasTrabajados;
    }

    @Override
    public double calcularSueldo() {
        return cantDiasTrabajados * SUELDO_POR_DIA;
    }

    
    
}
