
package Producto;

public class Main {

    public static void main(String[] args) {

        Inventario inventario = new Inventario();

        // 1. Crear al menos cinco productos con diferentes categorías y agregarlos al inventario.
        Producto p1 = new Producto("1", "Fideos", 1200, 50, CategoriaProducto.ALIMENTOS);
        Producto p2 = new Producto("2", "Salsa de tomates", 1000, 10, CategoriaProducto.ALIMENTOS);
        Producto p3 = new Producto("3", "TV", 550000, 30, CategoriaProducto.ELECTRONICA);
        Producto p4 = new Producto("4", "Mesa", 200000, 80, CategoriaProducto.HOGAR);
        Producto p5 = new Producto("5", "Pantalon", 60000, 45, CategoriaProducto.ROPA);

        inventario.agregarProducto(p1);
        inventario.agregarProducto(p2);
        inventario.agregarProducto(p3);
        inventario.agregarProducto(p4);
        inventario.agregarProducto(p5);

        // 2. Listar todos los productos mostrando su información y categoría.
        System.out.println("Lista de Productos:");
        inventario.listarProductos();
        

        // 3. Buscar un producto por ID y mostrar su información.
        System.out.println("Buscar Producto por ID 3");
        Producto encontrado = inventario.buscarProductoPorId("3");
        if (encontrado != null){
            System.out.println(encontrado.toString());
        }else{
            System.out.println("Producto no encontrado.");
        }

        // 4. Filtrar y mostrar productos que pertenezcan a una categoría específica.
        System.out.println("Productos de la categoría Alimentos");
        for (Producto p : inventario.filtrarPorCategoria(CategoriaProducto.ALIMENTOS)) {
            System.out.println(p.toString());
        }

        // 5. Eliminar un producto por su ID y listar los productos restantes.
        System.out.println("Eliminar Producto ID: 2");
        inventario.eliminarProducto("2");
        System.out.println("Lista de Productos actualizada:");
        inventario.listarProductos();

        // 6. Actualizar el stock de un producto existente.
        System.out.println("Actualizar stock del producto 1");
        inventario.actualizarStock("1", 80);
        System.out.println(inventario.buscarProductoPorId("1").toString());

        // 7. Mostrar el total de stock disponible.
        System.out.println("Total de stock disponible");
        System.out.println("Total stock: " + inventario.obtenerTotalStock());

        // 8. Obtener y mostrar el producto con mayor stock.
        System.out.println("Productos con mayor stock");
        for (Producto p : inventario.obtenerProductoConMayorStock()) {
            System.out.println(p.toString());
        }

        // 9. Filtrar productos con precios entre $1000 y $3000.
        System.out.println("Productos con precio entre $1000 y $100000");
        for (Producto p : inventario.filtrarProductosPorPrecio(1000, 100000)) {
            System.out.println(p);
        }

        // 10. Mostrar las categorías disponibles con sus descripciones.
        System.out.println("Categorías disponibles");
        inventario.mostrarCategoriasDisponibles();
    }
    
}
