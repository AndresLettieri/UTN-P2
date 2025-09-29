package tp7;

public class ProcesadorPago {
    public static <T extends Pagable> void procesarPago(T medio) {
        medio.pagar();
    }
}