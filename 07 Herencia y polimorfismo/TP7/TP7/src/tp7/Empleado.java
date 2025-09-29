package tp7;

public abstract class Empleado {
    
    private String nombre;
    private String tipo;

    public Empleado(String nombre) {
        this.nombre = nombre;
        tipo = (this instanceof EmpleadoPlanta ? "Planta" : "Externo");
    }
    
    public abstract double calcularSueldo();

    @Override
    public String toString() {
      
        return "Empleado{" + "nombre=" + nombre + ". Tipo= " + tipo + ". Sueldo= " + calcularSueldo() + '}';
    }
    
    
}
