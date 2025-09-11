package tp5;


public class TP5 {

    public static void main(String[] args) {
        ejercicio1();
        ejercicio2();
        ejercicio3();
        ejercicio4();
        ejercicio5();
        ejercicio6();
        ejercicio7();
        ejercicio8();
        ejercicio9();
        ejercicio10();
        ejercicio11();
        ejercicio12();
        ejercicio13();
        ejercicio14();
    }
    
    private static void ejercicio1(){
        System.out.println("----------------------------------");
        System.out.println("Ejercicio 1");
        //Asignacion 1 a 1
        Pasaporte pasaporte = new Pasaporte("ABC123","2020-03-01","imagen","JGP");
        Titular titular = new Titular("Andres","33222444");
        titular.setPasaporte(pasaporte);
        System.out.println("Titular: " + titular.getNombre());
        System.out.println("Pasaporte:" + pasaporte.getNumero());
        System.out.println("Pasaporte asociado al titular: " + titular.getPasaporte().getNumero());
        System.out.println("Titular asociado al pasaporte: " + pasaporte.getTitular().getNombre());
        
        //Composicion 1 a 1
        pasaporte.mostrarFoto();
        
    }
    
    private static void ejercicio2(){
        System.out.println("----------------------------------");
        System.out.println("Ejercicio 2");

        //Asignacion y agregacion 1 a 1
        Bateria bateria = new Bateria("Acme", 100);
        Celular celular = new Celular("1234567890","LG","ABC", bateria);
        Usuario usuario = new Usuario("Andres","33222444");
        celular.setUsuario(usuario);
        System.out.println("Usuario: " + usuario.getNombre());
        System.out.println("Celular:" + celular.getMarca() + " " + celular.getModelo());
        System.out.println("Usuario asociado al celular: " + celular.getUsuario().getNombre());
        System.out.println("Celular asociado al usuario: " + usuario.getCelular().getMarca() + " " + usuario.getCelular().getModelo());
        celular.mostrarBateria();
        celular.setBateria(null);
        System.out.println("La bateria sigue existiendo " + bateria.toString());
        celular.mostrarBateria();
    }
    
    private static void ejercicio3(){
        
        System.out.println("----------------------------------");
        System.out.println("Ejercicio 3");
    
        //Asociacion unidireccional y agregacion 1 a 1
        Editorial editorial = new Editorial("santillana", "calle falsa 123");
        Autor autor = new Autor("Stephen King","Estadounidense");
        Libro libro = new Libro("El resplandor", "99990000", autor, editorial);
        libro.setAutor(autor);
        
        libro.mostrarAutor();
        
        libro.mostrarEditorial();
        libro.setEditorial(null);
        System.out.println("La editorial sigue existiendo " + editorial.getNombre());
        libro.getEditorial();
    }
    
    private static void ejercicio4(){
        System.out.println("----------------------------------");
        System.out.println("Ejercicio 4");

       //Asignacion y agregacion 1 a 1
        Banco banco = new Banco("Santander", "9922233445");
        TarjetaDeCredito tarjetaDeCredito = new TarjetaDeCredito("1234567890","2028-01-01", banco);
        Cliente cliente = new Cliente("Andres","33222444");
        tarjetaDeCredito.setCliente(cliente);
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Tarjeta de credito:" + tarjetaDeCredito.getNumero());
        System.out.println("Cliente asociado a la tarjeta: " + tarjetaDeCredito.getCliente().getNombre());
        System.out.println("Tarjeta asociada al cliente: " + cliente.getTarjetaDeCredito().getNumero());
        tarjetaDeCredito.mostrarBanco();
        tarjetaDeCredito.setBanco(null);
        System.out.println("El banco sigue existiendo " + banco.toString());
        tarjetaDeCredito.mostrarBanco();
    }
    
    private static void ejercicio5(){
        System.out.println("----------------------------------");
        System.out.println("Ejercicio 5");

        //Asignacion 1 a 1
        Computadora computadora = new Computadora("Dell","RRR1234GGG","Asus","123456");
        Propietario propietario = new Propietario("Andres","33222444");
        propietario.setComputadora(computadora);
        System.out.println("Propietario: " + propietario.getNombre());
        System.out.println("Computadora:" + computadora.getMarca() + " Serie: " + computadora.getNumeroDeSerie());
        System.out.println("Computadora asociada al propietario: " + propietario.getComputadora().getMarca());
        System.out.println("Propietario asociado a la computadora: " + computadora.GetPropietario().getNombre());
        
        //Composicion 1 a 1
        computadora.mostrarPlacaMadre();
     
    } 
    
    private static void ejercicio6(){
        System.out.println("----------------------------------");
        System.out.println("Ejercicio 6");
        
        //Asociacion unidireccional y agregacion 1 a 1
        ClienteReserva clienteReserva = new ClienteReserva("Andres", "1155557777");
        Mesa mesa = new Mesa(9,4);
        Reserva reserva = new Reserva("22-09-2025", "21:30", clienteReserva);
        reserva.setMesa(mesa);
        
        reserva.mostrarMesa();
        reserva.mostrarCliente();
       
        reserva.setMesa(null);
        System.out.println("La mesa sigue existiendo " + mesa.toString());
        reserva.getMesa();
    }
    
