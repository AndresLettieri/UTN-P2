package Service;

import Dao.GenericDAO;
import Entities.Producto;

import java.util.List;

public class ProductoServiceImpl implements GenericService<Producto> {
    
    private final GenericDAO<Producto> productoDAO;
    
    public ProductoServiceImpl(GenericDAO<Producto> productoDAO){
        this.productoDAO = productoDAO;
    }
    
    @Override
    public void insertar(Producto producto) throws Exception {
        
    }
    
    @Override
    public void actualizar(Producto producto) throws Exception {

    }
    
    @Override
    public void eliminar(long id) throws Exception{

    }
    
    @Override
    public Producto getById(long id) throws Exception{
        return null;
    }
    
    @Override
    public List<Producto> getAll() throws Exception{
        return null;
    }
    
    @Override
    public void recuperar(long id) throws Exception{
    }
    
    public List<Producto> buscarPorNombre(String filtro) throws Exception{
        return null;
    }
    
    public Producto buscarPorIdCodigoBarras(String filtro) throws Exception {
        return null;
    }
    
    public Producto buscarPorValorCodigoBarras(String filtro) throws Exception {
        return null;
    }
    
    
}
