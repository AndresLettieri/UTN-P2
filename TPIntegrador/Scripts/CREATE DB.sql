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
    fechaAsignacion DATE NOT NULL,
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


