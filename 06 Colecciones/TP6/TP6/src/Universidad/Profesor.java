package Universidad;

import java.util.ArrayList;
import java.util.List;

public class Profesor {
    private String id;
    private String nombre;
    private String especialidad;
    private List<Curso> cursos;

    public Profesor(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.cursos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (!nombre.isEmpty()){
            this.nombre = nombre;
        }
    }

    public String getEspecialidad() {
        return especialidad;
    }
    
    public void setEspecialidad(String especialidad) {
        if (!especialidad.isEmpty()){
            this.especialidad = especialidad;
        }
    }
    
    public void agregarCurso(Curso c){
        if (!cursos.contains(c) && c != null){
            cursos.add(c);
            if (c.getProfesor() != this){
                c.setProfesor(this);
            }
            System.out.println("Curso " + c.getNombre() + " agregado con éxito al profesor " + nombre + ".");
        }else{
            System.out.println("El profesor " + nombre +" ya tenia asignado el curso" + c.getNombre() + ".");
        }
    }
    
    public void eliminarCurso(Curso c){
        if (cursos.remove(c) && c != null){
            if (c.getProfesor() == this){
                c.setProfesor(null);
            }
            System.out.println("Curso " + c.getNombre() + " eliminado del profesor " + nombre + "." );
        }else{
            System.out.println("El profesor " + nombre + "  no tiene asignado el curso " + c.getNombre() + ".");
        }
    }
    
    public void listarCursos(){
        System.out.println("Cursos:\n");
        for (Curso c : cursos){
            System.out.println("Codigo: " + c.getCodigo() + ". Nombre: " + c.getNombre() + "\n");
        }
    }
    
    public void mostrarInfo(){
        System.out.println("Id profesor: " + id + ", nombre: " + nombre + ", especialidad: " + especialidad + "\n");
        listarCursos();
    }
    
    public int getCantidadDeCursos(){
        return cursos.size();
    }
}
