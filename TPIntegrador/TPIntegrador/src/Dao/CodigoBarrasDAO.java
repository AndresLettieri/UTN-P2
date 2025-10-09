package Dao;

import Config.DatabaseConnection;
import Entities.CodigoBarras;
import Entities.TipoCodigoBarras;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CodigoBarrasDAO implements GenericDAO<CodigoBarras> {

    @Override
    public void insertar(CodigoBarras cb, Connection conn) throws Exception {
        String sql = "INSERT INTO CodigoBarras (tipo, valor, fechaAsignacion, observaciones, eliminado) VALUES (?, ?, ?, ?, false)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cb.getTipo().name());
            stmt.setString(2, cb.getValor());
            if(cb.getFechaAsignacion() != null)
                stmt.setDate(3, Date.valueOf(cb.getFechaAsignacion()));
            else
                stmt.setNull(3, java.sql.Types.DATE);
            stmt.setString(4, cb.getObservaciones());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cb.setId(generatedKeys.getInt(1)); 
                } else {
                    throw new Exception("No se pudo obtener el ID generado para la categoría.");
                }
            }
        }
    }

    @Override
    public void actualizar(CodigoBarras cb, Connection conn) throws Exception {
        String sql = "UPDATE CodigoBarras SET tipo = ?, valor = ?, fechaAsignacion = ?, observaciones = ? WHERE id = ?";
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
    public void eliminar(long id, Connection conn) throws Exception {
        String sql = "UPDATE CodigoBarras SET eliminado = true WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void recuperar(long id, Connection conn) throws Exception {
        String sql = "UPDATE CodigoBarras SET eliminado = false WHERE id = ?";
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
        String sql = "SELECT * FROM CodigoBarras WHERE id = ? AND eliminado = false";
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
        String sql = "SELECT * FROM CodigoBarras WHERE eliminado = false";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            List<CodigoBarras> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
            return lista;
        }
    }
    
    
    public List<CodigoBarras> codigoBarrasDisponibles(Connection conn) throws Exception {
        String sql = "SELECT * FROM vw_CodigoBarrasDisponibles order by valor";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            List<CodigoBarras> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
            return lista;
        }
    }

    public CodigoBarras buscarPorValor(String filtro) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return buscarPorValor(filtro, conn);
        }
    }

    public CodigoBarras buscarPorValor(String filtro, Connection conn) throws Exception {
        String sql = "SELECT * FROM CodigoBarras WHERE valor = ? AND eliminado = false";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,filtro);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    // Mapea una fila del ResultSet a un objeto CodigoBarras
    private CodigoBarras mapRow(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String tipoStr = rs.getString("tipo");
        TipoCodigoBarras tipo = TipoCodigoBarras.valueOf(tipoStr);
        String valor = rs.getString("valor");
        LocalDate fecha = null;
        if (rs.getDate("fechaasignacion")!= null )
            fecha = rs.getDate("fechaasignacion").toLocalDate();
        String obs = rs.getString("observaciones");
        boolean eliminado = rs.getBoolean("eliminado");
        
        return new CodigoBarras(id, tipo, valor, eliminado, fecha, obs);
    }
}
