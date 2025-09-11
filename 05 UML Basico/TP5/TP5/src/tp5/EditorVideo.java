package tp5;

public class EditorVideo {
    public void exportar(String formato, Proyecto proyecto){
        Render render = new Render(formato, proyecto);
        
        System.out.println("Render creado: " + render.toString());
    }
}
