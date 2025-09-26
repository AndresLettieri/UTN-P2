package Service;

import Dao.GenericDAO;
import Entities.CodigoBarras;
import java.util.List;

public class CodigoBarrasServiceImpl implements GenericService<CodigoBarras> {
    
 private final GenericDAO<CodigoBarras> codigoBarrasDAO;
    
    public CodigoBarrasServiceImpl(GenericDAO<CodigoBarras> codigoBarrasDAO){
        this.codigoBarrasDAO = codigoBarrasDAO;
    }
    
    @Override
    public void insertar(CodigoBarras codigoBarras) throws Exception {
        
    }
    
    @Override
    public void actualizar(CodigoBarras codigoBarras) throws Exception {

    }
    
    @Override
    public void eliminar(long id) throws Exception{

    }
    
    @Override
    public CodigoBarras getById(long id) throws Exception{
        return null;
    }
    
    @Override
    public List<CodigoBarras> getAll() throws Exception{
        return null;
    }
    
    @Override
    public void recuperar(long id) throws Exception{
    }
    
    public List<CodigoBarras> buscarPorValor(String filtro) throws Exception{
        return null;
    }
    
        
}
