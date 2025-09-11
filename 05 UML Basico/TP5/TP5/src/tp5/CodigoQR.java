package tp5;

public class CodigoQR {

  private String valor;
    private UsuarioQR usuarioQR;
    
    public CodigoQR(String valor, UsuarioQR usuarioQR){
        this.valor = valor;
        this.usuarioQR = usuarioQR;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        if (valor!="")
            this.valor = valor;
    }

    public UsuarioQR getUsuarioQR() {
        return usuarioQR;
    }

    public void setUsuarioQR(UsuarioQR usuarioQR) {
        if (usuarioQR != null)
            this.usuarioQR = usuarioQR;
    }

    @Override
    public String toString() {
        return "GeneradorQR{" + "valor=" + valor + ", usuarioQR=" + usuarioQR + '}';
    }
      
}
