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
    public void insertar(Producto p) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            insertar(p, conn);
        }
    }

    public void insertar(Producto p, Connection conn) throws Exception {
        String sql = "INSERT INTO producto (nombre, marca, categoria_id, precio, peso, codigo_barras_id, eliminado) " +
                     "VALUES (?, ?, ?, ?, ?, ?, false)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getMarca());
            stmt.setInt(3, p.getCategoria().getId());
            stmt.setDouble(4, p.getPrecio());
            stmt.setDouble(5, p.getPeso());
            stmt.setLong(6, p.getCodigoBarras().getId());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    p.setId(keys.getLong(1));
                }
            }
        }
    }

    @Override
    public void actualizar(Producto p) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            actualizar(p, conn);
        }
    }

    public void actualizar(Producto p, Connection conn) throws Exception {
        String sql = "UPDATE producto SET nombre = ?, marca = ?, categoria_id = ?, precio = ?, peso = ?, codigo_barras_id = ? " +
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
    public void eliminar(long id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            eliminar(id, conn);
        }
    }

    public void eliminar(long id, Connection conn) throws Exception {
        String sql = "UPDATE producto SET eliminado = true WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void recuperar(long id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            recuperar(id, conn);
        }
    }

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
                cb.id AS cb_id, cb.tipo, cb.valor, cb.fecha_asignacion, cb.observaciones
            FROM producto p
            JOIN categoria c ON p.categoria_id = c.id
            JOIN codigo_barras cb ON p.codigo_barras_id = cb.id
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
                cb.id AS cb_id, cb.tipo, cb.valor, cb.fecha_asignacion, cb.observaciones
            FROM producto p
            JOIN categoria c ON p.categoria_id = c.id
            JOIN codigo_barras cb ON p.codigo_barras_id = cb.id
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
                cb.id AS cb_id, cb.tipo, cb.valor, cb.fecha_asignacion, cb.observaciones
            FROM producto p
            JOIN categoria c ON p.categoria_id = c.id
            JOIN codigo_barras cb ON p.codigo_barras_id = cb.id
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
                cb.id AS cb_id, cb.tipo, cb.valor, cb.fecha_asignacion, cb.observaciones
            FROM producto p
            JOIN categoria c ON p.categoria_id = c.id
            JOIN codigo_barras cb ON p.codigo_barras_id = cb.id
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
}
