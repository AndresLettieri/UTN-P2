# Sistema de Gestión de Productos y codigos de barras

## Trabajo Práctico Integrador - Programación 2

### Descripción del Proyecto

Este Trabajo Práctico Integrador tiene como objetivo demostrar la aplicación práctica de los conceptos fundamentales de Programación Orientada a Objetos y Persistencia de Datos aprendidos durante el cursado de Programación 2. El proyecto consiste en desarrollar un sistema de gestión de productos y codigos de barras que permita realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre estas entidades, implementando una arquitectura robusta y profesional.

### Objetivos Académicos

El desarrollo de este sistema permite aplicar y consolidar los siguientes conceptos clave de la materia:

**1. Arquitectura en Capas (Layered Architecture)**
- Implementación de separación de responsabilidades en 4 capas diferenciadas
- Capa de Presentación (Main/UI): Interacción con el usuario mediante consola
- Capa de Lógica de Negocio (Service): Validaciones y reglas de negocio
- Capa de Acceso a Datos (DAO): Operaciones de persistencia
- Capa de Modelo (Entities): Representación de entidades del dominio

**2. Programación Orientada a Objetos**
- Uso de herencia mediante clase abstracta Base
- Implementación de interfaces genéricas (GenericDAO, GenericService)
- Encapsulamiento con atributos privados y métodos de acceso
- Sobrescritura de métodos

**3. Persistencia de Datos con JDBC**
- Conexión a base de datos MySQL mediante JDBC
- Implementación del patrón DAO (Data Access Object)
- Uso de PreparedStatements para prevenir SQL Injection
- Gestión de transacciones con commit y rollback
- Consultas con LEFT JOIN para relaciones entre entidades mediante vistas genradas en la base de datos

**4. Manejo de Recursos y Excepciones**
- Uso del patrón try-with-resources para gestión automática de recursos JDBC
- Manejo apropiado de excepciones con propagación controlada
- Validación multi-nivel: base de datos y aplicación

**5. Patrones de Diseño**
- Service Layer Pattern (separación lógica de negocio)
- DAO Pattern (abstracción del acceso a datos)
- Soft Delete Pattern (eliminación lógica de registros)

**6. Validación de Integridad de Datos**
- Validación de unicidad (valor único por codigo de barras)
- Validación de campos obligatorios en múltiples niveles
- Validación de integridad referencial (Foreign Keys)
- Implementación de eliminación segura para prevenir referencias huérfanas

### Funcionalidades Implementadas

El sistema permite gestionar dos entidades principales y una secundaria con las siguientes operaciones:

## Características Principales

- **Gestión de productos**: Crear, listar, actualizar y eliminar productos de forma independiente o asociados a un producto
- **Gestión de codigos de barras**: Crear, listar, actualizar y eliminar codigos de barras de forma independiente o asociados a un producto con validacion de valor único
- **Gestión de categorias**: Crear, listar, actualizar y eliminar categorias de productos
- **Reporteria general**: Acceso a reportes que aporten informacion de valor para el usuario
- **Soft Delete**: Eliminación lógica que preserva la integridad de datos en productos y codigos de  barras
- **Seguridad**: Protección contra SQL injection mediante PreparedStatements
- **Validación Multi-capa**: Validaciones en capa de servicio y base de datos
- **Transacciones**: Soporte para operaciones atómicas con rollback automático

## Requisitos del Sistema

| Componente | Versión Requerida |
|------------|-------------------|
| Java JDK | 17 o superior |
| MySQL | 8.0 o superior |
| Gradle | 8.12 (incluido wrapper) |
| Sistema Operativo | Windows, Linux o macOS |

## Instalación

### 1. Configurar Base de Datos

Ejecutar los scripts del archivo ubicado en Scripts/00 Iniciador sistema SQL en MySQL:


### 2. Compilar el Proyecto

```bash
# Linux/macOS
./gradlew clean build

# Windows
gradlew.bat clean build
```

### 3. Verificar configuracion de conexión 

El sistema conecta por defecto conecta a:
- **Host**: localhost:3306
- **Base de datos**: fabrica
- **Usuario**: root
- **Contraseña**: 12345678


## Ejecución

### Opción 1: Desde IDE
1. Abrir proyecto en apache NetBeans
2. Ejecutar clase `Main.Main`

### Opción 2: Línea de comandos

**Windows:**
```bash
# Localizar JAR de MySQL
dir /s /b %USERPROFILE%\.gradle\caches\*mysql-connector-j-9.4.0.jar

# Ejecutar (reemplazar <ruta-mysql-jar>)
java -cp "build\classes\java\main;<ruta-mysql-jar>" Main.Main
```

**Linux/macOS:**
```bash
# Localizar JAR de MySQL
find ~/.gradle/caches -name "mysql-connector-j-9.4.0.jar"

# Ejecutar (reemplazar <ruta-mysql-jar>)
java -cp "build/classes/java/main:<ruta-mysql-jar>" Main.Main
```

### Verificar Conexión

```bash
# Usar TestConexion para verificar conexión a BD
java -cp "build/classes/java/main:<ruta-mysql-jar>" Main.TestConexion
```

