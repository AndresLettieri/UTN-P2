package tp5;

public class FirmaDigital {
    private String codigoHash;
    private String fecha;
    private UsuarioDocumento usuarioDocumento;
    
    public FirmaDigital(String codigoHash, String fecha, UsuarioDocumento usuarioDocumento){
        this.codigoHash = codigoHash;
        this.fecha = fecha;
        this.usuarioDocumento = usuarioDocumento;
    }
    
    public void mostrarUsuarioDocumento(){
        if (usuarioDocumento != null){
            System.out.println("La firma digital " + codigoHash + " pertenece al usuarioDocumento " + usuarioDocumento.getNombre());
        }else{
            System.out.println("La firma digital " + codigoHash  + " no tiene un usuarioDocumento asignado.");
        }    
    }

    public String getCodigoHash() {
        return codigoHash;
    }

    public void setCodigoHash(String codigoHash) {
        if (codigoHash!="")
            this.codigoHash = codigoHash;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        if (fecha!="")
            this.fecha = fecha;
    }

    public UsuarioDocumento getUsuarioDocumento() {
        return usuarioDocumento;
    }

    public void setUsuarioDocumento(UsuarioDocumento usuarioDocumento) {
        this.usuarioDocumento = usuarioDocumento;
    }

    @Override
    public String toString() {
        return "FirmaDigital{" + "codigoHash=" + codigoHash + ", fecha=" + fecha + ", usuarioDocumento=" + usuarioDocumento + '}';
    }
     
    
}
