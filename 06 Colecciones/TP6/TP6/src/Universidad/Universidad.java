package Universidad;

import java.util.ArrayList;
import java.util.List;

public class Universidad {
    private String nombre;
    private List<Profesor> profesores;
    private List<Curso> cursos;

    public Universidad(String nombre) {
        this.nombre = nombre;
        this.profesores = new ArrayList<>();
        this.cursos = new ArrayList<>();
    }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            if (!nombre.isEmpty()){
                this.nombre = nombre;
            }
        }
    
    public void agregarProfesor(Profesor p){
        profesores.add(p);
    }
    
    public void agregarCurso (Curso c){
        cursos.add(c);
    }
    
    public void asignarProfesorACurso(String codigoCurso, String idProfesor){
        Curso c = buscarCursoPorCodigo(codigoCurso);
        if (c==null){
            System.out.println("Codigo de curso no encontrado.");
            return;
        }
        
        Profesor p = buscarProfesorPorId(idProfesor);
        if (p == null){
            System.out.println("Id de profesor no encontrado.");
            return;
        }
        //paso el profesor que estoy actualizando
        c.setProfesor(p);
            
    }
    
    public void listarProfesores(){
        System.out.println("Listado de profesores:");
        for (Profesor p : profesores){
            p.mostrarInfo();
        }
    }
    public void listarCursos(){
        System.out.println("Listado de cursos:");
        for (Curso c : cursos){
            c.mostrarInfo();
        }
    }
    
    private Profesor buscarProfesorPorId(String id){
        int i = 0;
        Profesor profeEncontrado = null;
        while (i < profesores.size() && !this.profesores.get(i).getId().equals(id)) {
            i++;
        }
        if (i < profesores.size()) {
            profeEncontrado = this.profesores.get(i);
        }
        return profeEncontrado;
    }
    
    private Curso buscarCursoPorCodigo(String codigo){
        int i = 0;
        Curso cursoEncontrado = null;
        while (i < cursos.size() && !this.cursos.get(i).getCodigo().equals(codigo)) {
            i++;
        }
        if (i < cursos.size()) {
            cursoEncontrado = this.cursos.get(i);
        }
        return cursoEncontrado;
    }
    
    public void eliminarCurso(String codigo){
        Curso c = buscarCursoPorCodigo(codigo);
        if (c == null){
            System.out.println("Codigo de curso no encontrado.");
        }else{
            c.setProfesor(null);
            cursos.remove(c);
            System.out.println("Curso " + c.getNombre() + " eliminado.\n");
        }
        
    }
    public void eliminarProfesor(String id){
        Profesor p = buscarProfesorPorId(id);
        if (p == null){
            System.out.println("Id de profesor no encontrado.");
        }else{
            for (Curso c : cursos){
                if (c.getProfesor().equals(p)){
                    c.setProfesor(null);
                }
            }
            profesores.remove(p);
        }
    }
    
    public void cursosPorProfesor(){
        for (Profesor profesor : profesores) {
            System.out.println("- " + profesor.getNombre() + ": " + profesor.getCantidadDeCursos() + " cursos");
        }
    }
}
