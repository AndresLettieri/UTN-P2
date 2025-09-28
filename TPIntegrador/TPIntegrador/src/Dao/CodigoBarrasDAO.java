package Dao;

import Config.DatabaseConnection;
import Config.TransactionManager;
import Entities.CodigoBarras;
import Entities.TipoCodigoBarras;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CodigoBarrasDAO implements GenericDAO<CodigoBarras> {

    @Override
    public void insertar(CodigoBarras cb) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            insertar(cb, conn);
        }
    }

    public void insertar(CodigoBarras cb, Connection conn) throws Exception {
        String sql = "INSERT INTO codigo_barras (tipo, valor, fecha_asignacion, observaciones, eliminado) VALUES (?, ?, ?, ?, false)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cb.getTipo().name());
            stmt.setString(2, cb.getValor());
            stmt.setDate(3, Date.valueOf(cb.getFechaAsignacion()));
            stmt.setString(4, cb.getObservaciones());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    cb.setId(keys.getLong(1));
                }
            }
        }
    }

    @Override
    public void actualizar(CodigoBarras cb) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            actualizar(cb, conn);
        }
    }

    public void actualizar(CodigoBarras cb, Connection conn) throws Exception {
        String sql = "UPDATE codigo_barras SET tipo = ?, valor = ?, fecha_asignacion = ?, observaciones = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cb.getTipo().name());
            stmt.setString(2, cb.getValor());
            stmt.setDate(3, Date.valueOf(cb.getFechaAsignacion()));
            stmt.setString(4, cb.getObservaciones());
            stmt.setLong(5, cb.getId());
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
        String sql = "UPDATE codigo_barras SET eliminado = true WHERE id = ?";
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
        String sql = "UPDATE codigo_barras SET eliminado = false WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public CodigoBarras getById(long id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return getById(id, conn);
        }
    }

    public CodigoBarras getById(long id, Connection conn) throws Exception {
        String sql = "SELECT * FROM codigo_barras WHERE id = ? AND eliminado = false";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<CodigoBarras> getAll() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return getAll(conn);
        }
    }

    public List<CodigoBarras> getAll(Connection conn) throws Exception {
        String sql = "SELECT * FROM codigo_barras WHERE eliminado = false";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            List<CodigoBarras> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
            return lista;
        }
    }

    public List<CodigoBarras> buscarPorValor(String filtro) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return buscarPorValor(filtro, conn);
        }
    }

    public List<CodigoBarras> buscarPorValor(String filtro, Connection conn) throws Exception {
        String sql = "SELECT * FROM codigo_barras WHERE valor LIKE ? AND eliminado = false";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + filtro + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                List<CodigoBarras> lista = new ArrayList<>();
                while (rs.next()) {
                    lista.add(mapRow(rs));
                }
                return lista;
            }
        }
    }

    // Mapea una fila del ResultSet a un objeto CodigoBarras
    private CodigoBarras mapRow(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String tipoStr = rs.getString("tipo");
        TipoCodigoBarras tipo = TipoCodigoBarras.valueOf(tipoStr);
        String valor = rs.getString("valor");
        LocalDate fecha = rs.getDate("fecha_asignacion").toLocalDate();
        String obs = rs.getString("observaciones");
        return new CodigoBarras(id, tipo, valor, fecha, obs);
    }
}
