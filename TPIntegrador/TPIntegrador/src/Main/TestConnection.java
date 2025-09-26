package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class TestConnection {
    
     // Parámetros de la conexión
    private static final String URL = "jdbc:mysql://localhost:3306/fabrica";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "12345678";
    
    
    public static void main(String[] args){
        probarConexion();
    }
    
     public static void probarConexion() {
        Connection conexion = null;

        try {
            // Intentar conectar a la base de datos
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            if (conexion != null) {
                System.out.println("¡Conexión exitosa a la base de datos!");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        } finally {
            // Cerrar la conexión si fue establecida
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
}
