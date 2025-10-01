CREATE USER 'admin_user'@'localhost' IDENTIFIED BY 'clave_admin';
GRANT ALL PRIVILEGES ON fabrica.* TO 'admin_user'@'localhost';

CREATE USER 'lectura_user'@'localhost' IDENTIFIED BY 'clave_lectura';
GRANT SELECT ON fabrica.* TO 'lectura_user'@'localhost';

CREATE USER 'rw_user'@'localhost' IDENTIFIED BY 'clave_rw';
GRANT SELECT, INSERT, UPDATE ON fabrica.* TO 'rw_user'@'localhost';

FLUSH PRIVILEGES;
