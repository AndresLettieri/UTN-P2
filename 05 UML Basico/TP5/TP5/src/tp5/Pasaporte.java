/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5;

import java.util.Date;

/**
 *
 * @author Andres
 */
public class Pasaporte {
    private String numero;
    private String fechaEmision;
    private Titular titular;
    private Foto foto;
    
    public Pasaporte(String numero, String fechaEmision, String imagen, String formato)
    {
        this.numero = numero;
        this.fechaEmision = fechaEmision;
        this.foto = new Foto(imagen,formato);
    }

    public void setTitular(Titular titular){
        this.titular = titular;
        if (titular != null && titular.getPasaporte() != this)
            titular.setPasaporte(this);
    }    

    public void mostrarFoto(){
        System.out.println("El pasaporte " + numero + " tiene la " + foto.toString() );
    }

    @Override
    public String toString() {
        return "Pasaporte{" + "numero=" + numero + ", fechaEmision=" + fechaEmision + ", titular=" + titular.toString() + ", foto=" + foto.toString() + '}';
    }
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if(numero != "")
            this.numero = numero;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        if (fechaEmision != "")
            this.fechaEmision = fechaEmision;
    }
    
    public Titular getTitular() {
        return titular;
    }

        
}
