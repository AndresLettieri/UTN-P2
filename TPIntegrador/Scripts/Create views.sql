
create view vw_productoCodigoBarras as

select p.nombre, precio, cb.valor as codigoBarras
from producto p
inner join codigoBarras cb on cb.id = p.idCodigoBarras
where p.eliminado = false;


create view vw_ProductoSinCodigoBarras as
select p.id, p.nombre, p.marca, c.nombre as categoria
from producto p
inner join categoria c on p.idCategoria = c.id
where p.eliminado = false
and p.idCodigoBarras IS NULL;

create view vw_CodigoBarrasDisponibles as
select cb.* 
from codigoBarras cb
left join producto p on p.idCodigoBarras = cb.id
where cb.eliminado = false
and p.idCodigoBarras IS NULL;


create view vw_NombreProductoDuplicado as
select nombre, count(*) as cantidad
from producto 
where eliminado = false
group by nombre
having count(*) > 1;

Create view vw_productoBase as
select p.nombre, cb.Valor
FROM Producto p
inner join codigoBarras cb on cb.id = p.idCodigoBarras
where p.eliminado = false;

CREATE VIEW vw_codigoBarrasBase AS
SELECT 
      tipo,
      valor,
     fechaAsignacion
FROM codigoBarras 
WHERE eliminado = FALSE;

