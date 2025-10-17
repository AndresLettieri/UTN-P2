package tp8.Ejercicio1;

public class PayPal implements PagoConDescuento, Pago {

    @Override
    public void aplicarDescuento(double descuento) {
        System.out.println("Se aplica descuento con paypal del " + descuento + "%");
    }

    @Override
    public void procesarPago(double monto) {
        System.out.println("Pago con paypal de $" + monto);
    }
    
}
