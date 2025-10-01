SELECT p.id, p.nombre
FROM Producto p
LEFT JOIN CodigoBarras cb ON cb.id = p.idCodigoBarras
WHERE cb.id IS NULL;


insert into producto (nombre,marca,precio,peso,eliminado,idCodigoBarras,idCategoria) values 
 ('test', 'marca test', 123, 0, true, 1234, 999);
