package tp7;

public class Efectivo implements Pagable {
    
    @Override
    public void pagar() {
        System.out.println("Pagando en efectivo.");
    }
}

