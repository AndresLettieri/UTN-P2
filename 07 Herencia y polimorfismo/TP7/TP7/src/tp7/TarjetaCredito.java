package tp7;

public class TarjetaCredito implements Pagable{
    private String numero;
    
    public TarjetaCredito(String numero) {
        this.numero = numero;
    }

    @Override
    public void pagar() {
        System.out.println("Pagando con tarjeta de credito: " + numero);
    }
    
}
