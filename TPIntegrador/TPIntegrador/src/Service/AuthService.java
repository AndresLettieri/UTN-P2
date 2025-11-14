package Service;

import Config.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AuthService {
    private static final int MAX_INTENTOS = 3;

    public boolean loginInteractivo() {
        Scanner sc = new Scanner(System.in);
        int intentos = 0;

        System.out.println("=== LOGIN ===");

        while (intentos < MAX_INTENTOS) {
            String user;
            String pass;
            
            System.out.print("Usuario: ");
            user = sc.nextLine().trim();
            System.out.print("Contraseña: ");
            pass = sc.nextLine().trim();

            if (validarCredenciales(user, pass)) {
                return true;
            }

            intentos++;
            System.out.println("Usuario o contraseña incorrecto" );
        }
        
        System.out.println("Se agotaron los intentos de ingreso. Se cerrará el programa." );
        return false;
    }

    private boolean validarCredenciales(String user, String pass) {
        boolean resp = false;
        try {
            DatabaseConnection.setCredentials(user, pass);
            Connection conn = DatabaseConnection.getConnection();

            if (conn != null && !conn.isClosed()) {
                resp = true;
            }
        } catch (SQLException e) {
            resp = false;
        } finally {
            return resp;
        }
    }
}