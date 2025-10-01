INSERT INTO Producto (eliminado, nombre, marca, precio, peso, idCodigoBarras, idCategoria)
SELECT 
    0,
    CONCAT('Producto ', cb.id),
    CONCAT('Marca ', FLOOR(RAND()*50)),
    ROUND(RAND()*1000, 3),
    ROUND(RAND()*10, 3),
    cb.id,                             
    FLOOR(RAND()*10)+1
FROM CodigoBarras cb
LIMIT 100000;   
