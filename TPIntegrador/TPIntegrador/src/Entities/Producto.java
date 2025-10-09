package Entities;

import Entities.CodigoBarras;

public class Producto extends Base {
    private String nombre;
    private String marca;
    private Categoria categoria;
    private double precio;
    private double peso;
    private CodigoBarras codigoBarras;

    
    private static final int MAX_NOMBRE_LENGTH = 120;
    
    
    //constructor para nuevos productos 
    public Producto(String nombre, String marca, Categoria categoria, double precio, double peso) {
        super();
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.precio = precio;
        this.peso = peso;
    }
    
    //constructor para recuperar productos ya creados en base    
    public Producto(long id,  String nombre, String marca, boolean eliminado, Categoria categoria, double precio, double peso) {
        super(id, eliminado);
        this.nombre = nombre;
        this.marca = marca;
        this.categoria = categoria;
        this.precio = precio;
        this.peso = peso;
    }
    
    public Producto() {
        super();
    }
    
    public int getMAX_NOMBRE_LENGTH(){
        return MAX_NOMBRE_LENGTH;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public CodigoBarras getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(CodigoBarras codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    @Override
    public String toString() {
        String codBarras = "Sin asignar";
        if (codigoBarras != null)
            codBarras = codigoBarras.getValor();
        return "Producto: " + 
                "id:" + getId() + 
                ", eliminado:" + isEliminado() +
                ", nombre:" + nombre 
                + ", marca:" + marca 
                + ", categoria:" + categoria.getNombre()
                + ", precio:" + precio 
                + ", peso:" + peso 
                + ", codigoBarras:" + codBarras;
    }
    
    
    
    
    
}
