package Config;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    private static ThreadLocal<Connection> threadLocalConnection = new ThreadLocal<>();

    // Inicia una nueva transacción
    public static void beginTransaction() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        conn.setAutoCommit(false);  // Desactiva el auto-commit para poder gestionar la transacción
        threadLocalConnection.set(conn);  // Almacena la conexión actual en un ThreadLocal
    }

    // Realiza commit de la transacción
    public static void commit() throws SQLException {
        Connection conn = threadLocalConnection.get();
        if (conn != null) {
            conn.commit();  // Realiza el commit de la transacción
        }
    }

    // Realiza rollback de la transacción
    public static void rollback() throws SQLException {
        Connection conn = threadLocalConnection.get();
        if (conn != null) {
            conn.rollback();  // Deshace los cambios de la transacción
        }
    }

    // Cierra la conexión y limpia el ThreadLocal
    public static void close() throws SQLException {
        Connection conn = threadLocalConnection.get();
        if (conn != null) {
            conn.close();  // Cierra la conexión
            threadLocalConnection.remove();  // Limpia el ThreadLocal
        }
    }

    // Obtiene la conexión actual para usarla dentro de la transacción
    public static Connection getConnection() {
        return threadLocalConnection.get();
    }
}
    

