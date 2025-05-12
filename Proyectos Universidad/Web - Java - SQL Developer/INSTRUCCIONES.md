
# INSTRUCCIONES.md

## 📌 Nombre del Proyecto
> **Sistema Académico** – CRUD con Spring Boot, Oracle PL/SQL y Thymeleaf

---

## 🧰 Requisitos Previos

Antes de ejecutar este proyecto, asegúrate de tener lo siguiente instalado en tu sistema:

### Base de Datos
- **Oracle SQL Developer**
- **Base de datos Oracle configurada** (se recomienda tener un usuario con permisos suficientes para crear tablas y paquetes)

### Backend Java
- **Java 17 o superior**
- **Spring Boot**
- **Maven**
- **Lombok v1.18.30**
- **ojdbc11 v23.3.0.23.0**

### Frontend
- **Thymeleaf**
- **Bootstrap v5.3.3**

---

## 📁 Estructura del Proyecto

```
SistemaAcademico/
│
├── SQL/
│   ├── Script_CentroAcademico.sql         # Script para crear tablas e insertar datos
│   └── Script_CA_Paquetes.sql # Script para crear paquetes y procedimientos
│
├── src/
│   └── main/
│       ├── java/
│       │   └── ... (MVC completo: domain, dao, service, controller)
│       └── resources/
│           ├── templates/            # Vistas Thymeleaf (HTML)
│           └── application.properties
│
└── pom.xml                           # Manejo de dependencias (Maven)
```

---

## ⚙️ Configuración de Base de Datos

1. Abre **Oracle SQL Developer**.
2. Conéctate a tu base de datos.
3. Ejecuta el script `Script_CentroAcademico.sql` para crear tablas e insertar datos iniciales.
4. Luego ejecuta `Script_CA_Paquetes.sql` para crear los **paquetes PL/SQL**, uno por entidad.

> **Nota:** Los paquetes contienen procedimientos con cursores que implementan operaciones CRUD.

---

## 🧪 Cómo ejecutar el proyecto

1. Clona este repositorio o descarga el proyecto.
2. Abre el proyecto en tu IDE favorito (recomendado: **IntelliJ IDEA** o **Eclipse**).
3. Asegúrate de tener instalado **Lombok v1.18.30**.
4. En el archivo `application.properties`, configura tu conexión a la base de datos Oracle:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:orcl
spring.datasource.username=centro_Academico
spring.datasource.password=toad
spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
```

5. Ejecuta el proyecto con Spring Boot (`Run Application` o `mvn spring-boot:run`).
6. Accede a la aplicación desde el navegador en: [http://localhost:80](http://localhost:80)

---

## 🧱 Arquitectura del Código

El proyecto sigue el patrón **MVC** usando Spring Boot:

- **Domain**: Definición de entidades y los paquetes/procedimientos que se utilizarán.
- **DAO**: Interacción directa con la base de datos mediante `JdbcTemplate`.
- **Service / ServiceImpl**: Lógica de negocio.
- **Controller**: Controladores para manejar las rutas HTTP.
- **View**: Vistas Thymeleaf con Bootstrap.

---

## ✅ Tecnologías Usadas

- **Spring Boot**
- **Thymeleaf**
- **Bootstrap 5.3.3**
- **Oracle PL/SQL (con cursores)**
- **JDBC (ojdbc11 v23.3.0.23.0)**
- **Lombok v1.18.30**
