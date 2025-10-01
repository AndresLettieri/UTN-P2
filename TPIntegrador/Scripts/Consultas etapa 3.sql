select p.nombre, precio, cb.valor codigoBarras
from producto p
inner join codigoBarras cb on cb.id = p.idCodigoBarras
where p.eliminado = false;


select p.nombre as nombreProducto, p.precio, cb.valor as codigoBarras, c.nombre as categoria 
from producto p
inner join codigoBarras cb on cb.id = p.idCodigoBarras
inner join categoria c on c.id = p.idCategoria
where p.eliminado = false;


select c.nombre as categoria, count(1) as cantidad
from producto p
inner join categoria c on c.id = p.idCategoria
where p.eliminado = false
group by c.nombre
having count(1) > 10000;

select nombre, count(*) as cantidad
from producto 
where eliminado = false
group by nombre
having count(*) > 1;


SELECT p.*, c.nombre as nombreCategoria 
FROM Producto p
inner join categoria c on c.id = p.idCategoria
WHERE p.eliminado = false 
and precio = (
    SELECT MAX(precio) 
    FROM Producto 
    WHERE idCategoria = p.idCategoria
);


--Mejora propuesta por IA
SELECT 
    c.nombre AS categoria, 
    COUNT(*) AS cantidad
FROM Producto p
INNER JOIN Categoria c ON c.id = p.idCategoria
WHERE p.eliminado = 0
GROUP BY c.id
HAVING COUNT(*) > 10000;
