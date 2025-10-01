INSERT INTO fabrica.categoria (nombre, descripcion) VALUES ('Indumentaria', 'Ropa, calzado, accesiorios de moda');
INSERT INTO fabrica.categoria (nombre, descripcion) VALUES ('Electronica', 'Dispositivos, insumos, repuestos');
INSERT INTO fabrica.categoria (nombre, descripcion) VALUES ('Indumentaria', '');
INSERT INTO fabrica.categoria (nombre, descripcion) VALUES ('Validacion del largo del campo superando los 80 caracteres Validacion del largo del campo superando los 80 caracteres', '');

INSERT INTO fabrica.codigobarras (tipo,valor,fechaAsignacion,observaciones,eliminado) VALUES ('EAN13','978020137962','2025-10-01','sin observaciones',FALSE);
INSERT INTO fabrica.codigobarras (tipo,valor,fechaAsignacion,observaciones,eliminado) VALUES ('UPC','72527273070','2025-10-01','',FALSE);
INSERT INTO fabrica.codigobarras (tipo,valor,fechaAsignacion,observaciones,eliminado) VALUES ('EAN13','978020137962','2025-10-01','sin observaciones',FALSE);
INSERT INTO fabrica.codigobarras (tipo,valor,fechaAsignacion,observaciones,eliminado) VALUES ('UPC','72527273070123123123123123123','2025-10-01','',FALSE);

INSERT INTO fabrica.producto (nombre, marca, precio, peso, eliminado, idCodigoBarras, idCategoria) VALUES ('Camiseta','Lotto',65000,null,FALSE,1,1);
INSERT INTO fabrica.producto (nombre, marca, precio, peso, eliminado, idCodigoBarras, idCategoria) VALUES ('Notebook Vostro 1234','Dell',630000,1.5,FALSE,2,2);
INSERT INTO fabrica.producto (nombre, marca, precio, peso, eliminado, idCodigoBarras, idCategoria) VALUES ('Pantalon','Adidas',30000,null,FALSE,1,1);
INSERT INTO fabrica.producto (nombre, marca, precio, peso, eliminado, idCodigoBarras, idCategoria) VALUES ('Camiseta','Lotto',65000,null,FALSE,8,1);

