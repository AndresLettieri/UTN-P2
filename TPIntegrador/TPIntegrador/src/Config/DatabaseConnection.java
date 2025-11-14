package Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/fabrica";
    private static String user;
    private static String password;
    private static Connection connection = null;
    
    static{ 
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            throw new RuntimeException("Error: No se encontró el driver JDBC");
        }
    }

    public static void setCredentials(String user, String password) {
        DatabaseConnection.user = user;
        DatabaseConnection.password = password;
    }
        
    // Método para obtener la conexión
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, user, password);
            }
        } catch (SQLException e) {
            if (e.getSQLState() != null) {
                if (!e.getSQLState().equals("28000")){
                    e.printStackTrace();
                }
            }
        }
        return connection;
    }
    

    // Método para cerrar la conexión
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}