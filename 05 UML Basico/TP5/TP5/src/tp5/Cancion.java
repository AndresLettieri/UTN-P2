package tp5;

public class Cancion {
    private String titulo;
    private Artista artista;
    
    public Cancion (String titulo, Artista artista){
        this.titulo = titulo;
        this.artista = artista;
    }

    public String getTitulo() {
        return titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setTitulo(String titulo) {
        if (titulo!= "")
            this.titulo = titulo;
    }

    public void setArtista(Artista artista) {
        if (artista != null)
            this.artista = artista;
    }

    @Override
    public String toString() {
        return "Cancion{" + "titulo=" + titulo + ", artista=" + artista + '}';
    }
    
    
    
    
    
}
