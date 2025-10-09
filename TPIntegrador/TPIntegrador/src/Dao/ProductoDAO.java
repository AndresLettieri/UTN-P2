package Dao;

import Config.DatabaseConnection;
import Entities.Categoria;
import Entities.CodigoBarras;
import Entities.Producto;
import Entities.TipoCodigoBarras;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements GenericDAO<Producto> {

    @Override
    public void insertar(Producto p, Connection conn) throws Exception {
        String sql = "INSERT INTO Producto (nombre, marca, precio, peso, eliminado, idCodigoBarras, idCategoria) " +
                     "VALUES (?, ?, ?, ?, false, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getMarca());
            stmt.setDouble(3, p.getPrecio());
            stmt.setDouble(4, p.getPeso());
            if (p.getCodigoBarras() != null)
                stmt.setLong(5, p.getCodigoBarras().getId());
            else
                stmt.setNull(5, Types.BIGINT);
            stmt.setInt(6, p.getCategoria().getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void actualizar(Producto p, Connection conn) throws Exception {
        String sql = "UPDATE producto SET nombre = ?, marca = ?, idCategoria = ?, precio = ?, peso = ?, idCodigoBarras = ? " +
                     "WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getMarca());
            stmt.setInt(3, p.getCategoria().getId());
            stmt.setDouble(4, p.getPrecio());
            stmt.setDouble(5, p.getPeso());
            if (p.getCodigoBarras() != null)
                stmt.setLong(6, p.getCodigoBarras().getId());
            else
                stmt.setNull(6, Types.BIGINT);
            stmt.setLong(7, p.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(long id, Connection conn) throws Exception {
        String sql = "UPDATE producto SET eliminado = true WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void recuperar(long id, Connection conn) throws Exception {
        String sql = "UPDATE producto SET eliminado = false WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Producto getById(long id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return getById(id, conn);
        }
    }

    public Producto getById(long id, Connection conn) throws Exception {
        String sql = """
            SELECT 
                p.id AS p_id, p.nombre AS p_nombre, p.marca AS p_marca, p.precio, p.peso, p.eliminado as p_eliminado,
                c.id AS c_id, c.nombre AS c_nombre, c.descripcion AS c_descripcion,
                cb.id AS cb_id, cb.tipo, cb.valor, cb.fechaAsignacion , cb.observaciones, cb.eliminado as cb_eliminado
            FROM Producto p
            INNER JOIN Categoria c ON p.idCategoria = c.id
            LEFT JOIN CodigoBarras cb ON p.idCodigoBarras  = cb.id
            WHERE p.eliminado = false AND p.id = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapProducto(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Producto> getAll() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return getAll(conn);
        }
    }

    public List<Producto> getAll(Connection conn) throws Exception {
        String sql = """
            SELECT 
                p.id AS p_id, p.nombre AS p_nombre, p.marca AS p_marca, p.precio, p.peso, p.eliminado as p_eliminado,
                c.id AS c_id, c.nombre AS c_nombre, c.descripcion AS c_descripcion,
                cb.id AS cb_id, cb.tipo, cb.valor, cb.fechaAsignacion, cb.observaciones, cb.eliminado as cb_eliminado
            FROM Producto p
            INNER JOIN Categoria c ON p.idCategoria = c.id
            LEFT JOIN CodigoBarras cb ON p.idCodigoBarras = cb.id
            WHERE p.eliminado = false AND cb.eliminado = false
        """;

        List<Producto> lista = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapProducto(rs));
            }
        }
        return lista;
    }

    public List<Producto> buscarPorNombre(String filtro, Connection conn) throws Exception {
        String sql = """
            SELECT 
                p.id AS p_id, p.nombre AS p_nombre, p.marca AS p_marca, p.precio, p.peso, p.eliminado as p_eliminado,
                c.id AS c_id, c.nombre AS c_nombre, c.descripcion AS c_descripcion,
                cb.id AS cb_id, cb.tipo, cb.valor, cb.fechaAsignacion, cb.observaciones, cb.eliminado as cb_eliminado
            FROM Producto p
            INNER JOIN Categoria c ON p.idCategoria = c.id
            LEFT JOIN CodigoBarras cb ON p.idCodigoBarras = cb.id
            WHERE p.eliminado = false AND p.nombre LIKE ?
        """;

        List<Producto> lista = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + filtro + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapProducto(rs));
                }
            }
        }
        return lista;
    }

    public Producto buscarPorValorCodigoBarras(String valor, Connection conn) throws Exception {
        String sql = """
            SELECT 
                p.id AS p_id, p.nombre AS p_nombre, p.marca AS p_marca, p.precio, p.peso, p.eliminado as p_eliminado,
                c.id AS c_id, c.nombre AS c_nombre, c.descripcion AS c_descripcion,
                cb.id AS cb_id, cb.tipo, cb.valor, cb.fechaAsignacion, cb.observaciones, cb.eliminado as cb_eliminado
            FROM Producto p
            INNER JOIN Categoria c ON p.idCategoria = c.id
            INNER JOIN CodigoBarras cb ON p.idCodigoBarras = cb.id
            WHERE p.eliminado = false AND cb.eliminado = false AND cb.valor = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, valor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapProducto(rs);
                }
            }
        }
        return null;
    }
    
    public List<String> productosSinCodigo(Connection conn) throws Exception {
        String sql = " SELECT * FROM vw_ProductoSinCodigoBarras";

        List<String> lista = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                String nombre, marca, categoria;
                long id;
                while (rs.next()) {
                    nombre = rs.getString("nombre");
                    marca = rs.getString("marca");
                    categoria = rs.getString("categoria");
                    id = rs.getInt("id");
                    lista.add("ID: " + id + ". Nombre: " + nombre + ". Marca: " + marca + ". Categoria: " + categoria + ".");
                }
            }
        }
        return lista;
    }
    
    public List<String> productosDuplicados(Connection conn) throws Exception {
        String sql = " SELECT * FROM vw_NombreProductoDuplicado";

        List<String> lista = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                String nombre;
                int cantidad;
                while (rs.next()) {
                    nombre = rs.getString("nombre");
                    cantidad = rs.getInt("cantidad");
                    lista.add("Nombre: " + nombre + ". Cantidad: " + cantidad + ".");
                }
            }
        }
        return lista;
    }
    
    
    
    
    public int obtenerIdCodigoBarras(long productoId, Connection conn) throws SQLException {
        String sql = "SELECT idCodigoBarras FROM Producto WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, productoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    // Mapear Producto con Categoria y CodigoBarras
    private Producto mapProducto(ResultSet rs) throws SQLException {
        long id = rs.getLong("p_id");
        String nombre = rs.getString("p_nombre");
        String marca = rs.getString("p_marca");
        double precio = rs.getDouble("precio");
        double peso = rs.getDouble("peso");
        boolean eliminadoP = rs.getBoolean("p_eliminado");
        
        
        int catId = rs.getInt(("c_id"));
        String catNombre = rs.getString("c_nombre");
        String catDescripcion = rs.getString("c_descripcion");
        Categoria categoria = new Categoria(catId, catNombre, catDescripcion);
        
        Producto p = new Producto(id, nombre, marca,eliminadoP, categoria, precio, peso);
        
        long cbId = rs.getLong("cb_id");
            
        //Si es cero no tiene codigo de barras asignado
        if(cbId != 0){
            
            TipoCodigoBarras tipo = TipoCodigoBarras.valueOf(rs.getString("tipo"));
            String valor = rs.getString("valor");
            LocalDate fecha = null;
            if (rs.getDate("fechaasignacion")!= null )
                fecha = rs.getDate("fechaasignacion").toLocalDate();
            String obs = rs.getString("observaciones");
            boolean eliminadoCB = rs.getBoolean("cb_eliminado");

            CodigoBarras cb = new CodigoBarras(cbId, tipo, valor, eliminadoCB, fecha, obs);
            p.setCodigoBarras(cb);
        }
        
        return p;
    }
    
    public static boolean existenProductosPorCategoria(long categoriaId, Connection conn) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Producto WHERE idCategoria = ? AND eliminado = false";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, categoriaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    public static void desasociarCodigoBarras(long idCodigoBarras, Connection conn) throws SQLException {
    String sql = "UPDATE Producto SET idCodigoBarras = NULL WHERE idCodigoBarras = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setLong(1, idCodigoBarras);
        ps.executeUpdate();
    }
}

}
