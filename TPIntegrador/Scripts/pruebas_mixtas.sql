SELECT COUNT(*) FROM producto WHERE eliminado = FALSE;
UPDATE producto SET precio = precio + 0.5 WHERE eliminado = FALSE AND id % 50 = 0;
SELECT tipo, valor FROM codigoBarras WHERE eliminado = FALSE LIMIT 100;