package Entities;

public class Categoria {
    private int id;
    private String nombre;
    private String descripcion;

    //Constructor para nuevas categorias
    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    //Constructor para recuperar categorias ya creadas en base
    public Categoria(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public Categoria(){
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (!nombre.isEmpty()) this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Categoria: " + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion ;
    }
    
    
    
    
    
    
}
