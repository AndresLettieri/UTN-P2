package tp5;

public class CuentaBancaria {
    private String cbu;
    private double saldo;
    private TitularCuentaBancaria titularCuentaBancaria;
    private ClaveSeguridad claveSeguridad;
    
    public CuentaBancaria(String cbu, double saldo, int codigo, String ultimaModificacion){
        this.cbu = cbu;
        this.saldo = saldo;
        this.claveSeguridad = new ClaveSeguridad(codigo,ultimaModificacion);
    }
    
    public void setTitularCuentaBancaria(TitularCuentaBancaria titularCuentaBancaria){
        this.titularCuentaBancaria = titularCuentaBancaria;
        if (titularCuentaBancaria != null && titularCuentaBancaria.getCuentaBancaria() != this)
            titularCuentaBancaria.setCuentaBancaria(this);
    } 
    
    public void mostrarClaveSeguridad(){
        System.out.println("La cuenta bancaria " + cbu  + " tiene una clave " + claveSeguridad.getCodigo());

    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        if (cbu != "")
            this.cbu = cbu;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        if (saldo >= 0)
            this.saldo = saldo;
    }

    public TitularCuentaBancaria getTitularCuentaBancaria() {
        return titularCuentaBancaria;
    }

    public ClaveSeguridad getClaveSeguridad() {
        return claveSeguridad;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" + "cbu=" + cbu + ", saldo=" + saldo + ", titularCuentaBancaria=" + titularCuentaBancaria + ", claveSeguridad=" + claveSeguridad + '}';
    }
    
    
}
