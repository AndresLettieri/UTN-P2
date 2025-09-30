package Dao;

import Config.DatabaseConnection;
import Entities.Categoria;
import Entities.CodigoBarras;
import Entities.Producto;
import Entities.TipoCodigoBarras;

import java.sql.*;
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
            stmt.setLong(5, p.getCodigoBarras().getId());
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
            stmt.setLong(6, p.getCodigoBarras().getId());
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
                p.id AS p_id, p.nombre AS p_nombre, p.marca AS p_marca, p.precio, p.peso,
                c.id AS c_id, c.nombre AS c_nombre, c.descripcion AS c_descripcion,
                cb.id AS cb_id, cb.tipo, cb.valor, cb.fechaAsignacion , cb.observaciones
            FROM Producto p
            JOIN Categoria c ON p.idCategoria = c.id
            JOIN CodigoBarras cb ON p.idCodigoBarras  = cb.id
            WHERE p.eliminado = false AND cb.eliminado = false AND p.id = ?
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
                p.id AS p_id, p.nombre AS p_nombre, p.marca AS p_marca, p.precio, p.peso,
                c.id AS c_id, c.nombre AS c_nombre, c.descripcion AS c_descripcion,
                cb.id AS cb_id, cb.tipo, cb.valor, cb.fechaAsignacion, cb.observaciones
            FROM Producto p
            JOIN Categoria c ON p.idCategoria = c.id
            JOIN CodigoBarras cb ON p.idCodigoBarras = cb.id
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
                p.id AS p_id, p.nombre AS p_nombre, p.marca AS p_marca, p.precio, p.peso,
                c.id AS c_id, c.nombre AS c_nombre, c.descripcion AS c_descripcion,
                cb.id AS cb_id, cb.tipo, cb.valor, cb.fechaAsignacion, cb.observaciones
            FROM Producto p
            JOIN Categoria c ON p.idCategoria = c.id
            JOIN CodigoBarras cb ON p.idCodigoBarras = cb.id
            WHERE p.eliminado = false AND cb.eliminado = false AND p.nombre LIKE ?
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
                p.id AS p_id, p.nombre AS p_nombre, p.marca AS p_marca, p.precio, p.peso,
                c.id AS c_id, c.nombre AS c_nombre, c.descripcion AS c_descripcion,
                cb.id AS cb_id, cb.tipo, cb.valor, cb.fechaAsignacion, cb.observaciones
            FROM Producto p
            JOIN Categoria c ON p.idCategoria = c.id
            JOIN CodigoBarras cb ON p.idCodigoBarras = cb.id
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

        String catNombre = rs.getString("c_nombre");
        String catDescripcion = rs.getString("c_descripcion");
        Categoria categoria = new Categoria(catNombre, catDescripcion);

        long cbId = rs.getLong("cb_id");
        TipoCodigoBarras tipo = TipoCodigoBarras.valueOf(rs.getString("tipo"));
        String valor = rs.getString("valor");
        Date fecha = rs.getDate("fecha_asignacion");
        String obs = rs.getString("observaciones");

        CodigoBarras cb = new CodigoBarras(cbId, tipo, valor, fecha.toLocalDate(), obs);

        Producto p = new Producto(id, nombre, marca, categoria, precio, peso);
        p.setCodigoBarras(cb);

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
