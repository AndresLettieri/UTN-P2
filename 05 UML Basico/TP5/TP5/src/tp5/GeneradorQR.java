
package tp5;

public class GeneradorQR {
    public void generar(String valor, UsuarioQR usuarioQR){
        CodigoQR codigoQR = new CodigoQR(valor, usuarioQR);
        System.out.println(codigoQR.toString());
    }
    
}
