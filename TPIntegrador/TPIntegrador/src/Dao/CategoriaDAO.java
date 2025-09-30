package Dao;

import Config.DatabaseConnection;
import Entities.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements GenericDAO<Categoria> {

    @Override
    public void insertar(Categoria c, Connection conn) throws Exception {
        String sql = "INSERT INTO categoria (nombre, descripcion) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, c.getNombre());
            stmt.setString(2, c.getDescripcion());
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void actualizar(Categoria c, Connection conn) throws Exception {
        String sql = "UPDATE categoria SET nombre = ?, descripcion = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getNombre());
            stmt.setString(2, c.getDescripcion());
            stmt.setInt(3, c.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(long id, Connection conn) throws Exception {
        String sql = "DELETE FROM categoria WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void recuperar(long id, Connection conn) throws Exception {
        throw new UnsupportedOperationException("CategoriaDAO no soporta recuperar (no usa soft delete)");
    }

    @Override
    public Categoria getById(long id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return getById(id, conn);
        }
    }

    private Categoria getById(long id, Connection conn) throws Exception {
        String sql = "SELECT * FROM categoria WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCategoria(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Categoria> getAll() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return getAll(conn);
        }
    }

    public List<Categoria> getAll(Connection conn) throws Exception {
        String sql = "SELECT * FROM categoria";
        List<Categoria> lista = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapResultSetToCategoria(rs));
            }
        }
        return lista;
    }

    private Categoria mapResultSetToCategoria(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String descripcion = rs.getString("descripcion");
        return new Categoria(nombre, descripcion);
    }
}
