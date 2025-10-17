package tp8.Ejercicio1;

public class TarjetaCredito implements PagoConDescuento, Pago{

    @Override
    public void aplicarDescuento(double descuento) {
        System.out.println("Se aplica descuento con tarjeta del " + descuento + "%");
    }

    @Override
    public void procesarPago(double monto) {
        System.out.println("Pago con tarjeta de $" + monto);
    }
    
}
