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
        
    }

    //Listar todos los productos activos (no eliminados)
    private static void productosListar() throws Exception {
        
    }
    
    //Actualiza producto
    private static void productoActualizar() throws Exception {
       
    }
    
    //baja logica del producto
    private static void productoEliminar() throws Exception {
        
    }

    //restaurar un producto dado de baja
    private static void productoRecuperar() throws Exception {
       
    }

    //Buscar producto por nombre
    private static void productoBuscarPorNombre() throws Exception {
       
    }

    //Buscar producto por codigo de barras
    private static void productoBuscarPorCodigoBarras() throws Exception {
       
    } 

    //Buscar producto por ID
    private static void productoBuscarPorID() throws Exception {
        
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
       
    }

    //Muestra todos los códigos activos
    private static void codigosListar() throws Exception {
       
    }
    
    //actualizar codigo de barras
    private static void codigoActualizar() throws Exception{
        
    }
    
    
    //Baja lógica del código de barras
    private static void codigoEliminar() throws Exception {
       
    }

    // Recupera un código eliminado
    private static void codigoRecuperar() throws Exception {
      }

    
    // Muestra un código por ID
    private static void codigoBuscarPorID() throws Exception {
       
    }
    
    // Muestra un código por valor
    private static void codigoBuscarPorValor() throws Exception {
       
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
       
    }
    
    //Lista todas las categorías existentes
    private static void categoriasListar() throws Exception {
   
    }
    
    //actualziar categoria
    private static void categoriaActualizar() throws Exception{
    
    }
    
    //Eliminar categoria. Baja fisica
    private static void categoriaEliminar() throws Exception {

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
        
    }
    
    private static void productoNombresDuplicados() throws Exception {
        
    }
            
    private static void codigoBarrasSinUsar() throws Exception {
       
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

    //funcion para double
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