CREATE DATABASE IF NOT EXISTS FABRICA;

USE FABRICA;

CREATE TABLE Categoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);

CREATE TABLE CodigoBarras (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(10) NOT NULL,
    valor VARCHAR(20) NOT NULL UNIQUE,
    fechaAsignacion DATE,
    observaciones VARCHAR(255),
    eliminado BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE Producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    marca VARCHAR(80),
    precio DECIMAL(10,3) NOT NULL,
    peso DECIMAL(10,3),
    eliminado BOOLEAN NOT NULL DEFAULT FALSE,
    idCodigoBarras BIGINT UNIQUE,
    idCategoria BIGINT NOT NULL,
    CONSTRAINT fk_producto_codigobarras
        FOREIGN KEY (idCodigoBarras) REFERENCES CodigoBarras(id),
    CONSTRAINT fk_producto_categoria
        FOREIGN KEY (idCategoria) REFERENCES Categoria(id)
);

CREATE INDEX idx_producto_categoria_eliminado
ON Producto(idCategoria, eliminado);

CREATE INDEX idx_producto_eliminado
ON Producto(eliminado);


CREATE USER IF NOT EXISTS 'admin_user'@'localhost' IDENTIFIED BY 'clave_admin';
GRANT ALL PRIVILEGES ON fabrica.* TO 'admin_user'@'localhost';

CREATE USER IF NOT EXISTS 'lectura_user'@'localhost' IDENTIFIED BY 'clave_lectura';
GRANT SELECT ON fabrica.* TO 'lectura_user'@'localhost';

CREATE USER IF NOT EXISTS 'rw_user'@'localhost' IDENTIFIED BY 'clave_rw';
GRANT SELECT, INSERT, UPDATE ON fabrica.* TO 'rw_user'@'localhost';

-- Aplicar los cambios
FLUSH PRIVILEGES;


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


INSERT INTO Categoria (nombre, descripcion)
VALUES 
('Bebidas', 'Bebidas refrescantes'),
('Alimentos', 'Comida y snacks'),
('Electronica', 'Dispositivos y gadgets'),
('Ropa', 'Prendas de vestir'),
('Hogar', 'Artículos para el hogar'),
('Juguetes', 'Juegos y juguetes'),
('Limpieza', 'Productos de limpieza'),
('Deporte', 'Artículos deportivos'),
('Papeleria', 'Material de oficina'),
('Otros', 'Categoría miscelánea');