Salida esperada:
```
¡Conexión exitosa a la base de datos!
```

## Uso del Sistema

### Menú Principal

```
=== MENU PRINCIPAL ===
1) Productos
2) Códigos de barras
3) Categorías
4) Reporteria
0) Salir
```
### Menú productos
```
--- PRODUCTOS ---
1) Crear producto
2) Listar productos
3) Actualizar producto
4) Eliminar producto
5) Recuperar producto
6) Buscar por nombre
7) Buscar por código de barras
8) Buscar por ID
0) Volver
```

### Menú Codigo de barras

```
--- CODIGOS DE BARRAS ---
1) Crear código de barras
2) Listar códigos de barras
3) Actualizar código de barras
4) Eliminar código de barras
5) Recuperar código de barras
6) Buscar código de barras por ID
7) Buscar código de barras por valor
0) Volver
```

### Menú categorias
```
--- CATEGORIAS ---
1) Crear categoría
2) Listar categorías
3) Acualizar categoría
4) Eliminar categoría
0) Volver
```
### Menú Reporteria
```
--- REPORTERIA ---
1) Productos sin codigo de barras asignado
2) Nombres de producto duplicados
3) Codigos de barras sin usar
0) Volver
```

## Arquitectura

### Estructura en Capas

```
┌─────────────────────────────────────┐
│     Main / UI Layer                 │
│  (Interacción con usuario)          │
│  AppMenu 							  │
└───────────┬─────────────────────────┘
            │
┌───────────▼─────────────────────────┐
│     Service Layer                   │
│  (Lógica de negocio y validación)   │
│  CategoriaServiceImpl               │
│  CodigoBarrasServiceImpl			  │
│  ProductoServiceImpl                │
│  AuthServiceImpl                    │
└───────────┬─────────────────────────┘
            │
┌───────────▼─────────────────────────┐
│     DAO Layer                       │
│  (Acceso a datos)                   │
│  CategoriaDAO, CodigoBarrasDAO,     │
│  ProductoDAO                        │
└───────────┬─────────────────────────┘
            │
┌───────────▼─────────────────────────┐
│     Models Layer                    │
│  (Entidades de dominio)             │
│  Categoria, Codigo de barras, 	  │
│   producto, Base                    │
└─────────────────────────────────────┘
```

### Componentes Principales

