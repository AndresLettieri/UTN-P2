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
            System.out.println("4) Reporteria");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            opcion = leerInt();

            switch (opcion) {
                case 1:
                    menuProducto();
                    break;
                case 2:
                    menuCodigo();
                    break;
                case 3:
                    menuCategoria();
                    break;
                case 4: 
                    menuReporteria();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema.");
                    break;
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
                        productoCrear();
                        break;
                    case 2:
                        productosListar();
                        break;
                    case 3:
                        productoActualizar();
                        break;
                    case 4:
                        productoEliminar();
                        break;
                    case 5:
                        productoRecuperar();
                        break;
                    case 6:
                        productoBuscarPorNombre();
                        break;
                    case 7:
                        productoBuscarPorCodigoBarras();
                        break;
                    case 8: 
                        productoBuscarPorID();
                        break;
                    case 0:// volver al menú anterior
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }


    //Crear un producto 
    private static void productoCrear() throws Exception {
        System.out.println("\nCreando producto...");
        System.out.print("Ingrese nombre del producto: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Ingrese marca (opcional): ");
        String marca = sc.nextLine().trim();
        System.out.print("Ingrese precio: ");
        double precio = leerDouble(false);
        System.out.print("ingrese Peso (opcional): ");
        double peso = leerDouble(true);
        
        Categoria categoria = categoriaCarga();
        
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setMarca(marca);
        p.setPrecio(precio);
        p.setPeso(peso);
        p.setCategoria(categoria);

        System.out.print("¿Desea asignar un código de barras para el producto? (S/N)");
        if (sc.nextLine().trim().equalsIgnoreCase("S") ){
            CodigoBarras codigoBarras = new CodigoBarras();
            System.out.print("¿Desea utilizar un código existente? (S/N) ") ;
            if (sc.nextLine().trim().equalsIgnoreCase("S") ){
                System.out.print("Ingrese valor del codigo de barras: ");
                codigoBarras = codigoValidarExistenteLibre();
            }
            else{
                System.out.print("Ingrese tipo (EAN13/EAN8/UPC): ");
                TipoCodigoBarras tipo = TipoCodigoBarras.valueOf(sc.nextLine().trim().toUpperCase());
                System.out.print("Ingrese valor del código: ");
                String valor = sc.nextLine().trim();
                System.out.print("Observaciones (opcional): ");
                String obs = sc.nextLine().trim();

                codigoBarras.setTipo(tipo);
                codigoBarras.setValor(valor);
                codigoBarras.setFechaAsignacion(LocalDate.now());
                codigoBarras.setObservaciones(obs);
                }
            //asignamos el codigo al producto
            p.setCodigoBarras(codigoBarras);
        }
        
        productoService.insertar(p);

        System.out.println("Producto creado correctamente.");
    }
        
    

    //Listar todos los productos activos (no eliminados) te va a mostrar de a 100 productos, si necesitas ver otros 100 hay que aceptar.
    private static void productosListar() throws Exception {
        List<Producto> lista = productoService.getAll();

        if (lista.isEmpty()) {
            System.out.println("No existen productos activos.");
            return;
        }

        final int LIMITE = 100;
        int total = lista.size();
        int indice = 0;

        while (indice < total) {
            int hasta = Math.min(indice + LIMITE, total);

            for (int i = indice; i < hasta; i++) {
                    System.out.println(lista.get(i).toString());
            }

            indice = hasta;

            if (indice < total) {
                System.out.print("Desea ver los proximos " + LIMITE + " registros? (S/N): ");
                String respuesta = sc.nextLine().trim();
                if (!respuesta.equalsIgnoreCase("s")) {
                    break;
                }
            }
        }
    }


    
    //Actualiza producto
    private static void productoActualizar() throws Exception {
        System.out.print("Id del producto a actualizar: ");
        long id = leerInt();
        Producto p = productoService.getById(id);
        if (p == null) {
            System.out.println("No se encontro el producto.");
            return;
        }

        if (opcionCambio("el nombre")) {
            System.out.print("Nuevo nombre: ");
            p.setNombre(sc.nextLine().trim());
        }
        if (opcionCambio("la marca")) {
            System.out.print("Nueva marca: ");
            p.setMarca(sc.nextLine().trim());
        }
        if (opcionCambio("la categoria")) {
            List<Categoria> categorias = new CategoriaServiceImpl(new CategoriaDAO()).getAll();
            for (Categoria c : categorias) System.out.println(c.getId() + ") " + c.getNombre());
            System.out.print("Id de categoria: ");
            int idCat = leerInt();
            for (Categoria c : categorias) if (c.getId() == idCat) { p.setCategoria(c); break; }
        }
        if (opcionCambio("el precio")) {
            System.out.print("Nuevo precio: ");
            p.setPrecio(leerDouble(false));
        }
        if (opcionCambio("el peso")) {
            System.out.print("Nuevo peso: ");
            p.setPeso(leerDouble(true));
        }
        if (opcionCambio("el codigo de barras")) {
            System.out.println("1) Asignar uno disponible");
            System.out.println("2) Quitar codigo");
            System.out.print("Opcion: ");
            int op = leerInt();
            if (op == 1) {
                List<CodigoBarras> libres = codigoService.codigoBarrasDisponibles();
                if (libres.isEmpty()) {
                    System.out.println("No hay codigos disponibles.");
                } else {
                    for (CodigoBarras x : libres) System.out.println(x.getId() + ") " + x.getTipo() + " - " + x.getValor());
                    System.out.print("Id de codigo: ");
                    long idCb = leerInt();
                    for (CodigoBarras x : libres) if (x.getId() == idCb) { p.setCodigoBarras(x); break; }
                }
            } else if (op == 2) {
                p.setCodigoBarras(null);
            }
        }

        productoService.actualizar(p);
        System.out.println("Producto actualizado.");
    }

    
    //baja logica del producto
    private static void productoEliminar() throws Exception {
        System.out.print("Id del producto a eliminar: ");
        long id = leerInt();
        productoService.eliminar(id);
        System.out.println("Producto eliminado.");
    }


    //restaurar un producto dado de baja
    private static void productoRecuperar() throws Exception {
        System.out.print("Id del producto a recuperar: ");
        long id = leerInt();
        productoService.recuperar(id);
        System.out.println("Producto recuperado.");
    }


    //Buscar producto por nombre
    private static void productoBuscarPorNombre() throws Exception {
         System.out.print("Filtro de nombre: ");
         String filtro = sc.nextLine().trim();
         List<Producto> lista = productoService.buscarPorNombre(filtro);
         if (lista.isEmpty()) System.out.println("Sin resultados.");
         else for (Producto p : lista) System.out.println(p.toString());
     }


    //Buscar producto por codigo de barras
    private static void productoBuscarPorCodigoBarras() throws Exception {
        System.out.print("Valor de codigo de barras: ");
        String valor = sc.nextLine().trim();
        Producto p = productoService.buscarPorValorCodigoBarras(valor);
        if (p == null) System.out.println("No se encontro producto con ese codigo.");
        else System.out.println(p.toString());
    }


    //Buscar producto por ID
    private static void productoBuscarPorID() throws Exception {
        System.out.print("Id del producto: ");
        long id = leerInt();
        Producto p = productoService.getById(id);
        if (p == null) System.out.println("No se encontro.");
        else System.out.println(p.toString());
    }


    private static void menuCodigo() {
        int opcion;
        do {
            System.out.println("\n--- CODIGOS DE BARRAS ---");
            System.out.println("1) Crear código de barras");
            System.out.println("2) Listar códigos de barras");
            System.out.println("3) Actualizar código de barras");
            System.out.println("4) Eliminar código de barras");
            System.out.println("5) Recuperar código de barras");
            System.out.println("6) Buscar código de barras por ID");
            System.out.println("7) Buscar código de barras por valor");
            System.out.println("0) Volver");
            System.out.print("Opción: ");
            opcion = leerInt();

            try {
                switch (opcion) {
                    case 1:
                        codigoCrear();
                        break;
                    case 2:
                        codigosListar();
                        break;
                    case 3:
                        codigoActualizar();
                        break;
                    case 4:
                        codigoEliminar();
                        break;
                    case 5:
                        codigoRecuperar();
                        break;
                    case 6:
                        codigoBuscarPorID();
                        break;
                    case 7:
                        codigoBuscarPorValor();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }
    
    //crea codigo de barras
    private static void codigoCrear() throws Exception {
        System.out.println("Crear codigo de barras");
        System.out.println("Tipos: 1) EAN13  2) EAN8  3) UPC");
        System.out.print("Opcion: ");
        int t = leerInt();
        TipoCodigoBarras tipo = null;
        do {
            switch (t){
                case 1:
                    tipo = TipoCodigoBarras.EAN13;
                    break;
                case 2:
                    tipo = TipoCodigoBarras.EAN8;
                    break;
                case 3:
                    tipo = TipoCodigoBarras.UPC;
                    break;
                default:
                    System.out.println("Tipo de codigo de barras invalido. Intente nuevamente.");
                    break;
            }  
        } while (tipo == null);
        
        System.out.print("Valor: ");
        String valor = sc.nextLine().trim();
        System.out.print("Observaciones: ");
        String obs = sc.nextLine();
        CodigoBarras cb = new CodigoBarras(tipo, valor, null, obs);
        codigoService.insertar(cb);
        System.out.println("Codigo creado con exito.");
    }


    //Muestra todos los códigos activos de a 100 valores, si hay mas, te pregunta si queres verlos
    private static void codigosListar() throws Exception {
        List<CodigoBarras> lista = codigoService.getAll();

        if (lista.isEmpty()) {
            System.out.println("No hay códigos disponibles.");
            return;
        }

        final int LIMITE = 1000;
        int total = lista.size();
        int indice = 0;

        while (indice < total) {
            int hasta = Math.min(indice + LIMITE, total);

            for (int i = indice; i < hasta; i++) {
                System.out.println(lista.get(i).toString());
            }

            indice = hasta;

            if (indice < total) {
                System.out.print("Desea ver los próximos " + LIMITE + " registros? (S/N): ");
                String respuesta = sc.nextLine().trim();
                if (!respuesta.equalsIgnoreCase("s")) {
                    break;
                }
            }
        }
    }


    
    //actualizar codigo de barras
    private static void codigoActualizar() throws Exception {
        System.out.print("Id del codigo a actualizar: ");
        long id = leerInt();
        CodigoBarras cb = codigoService.getById(id);
        if (cb == null) {
            System.out.println("No se encontro.");
            return;
        }
        if (opcionCambio("el tipo")) {
            do {
                System.out.println("Tipos: 1) EAN13  2) EAN8  3) UPC");
                int t = leerInt();
                switch (t){
                    case 1:
                        cb.setTipo(TipoCodigoBarras.EAN13);
                        break;
                    case 2:
                        cb.setTipo(TipoCodigoBarras.EAN8);
                        break;
                    case 3:
                        cb.setTipo(TipoCodigoBarras.UPC);
                        break;
                    default:
                        System.out.println("Tipo de codigo de barras invalido. Intente nuevamente.");
                        break;
                }  
            } while (cb.getTipo() == null);
        }
        if (opcionCambio("el valor")) {
            System.out.print("Nuevo valor: ");
            cb.setValor(sc.nextLine().trim());
        }
        if (opcionCambio("las observaciones")) {
            System.out.print("Nuevas observaciones: ");
            cb.setObservaciones(sc.nextLine());
        }
        codigoService.actualizar(cb);
        System.out.println("Codigo actualizado.");
    }

    
    
    //Baja lógica del código de barras
    private static void codigoEliminar() throws Exception {
        System.out.print("Id del codigo a eliminar: ");
        long id = leerInt();
        codigoService.eliminar(id);
        System.out.println("Codigo eliminado.");
    }


    // Recupera un código eliminado
    private static void codigoRecuperar() throws Exception {
        System.out.print("Id del codigo a recuperar: ");
        long id = leerInt();
        codigoService.recuperar(id);
        System.out.println("Codigo recuperado.");
    }


    
    // Muestra un código por ID
    private static void codigoBuscarPorID() throws Exception {
        System.out.print("Id del codigo: ");
        long id = leerInt();
        CodigoBarras cb = codigoService.getById(id);
        if (cb == null) System.out.println("No se encontro.");
        else System.out.println(cb.toString());
    }

    
    // Muestra un código por valor
    private static void codigoBuscarPorValor() throws Exception {
        System.out.print("Valor: ");
        String v = sc.nextLine().trim();
        CodigoBarras cb = codigoService.buscarPorValor(v);
        if (cb == null) System.out.println("No se encontro.");
        else System.out.println(cb.toString());
    }


    private static void menuCategoria() {
        int opcion;
        do {
            System.out.println("\n--- CATEGORIAS ---");
            System.out.println("1) Crear categoría");
            System.out.println("2) Listar categorías");
            System.out.println("3) Acualizar categoría");
            System.out.println("4) Eliminar categoría");
            System.out.println("0) Volver");
            System.out.print("Opción: ");
            opcion = leerInt();
            
            try {
                switch (opcion) {
                    case 1:
                        categoriaCrear();
                        break;
                    case 2:
                        categoriasListar();
                        break;
                    case 3:
                        categoriaActualizar();
                        break;
                    case 4:
                        categoriaEliminar();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    
    // Crea una nueva categoría
    private static void categoriaCrear() throws Exception {
        System.out.println("Crear categoria");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Descripcion: ");
        String desc = sc.nextLine();
        Categoria c = new Categoria(nombre, desc);
        categoriaService.insertar(c);
        System.out.println("Categoria creada.");
    }

    
    //Lista todas las categorías existentes
    private static void categoriasListar() throws Exception {
        List<Categoria> lista = categoriaService.getAll();
        if (lista.isEmpty()) 
            System.out.println("No hay categorias.");
        else{ 
            for (Categoria c : lista) {
                System.out.println(c.toString());
            }
        }
    }

    
    //actualziar categoria
    private static void categoriaActualizar() throws Exception {
        System.out.print("Id de categoria a actualizar: ");
        int id = leerInt();
        Categoria c = categoriaService.getById(id);
        if (c == null) {
            System.out.println("No se encontro.");
            return;
        }
        if (opcionCambio("el nombre")) {
            System.out.print("Nuevo nombre: ");
            c.setNombre(sc.nextLine().trim());
        }
        if (opcionCambio("la descripcion")) {
            System.out.print("Nueva descripcion: ");
            c.setDescripcion(sc.nextLine());
        }
        categoriaService.actualizar(c);
        System.out.println("Categoria actualizada.");
    }

    
    //Eliminar categoria. Baja fisica
    private static void categoriaEliminar() throws Exception {
        System.out.print("Id de categoria a eliminar: ");
        int id = leerInt();
        categoriaService.eliminar((id));
        System.out.println("Categoria eliminada.");
    }



    private static void menuReporteria() {
        int opcion;
        do {
            System.out.println("\n--- REPORTERIA ---");
            System.out.println("1) Productos sin codigo de barras asignado");
            System.out.println("2) Nombres de producto duplicados");
            System.out.println("3) Codigos de barras sin usar");
            System.out.println("0) Volver");
            System.out.print("Opción: ");
            opcion = leerInt();
            
            try {
                switch (opcion) {
                    case 1:
                        productosSinCodigo();
                        break;
                    case 2:
                        productoNombresDuplicados();
                        break;
                    case 3:
                        codigoBarrasSinUsar();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }
    
    private static void productosSinCodigo() throws Exception {
        List<String> lista = productoService.productosSinCodigo();
        if (lista.isEmpty()) System.out.println("Todos los productos tienen codigo.");
        else for (String s : lista) System.out.println(s);
    }

    
    private static void productoNombresDuplicados() throws Exception {
        List<String> lista = productoService.productosDuplicados();
        if (lista.isEmpty()) System.out.println("No hay nombres duplicados.");
        else for (String s : lista) System.out.println(s);
    }
            
    private static void codigoBarrasSinUsar() throws Exception {
        List<CodigoBarras> lista = codigoService.codigoBarrasDisponibles();
        if (lista.isEmpty()) System.out.println("No hay codigos sin usar.");
        else for (CodigoBarras c : lista) System.out.println(c.toString());
    }
    
    //Funcion que confirma con el usuario si desea actualizar un atributo
    private static boolean opcionCambio(String campo){
        System.out.print("¿Desea actualizar " + campo + "? (S/N) ");
        return sc.nextLine().trim().equalsIgnoreCase("s");
    }

    
    //funcion para leer enteros  
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

    //funcion para leer longs   
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

    //funcion para double opcional
    private static double leerDouble(boolean bOpcional) {
        while (true) {
            String input = sc.nextLine().trim();

            if (bOpcional && input.isEmpty()) {
                return 0;
            }
            try { 
                return Double.parseDouble(input); 
            }
            catch (NumberFormatException e) { 
                System.out.print("Opción incorrecta. Ingrese nuevamente un número válido: "); 
            }
        }
    }

    private static Categoria categoriaCarga() throws Exception {
        System.out.print("¿Quiere listar las categorias para elegir una? (S/N)");
        if (sc.nextLine().trim().equalsIgnoreCase("S")){
            categoriasListar();
        }
        System.out.print("Ingrese ID de categoría: ");
        long idCategoria = leerLong();
        Categoria categoria = categoriaService.getById(idCategoria);
        while (categoria == null){
            System.out.println("Categoria invalida. Ingrese una opción correcta.");
            idCategoria = leerLong();
            categoria = categoriaService.getById(idCategoria);
        }
        return categoria;
    }

    private static CodigoBarras codigoValidarExistenteLibre() throws Exception {
        CodigoBarras codBarras = null;
        String valor;

        //Valido que el codigo de barras exista y que no esté en uso
        do{
            valor = sc.nextLine().trim();
            if (valor.trim().equals(""))
                return null;
            codBarras = codigoService.buscarPorValor(valor);
            if (codBarras == null)
                System.out.print("Ingrese un codigo de barras válido: ");
            else{
                //valido que no esté usado en otro producto
                if (productoService.buscarPorValorCodigoBarras(codBarras.getValor()) != null){
                    System.out.println("Codigo de barras ya utilizado.");
                    codBarras = null;
                    System.out.println("Ingrese un codigo de barras válido: ");
                }
            }
        } while(codBarras == null);

        return codBarras;
    }
}