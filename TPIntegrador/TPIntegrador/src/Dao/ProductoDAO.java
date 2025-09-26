package Dao;

import Entities.Producto;
import java.sql.*;
import java.util.List;

public class ProductoDAO implements GenericDAO<Producto> {
    
    @Override
    public void insertar(Producto producto) throws Exception{
       
    }
    
    @Override
    public void actualizar(Producto producto) throws Exception{
       
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
    
    public List<Producto> buscarPorNombre(String filtro) throws SQLException {
        return null;
    }
    
    public Producto buscarPorIdCodigoBarras(String filtro) throws SQLException {
        return null;
    }
    
    public Producto buscarPorValorCodigoBarras(String filtro) throws SQLException {
        return null;
    }
    
    @Override
    public void recuperar(long id) throws SQLException{

    }
    
}

