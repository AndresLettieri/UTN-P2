package Service;

import Config.DatabaseConnection;
import Dao.ProductoDAO;
import Dao.CodigoBarrasDAO;
import Entities.Producto;
import Entities.CodigoBarras;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class ProductoServiceImpl implements GenericService<Producto> {

    private final ProductoDAO productoDAO;
    private final CodigoBarrasDAO codigoBarrasDAO;
    
    public ProductoServiceImpl(ProductoDAO productoDAO, CodigoBarrasDAO codigoBarrasDAO) {
        this.productoDAO = productoDAO;
        this.codigoBarrasDAO = codigoBarrasDAO;
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
                
                if (producto.getCodigoBarras() != null){
                    if (producto.getCodigoBarras().getId() == 0){
                        codigoBarrasDAO.insertar(producto.getCodigoBarras(), conn);
                    }else{
                        producto.getCodigoBarras().setFechaAsignacion(LocalDate.now());
                        codigoBarrasDAO.actualizar(producto.getCodigoBarras(), conn);
                    }
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
                Producto productoBase = productoDAO.getById(producto.getId(),conn);
                
                if (producto.getCodigoBarras() != null && 
                !producto.getCodigoBarras().equals(productoBase.getCodigoBarras())){
                    CodigoBarras cb = codigoBarrasDAO.getById(producto.getCodigoBarras().getId(),conn);
                    cb.setFechaAsignacion(LocalDate.now());
                    codigoBarrasDAO.actualizar(cb, conn);                    
                }
                
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
                // Además baja lógica del código de barras asociado
                int idCodigoBarras = productoDAO.obtenerIdCodigoBarras(id, conn); 
                if (idCodigoBarras > 0) {
                    codigoBarrasDAO.eliminar(idCodigoBarras, conn);
                 }
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
                // Además recuperacion del código de barras asociado
                int idCodigoBarras = productoDAO.obtenerIdCodigoBarras(id, conn); 
                if (idCodigoBarras > 0) {
                    codigoBarrasDAO.recuperar(idCodigoBarras, conn);
                 }
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
    
    public List<String> productosSinCodigo() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return productoDAO.productosSinCodigo(conn);
        }
    }
    
    public List<String> productosDuplicados() throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return productoDAO.productosDuplicados(conn);
        }
    }


}
