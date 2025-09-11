package tp5;

public class Reproductor {
    public void reproducir(Cancion cancion){
        System.out.println("Reproduciendo " + cancion.getTitulo() + " del artista " + cancion.getArtista().getNombre());
    }
}
