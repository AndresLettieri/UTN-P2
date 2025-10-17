
package tp8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import tp8.Ejercicio1.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TP8 {


    private static final Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) throws IOException {
       ejercicio1();
       ejercicio2();
    }

    private static void ejercicio2() throws IOException{
        System.out.println("Ejercicio 2\n\n");
        divisionSeguraYCadenaANumero();
        leerArchivo();
        validarEdad();
        tryWithResources();
    }
    
    private static void tryWithResources(){
        System.out.println("5. Try with resources.");
        String rutaArchivo = "src\\tp8\\archivo.txt";
        try (  BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo)); ){
            System.out.println(reader.readLine());
        }catch (FileNotFoundException e) {
            System.out.println("Error: El archivo no se encuentra en la ruta especificada.");
        }catch (IOException ex) {
            System.out.println("Error de IO. " + ex.getMessage() );
       }           
    }
    
    private static void leerArchivo(){
        System.out.println("3. Lectura de archivo.\n");
        String rutaArchivo = "src\\tp8\\archivo.txt";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(rutaArchivo));
            System.out.println(reader.readLine());
        }catch (FileNotFoundException e) {
            System.out.println("Error: El archivo no se encuentra en la ruta especificada.");
        }catch (IOException ex) {
            System.out.println("Error de IO. " + ex.getMessage() );
        }finally{
            try {
                reader.close();
            } catch (IOException ex) {
                System.out.println("No se pudo liberar el archivo");;
            }
        }           
    }

    private static void validarEdad() throws EdadInvalidaException {
        System.out.println("4. Excepcion personalizada\n");
        try {
            System.out.println("Ingrese la edad de la persona: ");
            int edad = Integer.parseInt(sc.nextLine().trim());
            if (edad < 0 || edad > 120)
            throw new EdadInvalidaException();
                System.out.println("La edad es valida");
        } catch (NumberFormatException nfe){
            System.out.println("Ingrese solo números.");
        } catch (EdadInvalidaException e) {
                //coloco catch para no interrumpir la ejecucion y completar los ejercicios en una sola corrida.
                System.out.println("Error: " + e.getMessage());
            }
    }
    
    private static void divisionSeguraYCadenaANumero(){
        System.out.println("1. Division segura y 2.Conversion de cadena a numero\n");
        try {
            System.out.println("Ingrese el primer numero: ");
            int num1 = Integer.parseInt(sc.nextLine().trim());
            System.out.println("Ingrese el segundo numero: ");
            int num2 = Integer.parseInt(sc.nextLine().trim());  

            System.out.println("El resultado de la division es " + num1 / num2);
        } catch (ArithmeticException ae) {
            System.out.println("No se puede dividir por cero.");
        } catch (NumberFormatException nfe){
            System.out.println("Ingrese solo números.");
        }
    }
    
    private static void ejercicio1(){
        System.out.println("Ejercicio 1\n\n");
        List<Producto> productos = new ArrayList<Producto>();
        productos.add( new Producto("Remera", 10000));
        productos.add( new Producto("Pantalon", 30000));
        
        Cliente cliente = new Cliente("Andres");
        Pedido pedido = new Pedido(productos, cliente, "nuevo");

        pedido.setEstado("Pagando");

        Pago metodoPago = new TarjetaCredito();  

        pedido.procesarPago(metodoPago);

        if (metodoPago instanceof PagoConDescuento) {
            ((PagoConDescuento) metodoPago).aplicarDescuento(5.0); 
        }
    }
    
    
}
