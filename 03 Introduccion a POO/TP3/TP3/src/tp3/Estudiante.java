/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp3;

/**
 *
 * @author Andres
 */
public class Estudiante {
    private String nombre;
    private String apellido;
    private String curso;
    private double calificacion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.trim() != ""){
            this.nombre = nombre;
        }
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido.trim() != "")
            this.apellido = apellido;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        if (curso.trim() != ""){
            this.curso = curso;
        }
    }

    public double getCalificacion() {
        return calificacion;
    }

    
    public void mostrarInfo(){
        System.out.println("Nombre: " + nombre + " " + apellido + ". Curso: " + curso + ". Nota: " + calificacion);
    }
    
    public void subirCalificacion(double c){ 
        double result = calificacion + c;
        if (validaCalificacion(result)){
            calificacion += c;
        }
    }
    
    public void bajarCalificacion(double c){
        double result = calificacion - c;
        if (validaCalificacion(result)){
            calificacion -= c;
        }
    }
    
    private boolean validaCalificacion(double c){
        return c >= 0 && c <=10;
    }
    
}
