package tp7;

public class Transferencia implements Pagable {
    private String alias;

    public Transferencia(String alias) {
        this.alias = alias;
    }

    @Override
    public void pagar() {
        System.out.println("Pagando por transferencia al alias " + alias);
    }
}