package Dao;

import Entities.CodigoBarras;
import java.sql.SQLException;
import java.util.List;

public class CodigoBarrasDAO implements GenericDAO<CodigoBarras> {
@Override
    public void insertar(CodigoBarras codigoBarras) throws Exception{
       
    }
    
    @Override
    public void actualizar(CodigoBarras codigoBarras) throws Exception{
       
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
    
    public List<CodigoBarras> buscarPorValor(String filtro) throws SQLException {
        return null;
    }
    
    @Override
    public void recuperar(long id) throws SQLException{

    }    
}
