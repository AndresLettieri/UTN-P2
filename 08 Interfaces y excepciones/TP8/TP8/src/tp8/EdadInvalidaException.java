
package tp8;

public class EdadInvalidaException extends RuntimeException {

    public EdadInvalidaException() {
        System.out.println("La edad debe ser entre 0 y 120");
    }

    public EdadInvalidaException(String message) {
        super(message);
    }

    public EdadInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }

    public EdadInvalidaException(Throwable cause) {
        super(cause);
    }
    
}
