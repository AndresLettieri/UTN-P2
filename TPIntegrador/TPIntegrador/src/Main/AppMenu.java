package Main;

import Entities.*; 
import Service.*;
import Dao.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AppMenu {

    private static final Scanner sc = new Scanner(System.in);

    private static final ProductoServiceImpl productoService =
        new ProductoServiceImpl(new ProductoDAO(), new CodigoBarrasDAO());
    private static final CodigoBarrasServiceImpl codigoService =
            new CodigoBarrasServiceImpl(new CodigoBarrasDAO());
    private static final CategoriaServiceImpl categoriaService =
            new CategoriaServiceImpl(new CategoriaDAO());

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        int opcion;
        //lista menu y entra en ciclo hasta seleccionar la opcion de salir
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1) Productos");
            System.out.println("2) Códigos de barras");
            System.out.println("3) Categorías");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            opcion = leerInt();

            switch (opcion) {
                case 1:
                    menuProducto();  //Menu productos
                case 2:
                    menuCodigo();    //Menú códigos de barras
                case 3:
                    menuCategoria(); //Menú categorías
                case 0:
                    System.out.println("Saliendo del sistema.");
                default:
                    System.out.print("Opción incorrecta. Ingrese nuevamente un número válido: "); 
            }
        } while (opcion != 0);
    }

    private static void menuProducto() {
        int opcion;
        do {
            System.out.println("\n--- PRODUCTOS ---");
            System.out.println("1) Crear producto");
            System.out.println("2) Listar productos");
            System.out.println("3) Actualizar producto");
            System.out.println("4) Eliminar producto");
            System.out.println("5) Recuperar producto");
            System.out.println("6) Buscar por nombre");
            System.out.println("7) Buscar por código de barras");
            System.out.println("8) Buscar por ID");
            System.out.println("0) Volver");
            System.out.print("Opción: ");
            opcion = leerInt();

            try {
                switch (opcion) {
                    case 1:
                        crearProducto();
                    case 2:
                        listarProductos();
                    case 3:
                        actualizarProducto();
                    case 4:
                        eliminarProducto();
                    case 5:
                        recuperarProducto();
                    case 6:
                        buscarProductoPorNombre();
                    case 7:
                        buscarProductoPorCodigoBarras();
                    case 8: 
                        buscarProductoPorID();
                    case 0:// volver al menú anterior
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    //Buscar producto por ID
    private static void buscarProductoPorID() throws Exception {
        System.out.print("Ingrese ID: ");
        long id = leerLong();
        Producto p = productoService.getById(id);
        if (p==null)
            System.out.println("Producto no encontrado.");
        else
            System.out.println(p.toString());
    }

    
    //Crear un producto 
    private static void crearProducto() throws Exception {
        System.out.println("\nCreando producto...");
        System.out.print("Ingrese nombre del producto: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Ingrese marca (opcional): ");
        String marca = sc.nextLine().trim();
        System.out.print("Ingrese precio: ");
        double precio = leerDouble();
        System.out.print("ingrees Peso (opcional): ");
        double peso = leerDouble();
        listarCategorias();
        System.out.print("Ingrese número de categoría: ");
        long idCategoria = leerLong();
        Categoria categoria = categoriaService.getById(idCategoria);
        while (categoria != null){
            System.out.println("Categoria invalida. Ingrese una opción correcta.");
            idCategoria = leerLong();
        }
        
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setMarca(marca);
        p.setPrecio(precio);
        p.setPeso(peso);
        p.setCategoria(categoria);

        System.out.println("¿Desea generar un código de barras para el producto? (S/N)");
        if (sc.nextLine().trim().toLowerCase() == "s"){
            System.out.print("Ingrese tipo (EAN13/EAN8/UPC): ");
            TipoCodigoBarras tipo = TipoCodigoBarras.valueOf(sc.nextLine().trim().toUpperCase());
            System.out.print("Ingrese valor del código: ");
            String valor = sc.nextLine().trim();
            System.out.print("Observaciones (opcional): ");
            String obs = sc.nextLine().trim();
            CodigoBarras codigoBarras = new CodigoBarras();
            codigoBarras.setTipo(tipo);
            codigoBarras.setValor(valor);
            codigoBarras.setFechaAsignacion(LocalDate.now());
            codigoBarras.setObservaciones(obs);
            
            //asignamos el codigo al producto
            p.setCodigoBarras(codigoBarras);
        }
        
        productoService.insertar(p);

        System.out.println("Producto creado correctamente.");
    }


    //Listar todos los productos activos (no eliminados)
    private static void listarProductos() throws Exception {
        List<Producto> lista = productoService.getAll();
        if (lista.isEmpty()) 
            System.out.println("No existen productos activos");
        else
            for (Producto p : lista){
                System.out.println(p.toString());
            }
    }

    private static void actualizarProducto() throws Exception {
        System.out.print("Ingrese el ID del producto a actualizar: ");
        long id = leerLong();
        Producto p = productoService.getById(id);
        if (p==null)
            System.out.println("Producto no encontrado.");
        else{
            System.out.print("Nuevo nombre (actual: " + p.getNombre() + "): ");
            String nombre = sc.nextLine().trim();
            if (!nombre.isEmpty()) 
                p.setNombre(nombre);

            System.out.print("Nuevo precio (actual: " + p.getPrecio() + "): ");
            double precio = leerDouble();
            
            if (precio > 0) 
                p.setPrecio(precio);

            productoService.actualizar(p);
            
            System.out.println("Producto actualizado con éxito.");
        }
    }

    //baja logica del producto
    private static void eliminarProducto() throws Exception {
        System.out.print("Ingrese ID del producto a dar de baja: ");
        long id = leerLong();
        productoService.eliminar(id);
        System.out.println("Producto eliminado con éxito.");
    }

    //restaurar un producto dado de baja
    private static void recuperarProducto() throws Exception {
        System.out.print("Ingrese ID del producto a recuperar : ");
        long id = leerLong();
        productoService.recuperar(id);
        System.out.println("Producto recuperado con éxito.");
    }

    //Buscar producto por nombre
    private static void buscarProductoPorNombre() throws Exception {
        System.out.print("Ingrese nombre a buscar: ");
        String filtro = sc.nextLine().trim();
        List<Producto> lista = productoService.buscarPorNombre(filtro);
        if (lista.isEmpty()) 
            System.out.println("No se encontró ningún producto con ese nombre.");
        else{
            for (Producto p : lista){
                System.out.println(p.toString());   
            }
        } 
    }

    private static void buscarProductoPorCodigoBarras() throws Exception {
        System.out.print("Ingrese valor del código de barras: ");
        String valor = sc.nextLine().trim();
        Producto p = productoService.buscarPorValorCodigoBarras(valor);
        if (p==null)
            System.out.println("Producto no encontrado.");
        else
            System.out.println(p.toString());
    }


    private static void menuCodigo() {
        int opcion;
        do {
            System.out.println("\n--- CODIGOS DE BARRAS ---");
            System.out.println("1) Crear código de barras");
            System.out.println("2) Listar códigos de barras");
            System.out.println("3) Buscar código de barras por ID");
            System.out.println("4) Eliminar código de barras");
            System.out.println("5) Recuperar código de barras");
            System.out.println("0) Volver");
            System.out.print("Opción: ");
            opcion = leerInt();

            try {
                switch (opcion) {
                    case 1:
                        crearCodigo();
                    case 2:
                        listarCodigos();
                    case 3:
                        buscarCodigoPorID();
                    case 4:
                        eliminarCodigo();
                    case 5:
                        recuperarCodigo();
                    case 0:
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }
    
    private static void crearCodigo() throws Exception {
        System.out.print("Ingrese tipo (EAN13/EAN8/UPC): ");
        TipoCodigoBarras tipo = TipoCodigoBarras.valueOf(sc.nextLine().trim().toUpperCase());
        System.out.print("Ingrese valor del código: ");
        String valor = sc.nextLine().trim();
        System.out.print("Observaciones (opcional): ");
        String obs = sc.nextLine().trim();
        
        CodigoBarras codigoBarras = new CodigoBarras();
        codigoBarras.setTipo(tipo);
        codigoBarras.setValor(valor);
        codigoBarras.setFechaAsignacion(LocalDate.now());
        codigoBarras.setObservaciones(obs);
        
        codigoService.insertar(codigoBarras);

        System.out.println("Codigo de barras creado correctamente.");
    }

    //Muestra todos los códigos activos
    private static void listarCodigos() throws Exception {
        List<CodigoBarras> lista = codigoService.getAll();
        if (lista.isEmpty())
            System.out.println("No se encuentra ningun codigo activo.");
        else{
            for (CodigoBarras cb : lista){
                System.out.println(cb.toString());
            }
        }
    }

    // Muestra un código por ID
    private static void buscarCodigoPorID() throws Exception {
        System.out.print("Ingrese ID del código de barras: ");
        long id = leerLong();
        CodigoBarras cb = codigoService.getById(id);
        if (cb == null)
            System.out.println("Código no encontrado.");
        else
            System.out.println(cb.toString());
    }

    //Baja lógica del código de barras
    private static void eliminarCodigo() throws Exception {
        System.out.print("Ingrese ID del código de barras a dar de baja: ");
        long id = leerLong();
        codigoService.eliminar(id);
        System.out.println("Código eliminado con éxito.");
    }

    // Recupera un código eliminado
    private static void recuperarCodigo() throws Exception {
        System.out.print("Ingrese ID del código de barras a recuperar: ");
        long id = leerLong();
        codigoService.recuperar(id);
        System.out.println("Código recuperado con éxito.");
    }

    
    private static void menuCategoria() {
        int opcion;
        do {
            System.out.println("\n--- CATEGORIAS ---");
            System.out.println("1) Listar categorías");
            System.out.println("2) Crear categoría");
            System.out.println("3) Eliminar categoría");
            System.out.println("0) Volver");
            System.out.print("Opción: ");
            opcion = leerInt();

            try {
                switch (opcion) {
                    case 1:
                        listarCategorias();
                    case 2:
                        crearCategoria();
                    case 0:
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    //Lista todas las categorías existentes
    private static void listarCategorias() throws Exception {
        List<Categoria> lista = categoriaService.getAll();
        
        if (lista.isEmpty())
            System.out.println("No se encontraron categorias.");
        else{
            for (Categoria c : lista){
                c.toString();
            }
        }
    }

    // Crea una nueva categoría
    private static void crearCategoria() throws Exception {
        System.out.println("Creando categoria...");
        System.out.print("Ingrese el nombre de la categoria: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Ingrese la descripción de la categoria: ");
        String desc = sc.nextLine().trim();
        Categoria c = new Categoria(nombre, desc);
        categoriaService.insertar(c);
        System.out.println("Categoría creada.");
    }

  
    private static int leerInt() {
        while (true) {
            try { 
                return Integer.parseInt(sc.nextLine().trim()); 
            }
            catch (NumberFormatException e) { 
                System.out.print("Opción incorrecta. Ingrese nuevamente un número válido: "); 
            }
        }
    }

    private static long leerLong() {
        while (true) 
        {
            try { 
                return Long.parseLong(sc.nextLine().trim()); 
            }
            catch (NumberFormatException e) { 
                System.out.print("Opción incorrecta. Ingrese nuevamente un número válido: "); 
            }
        }
    }

    private static double leerDouble() {
        while (true) {
            try { 
                return Double.parseDouble(sc.nextLine().trim()); 
            }
            catch (NumberFormatException e) { 
                System.out.print("Opción incorrecta. Ingrese nuevamente un número válido: "); 
            }
        }
    }
}