-- 1. Crear base de datos
DROP DATABASE IF EXISTS sc502_1c2025_grupo7;
CREATE DATABASE sc502_1c2025_grupo7;
USE sc502_1c2025_grupo7;

-- 2. Eliminar tablas en orden inverso a dependencias
DROP TABLE IF EXISTS Inscripciones;
DROP TABLE IF EXISTS Estados_Inscripcion;
DROP TABLE IF EXISTS Pagos;
DROP TABLE IF EXISTS Metodos_Pago;
DROP TABLE IF EXISTS Historial_Salarios;
DROP TABLE IF EXISTS Salarios;
DROP TABLE IF EXISTS Horarios;
DROP TABLE IF EXISTS Estudiante_Curso;
DROP TABLE IF EXISTS Profesor_Curso;
DROP TABLE IF EXISTS Cursos;
DROP TABLE IF EXISTS Estudiantes;
DROP TABLE IF EXISTS Calendario;
DROP TABLE IF EXISTS Profesores;
DROP TABLE IF EXISTS Rol;
DROP TABLE IF EXISTS Turnos;
DROP TABLE IF EXISTS Direccion;

-- 3. Crear tablas independientes primero
CREATE TABLE Direccion (
  id_direccion INT PRIMARY KEY AUTO_INCREMENT,
  calle VARCHAR(150) NOT NULL,
  ciudad VARCHAR(100) NOT NULL,
  estado VARCHAR(100) NOT NULL,
  codigo_postal VARCHAR(10) NOT NULL
);

CREATE TABLE Rol (
  id_rol INT PRIMARY KEY AUTO_INCREMENT, 
  nombre VARCHAR(30) NOT NULL
);

CREATE TABLE Cursos (
  id_curso INT PRIMARY KEY AUTO_INCREMENT,
  nombre_curso VARCHAR(150) NOT NULL,
  descripcion TEXT
);

