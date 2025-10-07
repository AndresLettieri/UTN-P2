package Main;

// Importamos las clases que vamos a usar
import Entities.*; // Producto, CodigoBarras, Categoria, etc.
import Service.*;  // Los servicios (manejan la lógica y las transacciones)
import Dao.*;      // Los DAO (acceso a la base)
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Esta clase muestra un menú en consola para usar el sistema.
 * El usuario elige opciones, ingresa datos, y se llama a los "Service"
 * que se encargan de guardar o leer desde la base de datos.
 */
public class AppMenu {

    // Scanner permite leer lo que el usuario escribe en la consola
    private final Scanner sc = new Scanner(System.in);

    // Creamos los servicios, que a su vez usan los DAO
    private final ProductoServiceImpl productoService =
            new ProductoServiceImpl(new ProductoDAO(), new CodigoBarrasDAO());
    private final CodigoBarrasServiceImpl codigoService =
            new CodigoBarrasServiceImpl(new CodigoBarrasDAO());
    private final CategoriaServiceImpl categoriaService =
            new CategoriaServiceImpl(new CategoriaDAO());

    // Método principal: se ejecuta cuando arranca el programa
    public static void main(String[] args) {
        new AppMenu().run(); // crea el menú y lo ejecuta
    }

    // Menú principal: permite entrar a las secciones
    private void run() {
        int op;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1) Productos");
            System.out.println("2) Códigos de barras");
            System.out.println("3) Categorías");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            op = leerInt(); // lee un número del teclado

