package tp5;

public class TarjetaDeCredito {
    
    private String numero;
    private String fechaVencimiento;
    private Cliente cliente;
    private Banco banco;
    
    public TarjetaDeCredito(String numero, String fechaVencimiento,Banco banco){
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.banco = banco;
    }
    
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
        if (cliente != null && cliente.getTarjetaDeCredito() != this)
            cliente.setTarjetaDeCredito(this);
    }
    
    public Cliente getCliente(){
        return cliente;
    }

    public void mostrarBanco(){
        if (banco != null){
            System.out.println("La tarjeta de credito " + numero + " es del banco " + banco.getNombre());
        }else{
            System.out.println("La tarjeta de credito " + numero  + " no tiene un banco asignado.");
        }
    }
        
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if (numero!="")
            this.numero = numero;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        if(fechaVencimiento!="")
            this.fechaVencimiento = fechaVencimiento;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    @Override
    public String toString() {
        return "TarjetaDeCredito{" + "numero=" + numero + ", fechaVencimiento=" + fechaVencimiento + ", cliente=" + cliente + ", banco=" + banco + '}';
    }
    
    
   
    
}
