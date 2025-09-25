package Producto;

import java.util.ArrayList;

public class Inventario {

    private ArrayList<Producto> productos;

    public Inventario() {
        this.productos = new ArrayList<>();
    }
    
    
    
    public void agregarProducto(Producto p){
        productos.add(p);
        System.out.println("Producto agregado con éxito.");
    }
    
    public void listarProductos(){
        for (Producto p : productos){
            System.out.println(p.getNombre() + ". Cantidad disponible: " + p.getCantidad() );
        }
    }
    
    public Producto buscarProductoPorId(String id){
        int i = 0;
        Producto prodEncontrado = null;
        while (i < productos.size() && !this.productos.get(i).getId().equals(id)) {
            i++;
        }
        if (i < productos.size()) {
            prodEncontrado = this.productos.get(i);
        }
        return prodEncontrado;
    }
    
    public void eliminarProducto(String id){
        Producto prodABorrar = buscarProductoPorId(id);
        if (prodABorrar != null){
        this.productos.remove(prodABorrar);
        System.out.println("Se elimino el producto " + prodABorrar.getNombre());
        }else{
            System.out.println("No se encontro el producto a eliminar.");
        }
    }
    
    public void actualizarStock(String id, int nuevaCantidad){
        Producto prodAActualizar = buscarProductoPorId(id);
        if (prodAActualizar != null){
            prodAActualizar.setCantidad(nuevaCantidad);
            System.out.println("Se actualizó el producto " + prodAActualizar.getNombre());
        }else{
            System.out.println("No se encontro el producto a actualizar");
        }
    }
    
    public ArrayList<Producto> filtrarPorCategoria(CategoriaProducto categoria){
        ArrayList<Producto> prodEncontrados = new ArrayList<>();
        for (Producto prod : productos) {
            if (prod.getCategoria() == categoria) {
                prodEncontrados.add(prod);
            }
        }
        return prodEncontrados;    
    }

    public int obtenerTotalStock(){
        int totalStock = 0;
        for (Producto prod : productos){
            totalStock += prod.getCantidad();
        }
       return totalStock;
    }
    
    public ArrayList<Producto> obtenerProductoConMayorStock(){
        ArrayList<Producto> prodEncontrados = new ArrayList<>();
        int mayorStock = 0;
        for (Producto prod : productos) {
            int stock = prod.getCantidad();
            if (stock > mayorStock){
                mayorStock = stock;
                prodEncontrados.clear();
                prodEncontrados.add(prod);
            }else if (stock == mayorStock){
                prodEncontrados.add(prod);
            }
        }
        return prodEncontrados;    
    }
    
    public ArrayList<Producto> filtrarProductosPorPrecio(double min, double max){
        ArrayList<Producto> productosFiltrados = new ArrayList<>();
        for (Producto prod : productos) {
            double precio = prod.getPrecio();
            if (precio >= min && precio <= max) {
                productosFiltrados.add(prod);
            }
        }
        return productosFiltrados;
    }
    
    public void mostrarCategoriasDisponibles(){
        for (CategoriaProducto categoria : CategoriaProducto.values()) {
            System.out.println(categoria.name() + ": " + categoria.getDescripcion());
        }   
    }
}
