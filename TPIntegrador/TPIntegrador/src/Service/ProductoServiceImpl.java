package Service;

import Config.DatabaseConnection;
import Dao.ProductoDAO;
import Entities.Producto;

import java.sql.Connection;
import java.util.List;

public class ProductoServiceImpl implements GenericService<Producto> {

    private final ProductoDAO productoDAO;

    public ProductoServiceImpl(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public void insertar(Producto producto) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // Validaciones
                if (producto.getNombre() == null || producto.getNombre().isBlank()) {
                    throw new IllegalArgumentException("El nombre es obligatorio.");
                }
                if (producto.getNombre().length() > producto.getMAX_NOMBRE_LENGTH()) {
                    throw new IllegalArgumentException("El nombre supera la longitud máxima.");
                }

                productoDAO.insertar(producto, conn);
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
    public void actualizar(Producto producto) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                productoDAO.actualizar(producto, conn);
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
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                productoDAO.eliminar(id, conn);
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
    public Producto getById(long id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return productoDAO.getById(id, conn);
        }
    }

    @Override
    public List<Producto> getAll() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return productoDAO.getAll(conn);
        }
    }

    @Override
    public void recuperar(long id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                productoDAO.recuperar(id, conn);
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    public List<Producto> buscarPorNombre(String filtro) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return productoDAO.buscarPorNombre(filtro, conn);
        }
    }

    public Producto buscarPorValorCodigoBarras(String valor) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return productoDAO.buscarPorValorCodigoBarras(valor, conn);
        }
    }
}
