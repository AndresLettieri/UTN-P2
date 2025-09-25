package Universidad;

public class Main {

    public static void main(String[] args) {
        Universidad universidad = new Universidad("UTN");

        System.out.println("1. Crear al menos 3 profesores y 5 cursos\n"); 
        Profesor prof1 = new Profesor("1","Juan Perez","Ingeniero informatico");
        Profesor prof2 = new Profesor("2","Ramon Valdez","Analista");
        Profesor prof3 = new Profesor("3","Pedro Pedrin","Licenciado en casi todo");

        Curso curso1 = new Curso("cur1","Programacion 1");
        Curso curso2 = new Curso("cur2","Programacion 2");
        Curso curso3 = new Curso("cur3","Ingles");
        Curso curso4 = new Curso("cur4","Estadistica");
        Curso curso5 = new Curso("cur5","Base de datos");

        System.out.println("\n2. Agregar profesores y cursos a la universidad\n");
        universidad.agregarProfesor(prof1);
        universidad.agregarProfesor(prof2);
        universidad.agregarProfesor(prof3);

        universidad.agregarCurso(curso1);
        universidad.agregarCurso(curso2);
        universidad.agregarCurso(curso3);
        universidad.agregarCurso(curso4);
        universidad.agregarCurso(curso5);

        System.out.println("\n3. Asignar profesores a cursos\n");
        universidad.asignarProfesorACurso("cur1", "1");
        universidad.asignarProfesorACurso("cur2", "1"); 
        universidad.asignarProfesorACurso("cur3", "3"); 
        universidad.asignarProfesorACurso("cur4", "2"); 
        universidad.asignarProfesorACurso("cur5", "2"); 


        System.out.println("\n4. Listar cursos con su profesor y profesores con sus cursos\n");
        universidad.listarCursos();
        universidad.listarProfesores();

        System.out.println("\n5. Cambiar el profesor de un curso y verificar sincronización\n");
        universidad.asignarProfesorACurso("cur5", "3");
        universidad.listarCursos();
        universidad.listarProfesores();

        
        System.out.println("\n6. Remover un curso y confirmar que ya no aparece en la lista del profesor\n");
        universidad.eliminarCurso("cur3");
        universidad.listarProfesores();

        System.out.println("\n7. Remover un profesor y dejar profesor = null en los cursos\n");
        universidad.eliminarProfesor("3");
        universidad.listarCursos();

        System.out.println("\n8. Reporte: cantidad de cursos por profesor\n");
        universidad.cursosPorProfesor();
    }
    
}
