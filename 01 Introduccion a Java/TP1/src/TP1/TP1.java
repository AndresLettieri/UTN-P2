
package TP1;

import java.util.Scanner;

public class TP1 {

    public static void main(String[] args) {
        ejercicio2();
        ejercicio3();
        ejercicio4();
        ejercicio5();
        ejercicio6();
        ejercicio8();   
    }
    
    public static void ejercicio2(){
        System.out.println("¡Hola, Java!");
    }
    
    public static void ejercicio3() {
       String nombre = "Andres";
       int edad = 39;
       double altura = 1.75;
       boolean estudiante = true;

       System.out.println("Nombre: " + nombre);
       System.out.println("Edad: " + edad + " años");
       System.out.println("Altura: " + altura);
       System.out.println("Estudiante: " + estudiante);
    }
    
    public static void ejercicio4(){
        Scanner input = new Scanner(System.in);
        String nombre;
        int edad;

        System.out.println("Ingrese nombre:" );
        nombre = input.nextLine();
        System.out.println("Ingrese edad:" );
        edad = input.nextInt();
        System.out.println("Hola " + nombre + "\n" + "tu edad es " + edad + " años");
        
    }
    public static void ejercicio5(){        
        Scanner input = new Scanner(System.in);
        int num1, num2;
        
        System.out.println("Ingrese el primer número: ");
        num1 = Integer.parseInt(input.nextLine());
        System.out.println("Ingrese el segundo número: ");
        num2 = Integer.parseInt(input.nextLine());
        System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
        System.out.println(num1 + " - " + num2 + " = " + (num1 - num2));
        System.out.println(num1 + " * " + num2 + " = " + (num1 * num2));
        System.out.println(num1 + " / " + num2 + " = " + (num1 / num2));
    }
    public static void ejercicio6(){
        System.out.println("Nombre: juan Pérez \nEdad: 30 años \nDirección: \"Calle Falsa 123\"");
    }
    
    public static void ejercicio8(){
        Scanner input = new Scanner(System.in);
        int num1, num2;
        
        System.out.println("Ingrese el primer número: ");
        num1 = Integer.parseInt(input.nextLine());
        System.out.println("Ingrese el segundo número: ");
        num2 = Integer.parseInt(input.nextLine());
        
        System.out.println(num1 + " / " + num2 + " = " + (num1 / num2));

        System.out.println(num1 + " / " + num2 + " = " + ((num1*1.0) / num2));

    }



}
