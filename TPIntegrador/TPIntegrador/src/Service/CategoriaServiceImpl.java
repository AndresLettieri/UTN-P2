package Service;

import Config.DatabaseConnection;
import Dao.CategoriaDAO;
import Dao.ProductoDAO;
import Entities.Categoria;

import java.sql.Connection;
import java.util.List;

public class CategoriaServiceImpl implements GenericService<Categoria> {

    private final CategoriaDAO categoriaDAO;
    
    public CategoriaServiceImpl(CategoriaDAO categoriaDAO){
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public void insertar(Categoria categoria) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                validarCategoria(categoria);
                categoriaDAO.insertar(categoria, conn);
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
    public void actualizar(Categoria categoria) throws Exception {
        if (categoria.getId() <= 0) {
            throw new IllegalArgumentException("El ID de la categoría debe ser válido.");
        }
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try {
                validarCategoria(categoria);
                categoriaDAO.actualizar(categoria, conn);
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
                // Validar si existen productos asociados
                if (ProductoDAO.existenProductosPorCategoria(id, conn)) {
                    throw new IllegalStateException(
                        "No se puede eliminar la categoría porque tiene productos asociados.");
                }

                categoriaDAO.eliminar(id, conn);
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
                categoriaDAO.recuperar(id, conn);
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
    public Categoria getById(long id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        
        return categoriaDAO.getById(id);
    }

    @Override
    public List<Categoria> getAll() throws Exception {
        return categoriaDAO.getAll();
    }

    public List<Categoria> buscarPorNombre(String filtro) throws Exception {
        if (filtro == null || filtro.isBlank()) {
            throw new IllegalArgumentException("El filtro de búsqueda no puede estar vacío.");
        }
        
        return categoriaDAO.getAll()
                .stream()
                .filter(c -> c.getNombre().toLowerCase().contains(filtro.toLowerCase()))
                .toList();
    }

    // Validaciones comunes para insertar/actualizar
    private void validarCategoria(Categoria categoria) {
        if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre de la categoría es obligatorio.");
        }
    }
}
