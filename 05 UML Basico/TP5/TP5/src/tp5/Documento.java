package tp5;

public class Documento {
    private String titulo;
    private String contenido;
    private FirmaDigital firmaDigital;
    
    public Documento(String titulo, String contenido, String codigoHash, String fecha, UsuarioDocumento usuarioDocumento){
        this.titulo = titulo;
        this.contenido = contenido;
        this.firmaDigital = new FirmaDigital(codigoHash, fecha, usuarioDocumento);
    }
    
    public void mostrarFirmaDigital(){
        if (firmaDigital != null){
            System.out.println("El documento " + titulo + " tiene la firma digital " + firmaDigital.getCodigoHash());
        }else{
            System.out.println("El documento " + titulo  + " no tiene una firma digital asignada.");
        }    
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo!="")
            this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        if (contenido!="")
            this.contenido = contenido;
    }

    public FirmaDigital getFirmaDigital() {
        return firmaDigital;
    }
    
    
    
    @Override
    public String toString() {
        return "Documento{" + "titulo=" + titulo + ", contenido=" + contenido + ", firmaDigital=" + firmaDigital + '}';
    }
    
    
    
    
}
