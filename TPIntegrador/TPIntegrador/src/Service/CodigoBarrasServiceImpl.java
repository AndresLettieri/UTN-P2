package Service;

import Config.DatabaseConnection;
import Dao.CodigoBarrasDAO;
import Dao.ProductoDAO;
import Entities.CodigoBarras;

import java.sql.Connection;
import java.util.List;

public class CodigoBarrasServiceImpl implements GenericService<CodigoBarras> {
    
    private final CodigoBarrasDAO codigoBarrasDAO;
    
    public CodigoBarrasServiceImpl(CodigoBarrasDAO codigoBarrasDAO){
        this.codigoBarrasDAO = codigoBarrasDAO;
    }

    @Override
    public void insertar(CodigoBarras codigoBarras) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                validarCodigoBarras(codigoBarras);
                codigoBarrasDAO.insertar(codigoBarras, conn);
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    @Override
    public void actualizar(CodigoBarras codigoBarras) throws Exception {
        if (codigoBarras.getId() <= 0) {
            throw new IllegalArgumentException("El ID del código de barras debe ser válido.");
        }
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                validarCodigoBarras(codigoBarras);
                codigoBarrasDAO.actualizar(codigoBarras, conn);
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    @Override
    public void eliminar(long id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // Desasociar de producto
                ProductoDAO.desasociarCodigoBarras(id, conn);

                // Baja lógica del código de barras
                codigoBarrasDAO.eliminar(id, conn);

                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    @Override
    public void recuperar(long id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                codigoBarrasDAO.recuperar(id, conn);
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    @Override
    public CodigoBarras getById(long id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        return codigoBarrasDAO.getById(id);
    }

    @Override
    public List<CodigoBarras> getAll() throws Exception {
        return codigoBarrasDAO.getAll();
    }

    public List<CodigoBarras> codigoBarrasDisponibles() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return codigoBarrasDAO.codigoBarrasDisponibles(conn);
        }
    }
    
    public CodigoBarras buscarPorValor(String filtro) throws Exception {
        if (filtro == null || filtro.isBlank()) {
            throw new IllegalArgumentException("El filtro de búsqueda no puede estar vacío.");
        }
        return codigoBarrasDAO.buscarPorValor(filtro);
    }
    
    // Validaciones comunes para insertar/actualizar
    private void validarCodigoBarras(CodigoBarras cb) {
        if (cb.getTipo() == null) {
            throw new IllegalArgumentException("El tipo de código de barras es obligatorio.");
        }
        if (cb.getValor() == null || cb.getValor().isBlank()) {
            throw new IllegalArgumentException("El valor del código de barras es obligatorio.");
        }
        if (cb.getValor().length() > 20) {
            throw new IllegalArgumentException("El valor del código de barras no puede superar los 20 caracteres.");
        }
        if (cb.getObservaciones() != null && cb.getObservaciones().length() > 255) {
            throw new IllegalArgumentException("Las observaciones no pueden superar los 255 caracteres.");
        }
    }
}