            switch (op) {
                case 1 -> menuProducto();  // Submenú de productos
                case 2 -> menuCodigo();    // Submenú de códigos de barras
                case 3 -> menuCategoria(); // Submenú de categorías
                case 0 -> System.out.println("Hasta luego!");
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0); // repite hasta que elija salir
    }

    /* ------------------- SECCIÓN PRODUCTOS ------------------- */
    private void menuProducto() {
        int op;
        do {
            System.out.println("\n--- PRODUCTOS ---");
            System.out.println("1) Crear producto + código");
            System.out.println("2) Ver producto por ID");
            System.out.println("3) Listar productos");
            System.out.println("4) Actualizar producto");
            System.out.println("5) Eliminar producto (baja lógica)");
            System.out.println("6) Recuperar producto");
            System.out.println("7) Buscar por nombre");
            System.out.println("8) Buscar por código de barras");
            System.out.println("0) Volver");
            System.out.print("Opción: ");
            op = leerInt();

            try {
                switch (op) {
                    case 1 -> crearProductoConCodigo(); // crea ambos juntos
                    case 2 -> verProducto();            // muestra uno
                    case 3 -> listarProductos();        // muestra todos
                    case 4 -> actualizarProducto();     // modifica
                    case 5 -> eliminarProducto();       // baja lógica
                    case 6 -> recuperarProducto();      // "reactiva" un eliminado
                    case 7 -> buscarProductoPorNombre();
                    case 8 -> buscarProductoPorValorCodigo();
                    case 0 -> {} // volver al menú anterior
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                // Si algo falla, muestra el error (por ejemplo, error SQL)
                System.out.println("❌ Error: " + e.getMessage());
            }
        } while (op != 0);
    }

    // Crea un producto y su código de barras al mismo tiempo
    private void crearProductoConCodigo() throws Exception {
        System.out.println("\n== Crear PRODUCTO + CÓDIGO ==");

        // 1️⃣ Pedimos los datos del código de barras
        System.out.print("Tipo (EAN13/EAN8/UPC): ");
        TipoCodigoBarras tipo = TipoCodigoBarras.valueOf(sc.nextLine().trim().toUpperCase());
        System.out.print("Valor del código: ");
        String valor = sc.nextLine().trim();
        System.out.print("Observaciones (opcional): ");
        String obs = sc.nextLine().trim();

        // Creamos el objeto del código de barras
        CodigoBarras cb = new CodigoBarras();
        cb.setTipo(tipo);
        cb.setValor(valor);
        cb.setFechaAsignacion(LocalDate.now());
        cb.setObservaciones(obs);

        // 2️⃣ Pedimos los datos del producto
        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Marca (opcional): ");
        String marca = sc.nextLine().trim();
        System.out.print("Precio: ");
        double precio = leerDouble();
        System.out.print("Peso: ");
        double peso = leerDouble();

        // 3️⃣ Mostramos las categorías existentes para que elija una
        listarCategorias();
        System.out.print("ID de categoría: ");
        long idCat = leerLong();
        Categoria cat = categoriaService.getById(idCat);
        if (cat == null) throw new IllegalArgumentException("Categoría inexistente.");

        // 4️⃣ Creamos el producto y lo asociamos con el código
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setMarca(marca);
        p.setPrecio(precio);
        p.setPeso(peso);
        p.setCategoria(cat);
        p.setCodigoBarras(cb);

        // 5️⃣ Llamamos al service → guarda ambos en la base (transacción)
        productoService.insertar(p);

        System.out.println("✅ Producto y código creados correctamente.");
    }

    // Muestra un producto por su ID
    private void verProducto() throws Exception {
        System.out.print("ID: ");
        long id = leerLong();
        Producto p = productoService.getById(id);
        System.out.println(p == null ? "No encontrado." : p);
    }

    // Lista todos los productos activos (no eliminados)
    private void listarProductos() throws Exception {
        List<Producto> lista = productoService.getAll();
        if (lista.isEmpty()) System.out.println("(sin datos)");
        else lista.forEach(System.out::println);
    }

    // Actualiza un producto (nombre, marca, precio, etc.)
    private void actualizarProducto() throws Exception {
        System.out.print("ID del producto: ");
        long id = leerLong();
        Producto p = productoService.getById(id);
        if (p == null) { System.out.println("No existe."); return; }

        System.out.print("Nuevo nombre (" + p.getNombre() + "): ");
        String nuevo = sc.nextLine().trim();
        if (!nuevo.isBlank()) p.setNombre(nuevo);

        System.out.print("Nuevo precio (" + p.getPrecio() + "): ");
        double nuevoPrecio = leerDouble();
        if (nuevoPrecio > 0) p.setPrecio(nuevoPrecio);

        productoService.actualizar(p);
        System.out.println("✅ Producto actualizado.");
    }

    // Marca un producto como eliminado (no se borra de la BD)
    private void eliminarProducto() throws Exception {
        System.out.print("ID: ");
        long id = leerLong();
        productoService.eliminar(id);
        System.out.println("🗑️ Producto eliminado (baja lógica).");
    }

    // Restaura un producto eliminado
    private void recuperarProducto() throws Exception {
        System.out.print("ID: ");
        long id = leerLong();
        productoService.recuperar(id);
        System.out.println("♻️ Producto recuperado.");
    }

    // Busca productos cuyo nombre contenga cierta palabra
    private void buscarProductoPorNombre() throws Exception {
        System.out.print("Buscar nombre: ");
        String filtro = sc.nextLine().trim();
        List<Producto> lista = productoService.buscarPorNombre(filtro);
        if (lista.isEmpty()) System.out.println("Sin resultados.");
        else lista.forEach(System.out::println);
    }

    // Busca un producto por el valor exacto del código de barras
    private void buscarProductoPorValorCodigo() throws Exception {
        System.out.print("Valor exacto del código: ");
        String valor = sc.nextLine().trim();
        Producto p = productoService.buscarPorValorCodigoBarras(valor);
        System.out.println(p == null ? "No encontrado." : p);
    }

    /* ------------------- SECCIÓN CODIGOS ------------------- */
    private void menuCodigo() {
        int op;
        do {
            System.out.println("\n--- CODIGOS DE BARRAS ---");
            System.out.println("1) Listar códigos");
            System.out.println("2) Ver código por ID");
            System.out.println("3) Eliminar código (baja lógica)");
            System.out.println("4) Recuperar código");
            System.out.println("0) Volver");
            System.out.print("Opción: ");
            op = leerInt();

            try {
                switch (op) {
                    case 1 -> listarCodigos();
                    case 2 -> verCodigo();
                    case 3 -> eliminarCodigo();
                    case 4 -> recuperarCodigo();
                    case 0 -> {}
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        } while (op != 0);
    }

    // Muestra todos los códigos activos
    private void listarCodigos() throws Exception {
        List<CodigoBarras> lista = codigoService.getAll();
        if (lista.isEmpty()) System.out.println("(sin datos)");
        else lista.forEach(System.out::println);
    }

    // Muestra un código por ID
    private void verCodigo() throws Exception {
        System.out.print("ID: ");
        long id = leerLong();
        CodigoBarras cb = codigoService.getById(id);
        System.out.println(cb == null ? "No encontrado." : cb);
    }

    // Baja lógica del código de barras
    private void eliminarCodigo() throws Exception {
        System.out.print("ID: ");
        long id = leerLong();
        codigoService.eliminar(id);
        System.out.println("🗑️ Código eliminado (baja lógica).");
    }

    // Recupera un código eliminado
    private void recuperarCodigo() throws Exception {
        System.out.print("ID: ");
        long id = leerLong();
        codigoService.recuperar(id);
        System.out.println("♻️ Código recuperado.");
    }

    /* ------------------- SECCIÓN CATEGORIAS ------------------- */
    private void menuCategoria() {
        int op;
        do {
            System.out.println("\n--- CATEGORIAS ---");
            System.out.println("1) Listar categorías");
            System.out.println("2) Crear categoría");
            System.out.println("0) Volver");
            System.out.print("Opción: ");
            op = leerInt();

            try {
                switch (op) {
                    case 1 -> listarCategorias();
                    case 2 -> crearCategoria();
                    case 0 -> {}
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        } while (op != 0);
    }

    // Lista todas las categorías existentes
    private void listarCategorias() throws Exception {
        List<Categoria> lista = categoriaService.getAll();
        if (lista.isEmpty()) System.out.println("(sin datos)");
        else lista.forEach(c -> System.out.println(c.getId() + " - " + c.getNombre()));
    }

    // Crea una nueva categoría
    private void crearCategoria() throws Exception {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Descripción: ");
        String desc = sc.nextLine().trim();
        Categoria c = new Categoria(nombre, desc);
        categoriaService.insertar(c);
        System.out.println("✅ Categoría creada.");
    }

    /* ------------------- MÉTODOS DE AYUDA ------------------- */
    // Estos métodos validan la entrada del usuario (para evitar errores)
    private int leerInt() {
        while (true) {
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Número inválido. Intente otra vez: "); }
        }
    }

    private long leerLong() {
        while (true) {
            try { return Long.parseLong(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Número inválido. Intente otra vez: "); }
        }
    }

    private double leerDouble() {
        while (true) {
            try { return Double.parseDouble(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Número inválido. Intente otra vez: "); }
        }
    }
}