CREATE TABLE Metodos_Pago (
  id_metodo_pago INT PRIMARY KEY AUTO_INCREMENT,
  nombre_metodo VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE Estados_Inscripcion (
  id_estado INT PRIMARY KEY AUTO_INCREMENT,
  estado VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE Turnos (
  id_turno INT PRIMARY KEY AUTO_INCREMENT,
  nombre_turno VARCHAR(50) UNIQUE NOT NULL,
  hora_inicio TIME NOT NULL,
  hora_fin TIME NOT NULL
);

-- 4. Tablas que dependen de las anteriores
CREATE TABLE Profesores (
  id_profesor INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  apellido VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  id_direccion INT,
  telefono VARCHAR(20),
  puesto VARCHAR(200),
  rol_id INT,
  FOREIGN KEY (rol_id) REFERENCES Rol(id_rol),
  FOREIGN KEY (id_direccion) REFERENCES Direccion(id_direccion)
);

CREATE TABLE Calendario (
  id_calendario INT PRIMARY KEY AUTO_INCREMENT,
  titulo VARCHAR(50) NOT NULL,
  descripcion VARCHAR(200) NOT NULL,
  fecha_inicial DATE NOT NULL,
  hora TIME NOT NULL,
  id_profesor INT,
  FOREIGN KEY (id_profesor) REFERENCES Profesores(id_profesor)
);

CREATE TABLE Estudiantes (
  id_estudiante INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  apellido VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  id_direccion INT,
  telefono VARCHAR(20),
  rol_id INT,
  FOREIGN KEY (rol_id) REFERENCES Rol(id_rol),
  FOREIGN KEY (id_direccion) REFERENCES Direccion(id_direccion)
);

CREATE TABLE Profesor_Curso (
  id_profesor INT NOT NULL,
  id_curso INT NOT NULL,
  PRIMARY KEY (id_profesor, id_curso),
  FOREIGN KEY (id_profesor) REFERENCES Profesores(id_profesor),
  FOREIGN KEY (id_curso) REFERENCES Cursos(id_curso)
);

CREATE TABLE Estudiante_Curso (
  id_estudiante INT NOT NULL,
  id_curso INT NOT NULL,
  fecha_inscripcion DATE NOT NULL,
  PRIMARY KEY (id_estudiante, id_curso),
  FOREIGN KEY (id_estudiante) REFERENCES Estudiantes(id_estudiante),
  FOREIGN KEY (id_curso) REFERENCES Cursos(id_curso)
);

CREATE TABLE Horarios (
  id_horario INT PRIMARY KEY AUTO_INCREMENT,
  id_curso INT NOT NULL,
  dia_semana ENUM('Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo') NOT NULL,
  hora_inicio TIME NOT NULL,
  hora_fin TIME NOT NULL,
  FOREIGN KEY (id_curso) REFERENCES Cursos(id_curso)
);

CREATE TABLE Salarios (
  id_salario INT PRIMARY KEY AUTO_INCREMENT,
  id_profesor INT UNIQUE NOT NULL,
  salario DECIMAL(10,2) NOT NULL,
  fecha_actualizacion DATE NOT NULL,
  FOREIGN KEY (id_profesor) REFERENCES Profesores(id_profesor)
);

CREATE TABLE Historial_Salarios (
  id_historial INT PRIMARY KEY AUTO_INCREMENT,
  id_profesor INT NOT NULL,
  salario DECIMAL(10,2) NOT NULL,
  fecha_inicio DATE NOT NULL,
  fecha_fin DATE,
  FOREIGN KEY (id_profesor) REFERENCES Profesores(id_profesor)
);

CREATE TABLE Pagos (
  id_pago INT PRIMARY KEY AUTO_INCREMENT,
  id_estudiante INT NOT NULL,
  monto DECIMAL(10,2) NOT NULL,
  fecha_pago DATE NOT NULL,
  id_metodo_pago INT NOT NULL,
  FOREIGN KEY (id_estudiante) REFERENCES Estudiantes(id_estudiante),
  FOREIGN KEY (id_metodo_pago) REFERENCES Metodos_Pago(id_metodo_pago)
);

CREATE TABLE Inscripciones (
  id_inscripcion INT PRIMARY KEY AUTO_INCREMENT,
  id_estudiante INT NOT NULL,
  id_curso INT NOT NULL,
  fecha_inscripcion DATE NOT NULL,
  id_estado INT NOT NULL DEFAULT 1,
  FOREIGN KEY (id_estudiante) REFERENCES Estudiantes(id_estudiante),
  FOREIGN KEY (id_curso) REFERENCES Cursos(id_curso),
  FOREIGN KEY (id_estado) REFERENCES Estados_Inscripcion(id_estado)
);

-- 5. Insertar datos

INSERT INTO Rol (nombre) VALUES ('Administrador'), ('Profesor'), ('Estudiante');

INSERT INTO Direccion (calle, ciudad, estado, codigo_postal) VALUES
('Av. Central 123', 'San José', 'San José', '10101'),
('Calle 45 Norte', 'Heredia', 'Heredia', '40101'),
('Boulevard 28', 'Alajuela', 'Alajuela', '20101'),
('Ruta 3', 'Cartago', 'Cartago', '30101'),
('Calle del Sol', 'Liberia', 'Guanacaste', '50101'),
('Calle Luna 21', 'Puntarenas', 'Puntarenas', '60101'),
('Via Azul 7', 'San Ramón', 'Alajuela', '20201');

INSERT INTO Profesores (nombre, apellido, email, password, id_direccion, telefono, puesto, rol_id) VALUES
('Carlos', 'Fernández', 'cfernandez@gmail.com', '$2y$10$fa4iQQPHodfNgMU6DzJkCOl/f.d8aenF4JHZUQU6FTrdeY.TUT12u',
 1, '8765-4321', 'Profesor de Matemáticas', 2),
('María', 'Gómez', 'mgomez@gmail.com', '$2y$10$68jJ2551dXGPZUA9inK/3ufAgEnkqU9MX/ybPxaWXzFwDynkB.ozS',
 2, '6012-3456', 'Profesor de Física', 2),
('Pedro', 'Ramírez', 'pramirez@gmail.com', '$2y$10$F5Exn1Dc44LAv9oRIuiRi.MFND/uY67A2gkCIXWNpFYFJffEIg9mu'
 , 3, '7070-7070', 'Profesor de Química', 2),
('Lucía', 'Alvarado', 'lalvarado@gmail.com', '$2y$10$F5Exn1Dc44LAv9oRIuiRi.MFND/uY67A2gkCIXWNpFYFJffEIg9mu',
 4, '6262-6262', 'Profesora de Historia', 2),
('Andrés', 'Chaves', 'achaves@gmail.com', '$2y$10$F5Exn1Dc44LAv9oRIuiRi.MFND/uY67A2gkCIXWNpFYFJffEIg9mu', 5, '7171-7171', 'Profesor de Informática', 1);

INSERT INTO Estudiantes (nombre, apellido, email, password, id_direccion, telefono, rol_id) VALUES
('Juan', 'Pérez', 'juanp@gmail.com', 'est123', 1, '7111-2222', 3),
('Ana', 'Ramírez', 'ana.r@gmail.com', 'ana456', 2, '8333-9999', 3),
('Luis', 'Soto', 'lsoto@gmail.com', 'ls789', 3, '8888-1111', 3),
('Daniela', 'Vargas', 'dvargas@gmail.com', 'dv1234', 4, '8999-2222', 3),
('Esteban', 'Mora', 'emora@gmail.com', 'em5678', 5, '8222-3333', 3),
('Laura', 'Jiménez', 'ljimenez@gmail.com', 'lj987', 6, '8111-4444', 3),
('Carlos', 'Ureña', 'curena@gmail.com', 'cu321', 7, '8555-6666', 3);

INSERT INTO Cursos (nombre_curso, descripcion) VALUES
('Matemáticas Avanzadas', 'Álgebra y cálculo integral'),
('Física Moderna', 'Cuántica y relatividad'),
('Química Orgánica', 'Estudio de compuestos de carbono'),
('Historia de América', 'Desde la época precolombina hasta hoy'),
('Programación Java', 'Desarrollo de software con Java'),
('Bases de Datos', 'Modelado relacional y SQL'),
('Ciencias Ambientales', 'Impacto humano y sostenibilidad');

INSERT INTO Profesor_Curso (id_profesor, id_curso) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(5, 6),
(4, 7);

INSERT INTO Estudiante_Curso (id_estudiante, id_curso, fecha_inscripcion) VALUES
(1, 1, '2025-03-01'),
(2, 2, '2025-03-01'),
(3, 3, '2025-03-02'),
(4, 4, '2025-03-02'),
(5, 5, '2025-03-03'),
(6, 6, '2025-03-04'),
(7, 7, '2025-03-05'),
(1, 5, '2025-03-06'),
(2, 6, '2025-03-06'),
(3, 7, '2025-03-06');

INSERT INTO Horarios (id_curso, dia_semana, hora_inicio, hora_fin) VALUES
(1, 'Lunes', '08:00', '11:00'),
(2, 'Martes', '10:00', '13:00'),
(3, 'Miércoles', '14:00', '17:00'),
(4, 'Jueves', '09:00', '12:00'),
(5, 'Viernes', '13:00', '16:00'),
(6, 'Sábado', '08:00', '11:00'),
(7, 'Lunes', '14:00', '17:00');

INSERT INTO Salarios (id_profesor, salario, fecha_actualizacion) VALUES
(1, 1500.00, '2025-04-01'),
(2, 1600.00, '2025-04-01'),
(3, 1450.00, '2025-04-01'),
(4, 1520.00, '2025-04-01'),
(5, 1800.00, '2025-04-01');

INSERT INTO Historial_Salarios (id_profesor, salario, fecha_inicio, fecha_fin) VALUES
(1, 1450.00, '2025-01-01', '2025-03-31'),
(1, 1500.00, '2025-04-01', NULL),
(2, 1550.00, '2025-01-01', '2025-03-31'),
(2, 1600.00, '2025-04-01', NULL);

INSERT INTO Metodos_Pago (nombre_metodo) VALUES ('Tarjeta'), ('Efectivo'), ('Transferencia');

INSERT INTO Pagos (id_estudiante, monto, fecha_pago, id_metodo_pago) VALUES
(1, 15000.00, '2025-03-01', 1),
(2, 15000.00, '2025-03-01', 2),
(3, 15000.00, '2025-03-02', 3);

INSERT INTO Estados_Inscripcion (estado) VALUES ('Activa'), ('Pendiente'), ('Cancelada');

INSERT INTO Inscripciones (id_estudiante, id_curso, fecha_inscripcion, id_estado) VALUES
(1, 1, '2025-03-01', 1),
(2, 2, '2025-03-01', 1),
(3, 3, '2025-03-02', 2);
