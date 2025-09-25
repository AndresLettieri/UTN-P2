package Universidad;

public class Curso {
    private String codigo;
    private String nombre;
    private Profesor profesor;

    public Curso(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (!nombre.isEmpty()){
            this.nombre = nombre;
        }
    }
    
    public Profesor getProfesor(){
        return profesor;
    }
    
    public void setProfesor(Profesor p){
        if (profesor == p){
            return;
        }
        
        if(profesor != null ){
           Profesor profABorrar = profesor;
           profesor = null;
           profABorrar.eliminarCurso(this);
        }
        
        profesor = p;
        if (p != null){
            p.agregarCurso(this);
        }
    }
    
    public void mostrarInfo(){
        System.out.println("Código de curso: " + codigo + ". Nombre: " + nombre);
        if (profesor != null){
            System.out.println("ID profesor: " + profesor.getId() + ". Nombre: " + profesor.getNombre()+ "\n");
        }else{
            System.out.println("Sin profesor asignado.");
        }
    }
}
