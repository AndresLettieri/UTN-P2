package tp2;

import java.util.Scanner;

public class TP2 {

    final static  Scanner input = new Scanner(System.in);
    final static double descuento = 0.1;
            
    public static void main(String[] args) {
        ejercicio13();
    }
    
    static void ejercicio1(){
        int year;
        System.out.println("Ingrese un año:" );
        year = input.nextInt();
        if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))
            System.out.println("El año " + year + " es bisiesto.");
        else
            System.out.println("El año " + year + " no es bisiesto.");   
    }
    static void ejercicio2(){
        int num1, num2, num3, mayor;
        System.out.println("Ingrese el primer número entero:" );
        num1 = input.nextInt();        
        System.out.println("Ingrese el segundo número entero:" );
        num2 = input.nextInt();        
        System.out.println("Ingrese el tercer número entero:" );
        num3 = input.nextInt();   
        
        mayor = num1;
        
        if (num2 > mayor)
            mayor = num2;
        
        if (num3 > mayor)
            mayor = num3;
        
        System.out.println("El mayor es: " + mayor);
    }
    
    static void ejercicio3(){
        int edad;
        System.out.println("Ingrese su edad:" );
        edad = input.nextInt();
        
        if (edad < 12)
            System.out.println("Eres un niño" );
        else if (edad <= 17)
            System.out.println("Eres un adolescente" );
        else if (edad <= 59)
            System.out.println("Eres un adulto" );
        else
            System.out.println("Eres un adulto mayor" );
   }

    static void ejercicio4(){
        double precio, precioDescuento, precioFinal, descuento;
        String categoria;
      
        
        System.out.println("Ingrese el precio del producto:" );
        precio =  Double.parseDouble(input.nextLine());
        System.out.println("Ingrese la categoria del producto:" );
        categoria = input.nextLine();
        
        switch(categoria.toUpperCase()){
            case "A":
                descuento = 10;
                break;
            case "B":
                descuento = 15;
                break;
            case "C":
                descuento = 20;
                break;
            default:
                descuento = 0;
                break;
        }
        if (descuento == 0)
        {
            System.out.println("Error en la categoria ingresada, solo puede ingresar A, B o C.");
        }
        else
        {
            precioDescuento = precio * (descuento / 100);  
            precioFinal = precio - precioDescuento;
            System.out.println("Precio original: $" + precio);
            System.out.println("Descuento aplicado: $" + precioDescuento);
            System.out.println("Precio final: $" + precioFinal);
        }
    }
    
    static void ejercicio5(){
        int num, resultado = 0;
        System.out.println("Ingrese un numero (0 para terminar):" );
        num = input.nextInt();        
        while (num != 0){
            if(num%2 == 0)
                resultado += num;
            System.out.println("Ingrese un numero (0 para terminar):" );
            num = input.nextInt();        
        }
        System.out.println("La suma de los numeros pares es: " + resultado);
        
    }
    
    static void ejercicio6(){
        int positivo = 0, negativo = 0, cero = 0, num;
        
        for (int i = 0; i < 10; i++) {
            System.out.println("Ingrese el numero " + (i + 1) + ":" );
            num = input.nextInt();
            if(num>0)
                positivo++;
            else if (num<0)
                negativo++;
            else
                cero++;
        }
        
        System.out.println("Resultados:");
        System.out.println("Postivos: " + positivo);
        System.out.println("Negativos: " + negativo);
        System.out.println("Ceros: " + cero);
    }
    
    static void ejercicio7(){
        double nota;
        System.out.println("Ingrese una nota (0-10)" );
        nota = input.nextDouble();
        while (nota > 10 || nota < 0){
            System.out.println("Error: Nota invalida. Ingrese una nota (0-10)" );
            nota = input.nextDouble();  
            System.out.println("Error: Nota invalida. Ingrese una nota (0-10)" );
            nota = input.nextDouble();  
        }
        System.out.println("Nota guardada correctamente");

    }
    
    static void ejercicio8(){
        double precioFinal = 0, precioBase = 0, pImpuesto = 0, pDescuento = 0;
        
        System.out.println("Ingrese el precio base del producto: " );
        precioBase = input.nextDouble();         
        System.out.println("Ingrese el impuesto en porcentaje (Ejemplo: 10 para 10%): " );
        pImpuesto = input.nextDouble();
        System.out.println("Ingrese el descuento en porcentaje (Ejemplo: 5 para 5%): " );
        pDescuento  = input.nextDouble();
        calcularPrecioFinal(precioBase, pImpuesto, pDescuento);
        
    }
    
    static void calcularPrecioFinal(double precioBase, double impuesto, double descuento){
        double precioFinal;
        precioFinal = precioBase + (precioBase * (impuesto/100)) - (precioBase * (descuento/100));
        System.out.println("El precio final del producto es: " + precioFinal);
    }
    
    static void ejercicio9(){
        double peso, precio, costoEnvio, costoTotal;
        String zona;
        System.out.println("Ingrese el precio del producto: " );
        precio = Double.parseDouble(input.nextLine());
        System.out.println("Ingrese el peso del paquete: " );
        peso = Double.parseDouble(input.nextLine());
        System.out.println("Ingrese la zona de envio (Nacional/Internacional): " );
        zona = input.nextLine();
        costoEnvio =  calcularCostoEnvio(peso,zona);
        costoTotal = calcularTotalCompra(precio,costoEnvio);
        System.out.println("El costo de envio es: " + costoEnvio);
        System.out.println("El total a pagares: " + costoTotal);

    }
    
    static double calcularCostoEnvio(double peso, String zona){
        double resp = 0;
        switch (zona.toUpperCase()){
            case "NACIONAL":
                resp = peso * 5;
                break;
            case "INTERNACIONAL":
                resp = peso * 10;
                break;
        }
        
        return resp;
    }
    
    static double calcularTotalCompra(double precioProducto, double costoEnvio){
        return precioProducto + costoEnvio;
    }
    
    static void ejercicio10(){
        int stockActual, cantidadVendida, cantidadRecibida;
        System.out.println("Ingrese el stock actual del producto:" );
        stockActual = input.nextInt();
        System.out.println("Ingrese la cantidad vendida:" );
        cantidadVendida = input.nextInt();
        System.out.println("Ingrese la cantidad recibida:" );
        cantidadRecibida = input.nextInt();
        actualizarStock(stockActual, cantidadVendida, cantidadRecibida);
    }
    
    static void actualizarStock(int stockActual, int cantidadVendida,int cantidadRecibida){
        int nuevoStock;
        nuevoStock = stockActual - cantidadVendida + cantidadRecibida;
        System.out.println("El nuevo stock del producto es:" + nuevoStock);
    }
    
    static void ejercicio11(){
        int stockActual, cantidadVendida, cantidadRecibida;
        double precio;
        System.out.println("Ingrese el precio del producto:" );
        precio = input.nextDouble();
        calcularDescuentoEspecial(precio);
    }
    static void calcularDescuentoEspecial(double precio){
        double descuentoAplicado, precioFinal;
        descuentoAplicado = precio * descuento;
        precioFinal = precio - (precio * descuento);
        System.out.println("El descuento especial aplicado es: " + descuentoAplicado);
        System.out.println("EL precio final con descuento es: " + precioFinal);
    }
    
    static void ejercicio12(){
        double[] precios = {199.99,299.5,149.75,399,89.99};
        
        for (double precio : precios){
            System.out.println(precio);
        }
        
        precios[2] = 123.45;
        
        System.out.println("Precios Modificados:");
        for (double precio : precios){
            System.out.println(precio);
        }
    }
    
    static void ejercicio13(){
        double[] precios = {199.99,299.5,149.75,399,89.99};
        recursivoPrecios(precios,0);
        precios[2] = 123.45;
        System.out.println("Precios Modificados:");
        recursivoPrecios(precios,0);

    }
    
    static void recursivoPrecios(double[] precios, int i){
        if (i < precios.length){
            System.out.println(precios[i]);
            recursivoPrecios(precios, i+1);
        }
           
    }
}
    
