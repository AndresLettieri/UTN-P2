package tp5;

public class TitularCuentaBancaria {
    private String nombre;
    private String dni;
    private CuentaBancaria cuentaBancaria;
    
    public TitularCuentaBancaria(String nombre, String dni){
        this.nombre = nombre;
        this.dni = dni;
    }
    
    public void setCuentaBancaria(CuentaBancaria cuentaBancaria){
        this.cuentaBancaria = cuentaBancaria;
        if (cuentaBancaria != null && cuentaBancaria.getTitularCuentaBancaria() != this)
            cuentaBancaria.setTitularCuentaBancaria(this);
    } 
    
    public CuentaBancaria getCuentaBancaria(){
        return cuentaBancaria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNomnbre(String nombre) {
        if (nombre != "")
            this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni != "")
            this.dni = dni;
    }

    @Override
    public String toString() {
        return "TitularCuentaBancaria{" + "nombre=" + nombre + ", dni=" + dni + ", cuentaBancaria=" + cuentaBancaria + '}';
    }
    
    
    
    
}
