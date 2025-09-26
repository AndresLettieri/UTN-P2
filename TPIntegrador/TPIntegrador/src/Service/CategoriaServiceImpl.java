package Service;

import Dao.CategoriaDAO;
import Entities.Categoria;
import java.util.List;

public class CategoriaServiceImpl {
    private final CategoriaDAO categoriaDAO;
     
      
    public CategoriaServiceImpl(CategoriaDAO categoriaDAO){
        this.categoriaDAO = categoriaDAO;
    }
    
    public void insertar(Categoria categoria) throws Exception {
        
    }
    
    public void actualizar(Categoria categoria) throws Exception {

    }
    
    public Categoria getById(int id) throws Exception{
        return null;
    }
    
    public List<Categoria> getAll() throws Exception{
        return null;
    }
    

    public List<Categoria> buscarPorNombre(String filtro) throws Exception{
        return null;
    }
}