    private static void ejercicio7(){
        System.out.println("----------------------------------");
        System.out.println("Ejercicio 7");
        
        //Asignacion y agregacion 1 a 1
        Motor motor = new Motor("V8", "7777777");
        Vehiculo vehiculo = new Vehiculo("AB123CD","Racer", motor);
        Conductor conductor = new Conductor("Andres","L4567");
        vehiculo.setConductor(conductor);
        System.out.println("Conductor: " + conductor.getNombre());
        System.out.println("Vehiculo: " + vehiculo.getPatente());
        System.out.println("Conductor asociado al vehiculo: " + vehiculo.getConductor().getNombre());
        System.out.println("Vechiculo asociado al conductor: " + conductor.GetVehiculo().getPatente());
        vehiculo.mostrarMotor();
        vehiculo.setMotor(null);
        System.out.println("El motor sigue existiendo " + motor.toString());
        vehiculo.mostrarMotor();    
    }
    
    private static void ejercicio8(){
        System.out.println("----------------------------------");
        System.out.println("Ejercicio 8");
        
        //composicion y agregacion 1 a 1
        UsuarioDocumento usuarioDocumento = new UsuarioDocumento("Andres", "Andres@mail.com");
        Documento documento = new Documento("DNI","Documento de identidad","!#$%&/", "2025-09-10", usuarioDocumento);
        
        System.out.println(documento.getFirmaDigital().toString());
        documento.getFirmaDigital().mostrarUsuarioDocumento();
        documento.getFirmaDigital().setUsuarioDocumento(null);
        documento.getFirmaDigital().mostrarUsuarioDocumento();  
       
    }
    
    private static void ejercicio9(){
        System.out.println("----------------------------------");
        System.out.println("Ejercicio 9");
        
        //Unidireccional
        Profesional profesional = new Profesional("Doctor", "Clinico");
        Paciente paciente = new Paciente("nombre", "osde");
        CitaMedica citaMedica = new CitaMedica("2025-10-01", "15:15", paciente, profesional);
        
        System.out.println(citaMedica.toString());
    
    }
    
    private static void ejercicio10(){
        System.out.println("----------------------------------");
        System.out.println("Ejercicio 10");

        //Asignacion 1 a 1
        CuentaBancaria cuentaBancaria = new CuentaBancaria("2232132131421231", 1500, 5678, "2022-01-01");
        TitularCuentaBancaria titularCuentaBancaria  = new TitularCuentaBancaria("Andres","33222444");
        titularCuentaBancaria.setCuentaBancaria(cuentaBancaria);
        System.out.println("Titular: " + titularCuentaBancaria.getNombre());
        System.out.println("Cuenta bancaria:" + cuentaBancaria.getCbu());
        System.out.println("Cuenta asociada al titular: " + titularCuentaBancaria.getCuentaBancaria().getCbu());
        System.out.println("Titular asociado a la cuenta: " + cuentaBancaria.getTitularCuentaBancaria().getNombre());
        
        //Composicion 1 a 1
        cuentaBancaria.mostrarClaveSeguridad();
    }
    
    private static void ejercicio11(){
        System.out.println("----------------------------------");
        System.out.println("Ejercicio 11");
        
        //Asociacion unidireccional y dependencia de uso
        Artista artista = new Artista("Rolling Stones", "Rock");
        Cancion cancion = new Cancion("Bitch", artista);
        System.out.println(cancion.getArtista());
        
        Reproductor reproductor = new Reproductor();
        reproductor.reproducir(cancion);
        
    }
    
    private static void ejercicio12(){
        System.out.println("----------------------------------");
        System.out.println("Ejercicio 12");
        
        //Asociacion unidireccional y dependencia de uso
        Contribuyente contribuyente = new Contribuyente("Andres", "233334444123");
        Impuesto impuesto = new Impuesto(1500, contribuyente);
        System.out.println(impuesto.getContribuyente());
        
        Calculadora calculadora = new Calculadora();
        calculadora.calcular(impuesto);   
    }
    
    private static void ejercicio13(){
        System.out.println("----------------------------------");
        System.out.println("Ejercicio 13");

        //Asociacion unidireccional y dependencia de creacion
        UsuarioQR usuarioQR = new UsuarioQR("Andres", "andres@mail.com");
        GeneradorQR generadorQR = new GeneradorQR();
        generadorQR.generar("texto para QR", usuarioQR);
        
    }
    
    private static void ejercicio14(){
        System.out.println("----------------------------------");
        System.out.println("Ejercicio 14");
        
        //Asociacion unidireccional y dependencia de creacion
        Proyecto proyecto = new Proyecto("Pelicula nueva", 88);
        EditorVideo editorVideo = new EditorVideo();
        editorVideo.exportar("mkv", proyecto); 
    }
    
}