**Config/**
- `DatabaseConnection.java`: Gestión de conexiones JDBC con validación en inicialización estática
- `TransactionManager.java`: Manejo de transacciones

**Entities/**
- `Base.java`: Clase abstracta con campos id y eliminado
- `Categoria.java`: Entidad Categoria (nombre, descripcion)
- `Producto.java`: Entidad Producto (nombre, marca, categoria, precio, peso, codigo de barra)
- `CodigoBarras.java`: Entidad Codigo de barras (Tipo de codigo de barras, valor, fecha de asignacion, observacion)
- `TipoCodigoBarras.java`: Enum para tipo de codigo de barras (EAN13, EAN8, UPC)

**Dao/**
- `GenericDAO<T>`: Interface genérica con operaciones CRUD
- `CategoriaDAO`: Implementación para categorias
- `CodigoBarrasDAO`: Implementación para codigos de barras
- `ProductoDAO`: Implementación para productos

**Service/**
- `GenericService<T>`: Interface genérica para servicios
- `CategoriaServiceImpl`: Validaciones de categorias y coordinación con productos
- `CodigoBarrasServiceImpl`: Validaciones de codigos de barras y coordinacion con productos
- `ProductoServiceImpl`: Validaciones de productos y coordinacion con codigo de barras
- `AuthServiceImpl`: Validacion de inicio de sesion con base de datos


**Main/**
- `Main.java`: Punto de entrada
- `AppMenu.java`: Orquestador del ciclo de menú
- `TestConexion.java`: Utilidad para verificar conexión a BD

## Modelo de Datos

```
┌────────────────────┐     	 ┌──────────────────┐		    ┌──────────────────┐ 
│     producto       │       │   Categoria      │   		│   codigoBarras   │
├────────────────────┤       ├──────────────────┤   		├──────────────────┤
│ id (PK)            │       │ id (PK)          │   		│ id (PK)          │
│ nombre             │       │ nombre           │		    │ tipo             │
│ marca              │       │ descripcion      │	        │ valor (UNIQUE)   │
│ precio             │──────┐└──────────────────┘   		│ fechaAsignacion  │
│ peso               │      │    							│ observaciones    │
│ elimnado           │	    └──▶ Relación N..1				│ eliminado        │
│ idCodigoBarras (FK)│          			   				└──────────────────┘
│ idCatgegoria (FK)  │    					   ┌─────▶ Relación 0..1	      			   
└────────────────────┘─────────────────────────┘   
							 
                             

**Reglas:**
- Un procuto puede tener 0 o 1 codigo de barras
- Un procuto debe tener 1 Categoria
- valor del codigo de barras es único (constraint en base de datos)
- Eliminación lógica para producto y codigo de barras: campo `eliminado = TRUE`
- Foreign key `idCodigoBarras` puede ser NULL

## Patrones y Buenas Prácticas

### Seguridad
- **100% PreparedStatements**: Prevención de SQL injection
- **Validación multi-capa**: Service layer valida antes de persistir
- **valor único**: Constraint en BD

### Gestión de Recursos
- **Try-with-resources**: Todas las conexiones, statements y resultsets

### Validaciones
- **Input trimming**: Todos los inputs usan `.trim()` inmediatamente
- **Campos obligatorios**: Validación de null y empty en service layer
- **IDs positivos**: Validación `id > 0` en todas las operaciones

### Soft Delete
- DELETE ejecuta: `UPDATE tabla SET eliminado = TRUE WHERE id = ?`
- SELECT filtra: `WHERE eliminado = FALSE`

## Reglas de Negocio Principales

1. **Valor único**: No se permiten codigos de barras con valor duplicado
2. **Campos obligatorios**: Tipo y valor son requeridos para codigo de barras y nombre, precio y categoria son requeridos para el producto.
3. **Validación antes de persistir**: Service layer valida antes de llamar a DAO
5. **Preservación de valores**: En actualización, campos vacíos mantienen valor original
6. **Búsqueda flexible**: LIKE con % permite coincidencias parciales
7. **Transacciones**: Operaciones complejas soportan rollback

## Solución de Problemas

### Error: "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
**Causa**: JAR de MySQL no está en classpath

**Solución**: Incluir mysql-connector-j-9.4.0.jar en el comando java -cp

### Error: "Communications link failure"
**Causa**: MySQL no está ejecutándose

**Solución**:
```bash
# Linux/macOS
sudo systemctl start mysql
# O
brew services start mysql

# Windows
net start MySQL80
```

### Error: "Access denied for user 'root'@'localhost'"
**Causa**: Credenciales incorrectas

**Solución**: Verificar usuario/contraseña en DatabaseConnection.java o usar -Ddb.user y -Ddb.password

### Error: "Unknown database 'fabrica'"
**Causa**: Base de datos no creada

**Solución**: Ejecutar script de creación de base de datos (ver sección Instalación)

### Error: "Table 'tabla' doesn't exist"
**Causa**: Tablas no creadas

**Solución**: Ejecutar script de creación de tablas (ver sección Instalación)

## Limitaciones Conocidas

1. **Interfaz solo consola**: No hay GUI gráfica
2. **Sin paginación**: Listar todos puede ser lento con muchos registros
3. **Baja fisica para categoria**: las categorias solo admiten bajas fisicas. Hay validación bloqueante 
	de asociacion a productos previo al intento de eliminar.

## Tecnologías Utilizadas

- **Lenguaje**: JDK 21
- **Base de Datos**: MySQL 8.x
- **JDBC Driver**: mysql-connector-j 9.4.0

## Estructura de Directorios

```
TPIntegrador/
├── src/main/java/
│   ├── Config/          # Configuración de BD y transacciones
│   ├── Dao/             # Capa de acceso a datos
│   ├── Main/            # UI y punto de entrada
│   ├── Entities/          # Entidades de dominio
│   └── Service/         # Lógica de negocio
├── README.md            # Este archivo

```



### Conceptos de Programación 2 Demostrados

| Concepto | Implementación en el Proyecto |
|----------|-------------------------------|
| **Herencia** | Clase abstracta `Base` heredada por `Producto`, `CodigoBarras` y `Categoria` |
| **Polimorfismo** | Interfaces `GenericDAO<T>` y `GenericService<T>` |
| **Encapsulamiento** | Atributos privados con getters/setters en todas las entidades |
| **Abstracción** | Interfaces que definen contratos sin implementación |
| **JDBC** | Conexión, PreparedStatements, ResultSets, transacciones |
| **DAO Pattern** | `ProductoDAO`, `CodigoBarrasDAO` y `CategoriaDAO` abstraen el acceso a datos |
| **Service Layer** | Lógica de negocio separada en `ProductoServiceImpl`, `CodigoBarrasServiceImpl` y  `CategoriaServiceImpl`. `AuthService` para validaciones de inicio de sesion |
| **Exception Handling** | Try-catch en todas las capas, propagación controlada |
| **Resource Management** | Try-with-resources para AutoCloseable (Connection, Statement, ResultSet) |

## Contexto Académico

**Materia**: Programación 2
**Tipo de Evaluación**: Trabajo Práctico Integrador (TPI)
**Modalidad**: Desarrollo de sistema CRUD con persistencia en base de datos
**Objetivo**: Aplicar conceptos de POO, JDBC, arquitectura en capas y patrones de diseño

Este proyecto representa la integración de todos los conceptos vistos durante el cuatrimestre, demostrando capacidad para:
- Diseñar sistemas con arquitectura profesional
- Implementar persistencia de datos con JDBC
- Aplicar patrones de diseño apropiados
- Manejar recursos y excepciones correctamente
- Validar integridad de datos en múltiples niveles
- Documentar código de forma profesional

---

**Versión**: 1.0
**Java**: 21+
**MySQL**: 8.x
**Proyecto Educativo** - Trabajo Práctico Integrador de Programación 2