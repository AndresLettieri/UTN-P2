package Dao;

import java.sql.Connection;
import java.util.List;

public interface GenericDAO<T> {
    
    void insertar (T entidad, Connection conn) throws Exception;
    
    void actualizar (T entidad, Connection conn) throws Exception;
    void eliminar (long id, Connection conn) throws Exception;
    T getById(long id) throws Exception;
    List<T> getAll() throws Exception;
    void recuperar(long id, Connection conn) throws Exception;
}
