-- ==========================
-----Script Creacion Esquema Base de Datos------
-- ==========================

alter session set "_ORACLE_SCRIPT"=true;  

--Crear usuario

CREATE USER centro_Academico identified by "toad";

-- Asignar el espacio de tablas y cuotas para el nuevo usuario
ALTER USER centro_Academico QUOTA UNLIMITED ON USERS;

-- Otorgar privilegios de DBA
GRANT DBA TO centro_Academico;

-- Otorgar privilegios para que pueda crear sesiones
GRANT CREATE SESSION TO centro_Academico;

-- Otorgar privilegios adicionales para que pueda administrar la base de datos
GRANT CREATE TABLE TO centro_Academico;
GRANT CREATE VIEW TO centro_Academico;
GRANT CREATE PROCEDURE TO centro_Academico;
GRANT CREATE TRIGGER TO centro_Academico;
GRANT CREATE SEQUENCE TO centro_Academico;

-- ==========================
-- CREACIÓN DE TABLAS PROYECTO BASE DE DATOS
-- ==========================

-- Tabla de Estudiantes
CREATE TABLE Estudiantes (
    id_estudiante INT PRIMARY KEY,
    cedula_estudiante VARCHAR(10),
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    direccion VARCHAR(255),
    telefono VARCHAR(20),
	codigo_estudiante VARCHAR(20),
    correo_electronico VARCHAR(100)
);

-- Tabla de Archivos relacionados a Estudiantes
CREATE TABLE Archivos (
    id_archivo INT PRIMARY KEY,
    id_estudiante INT,
    tipo_Documento VARCHAR(255),
    FOREIGN KEY (id_estudiante) REFERENCES Estudiantes(id_estudiante) ON DELETE CASCADE
);

-- Tabla de Profesores
CREATE TABLE Profesores (
    id_profesor INT PRIMARY KEY,
    cedula_profesor VARCHAR(10),
    nombre VARCHAR(255),
	codigo_profesor VARCHAR(20),
    apellido VARCHAR(255)
);

-- Tabla de Materias
CREATE TABLE Materias (
    id_materia INT PRIMARY KEY,
    nombre_materia VARCHAR(255),
	codigo_materia VARCHAR(20),
    descripcion VARCHAR(255)
);

-- Tabla de Aulas
CREATE TABLE Aulas (
    id_aula INT PRIMARY KEY,
    numero_Aula VARCHAR(50),
    capacidad INT
);

-- Tabla de Horarios de Clases
CREATE TABLE Horarios (
    id_horario INT PRIMARY KEY,
    id_profesor INT,
    id_materia INT,
    dia_Semana VARCHAR(10),
    horario_Inc TIMESTAMP,
    horario_Fin TIMESTAMP,
    FOREIGN KEY (id_profesor) REFERENCES Profesores(id_profesor) ON DELETE CASCADE,
    FOREIGN KEY (id_materia) REFERENCES Materias(id_materia) ON DELETE CASCADE
);

-- Tabla de Clases que agrupa horarios, profesores, materias y aulas
CREATE TABLE Clases (
    id_clase INT PRIMARY KEY,
    id_horario INT,
    id_profesor INT,
    id_materia INT,
    id_aula INT,
    FOREIGN KEY (id_horario) REFERENCES Horarios(id_horario) ON DELETE CASCADE,
    FOREIGN KEY (id_profesor) REFERENCES Profesores(id_profesor) ON DELETE CASCADE,
    FOREIGN KEY (id_materia) REFERENCES Materias(id_materia) ON DELETE CASCADE,
    FOREIGN KEY (id_aula) REFERENCES Aulas(id_aula) ON DELETE CASCADE
);

-- Tabla de Inscripciones de Estudiantes en Materias
CREATE TABLE Inscripciones (
    id_inscripcion INT,
    id_materia INT,
    id_estudiante INT,
    PRIMARY KEY (id_inscripcion),
    FOREIGN KEY (id_estudiante) REFERENCES Estudiantes(id_estudiante) ON DELETE CASCADE,
    FOREIGN KEY (id_materia) REFERENCES Materias(id_materia) ON DELETE CASCADE
);

-- Tabla de Notas asignadas a los Estudiantes
CREATE TABLE Notas (
    id_nota INT PRIMARY KEY,
    id_profesor INT,
    id_materia INT,
    id_estudiante INT,
    calificacion DECIMAL(5,2),
    FOREIGN KEY (id_profesor) REFERENCES Profesores(id_profesor) ON DELETE CASCADE,
    FOREIGN KEY (id_materia) REFERENCES Materias(id_materia) ON DELETE CASCADE,
    FOREIGN KEY (id_estudiante) REFERENCES Estudiantes(id_estudiante) ON DELETE CASCADE
);

-- Tabla de Congelamiento de Matrícula de un Estudiante
CREATE TABLE Congelamiento (
    id_congelamiento INT PRIMARY KEY,
    id_estudiante INT,
    fechaComplemento DATE,
    FOREIGN KEY (id_estudiante) REFERENCES Estudiantes(id_estudiante) ON DELETE CASCADE
);

-- Tabla de Usuarios del sistema
CREATE TABLE Usuarios (
    id_usuario INT PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    nombre VARCHAR(255),
    apellidos VARCHAR(255),
    correo VARCHAR(255),
    activo NUMBER(1,0)
);

-- Tabla de Roles asignados a los Usuarios
CREATE TABLE Rol (
    id_rol INT PRIMARY KEY,
    nombre_rol VARCHAR(255),
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE
);

-- ==========================
-- CREACIÓN DE SECUENCIAS Y TRIGGERS
-- ==========================

-- Secuencia y Trigger para Estudiantes
CREATE SEQUENCE seq_estudiantes START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_estudiantes
BEFORE INSERT ON Estudiantes
FOR EACH ROW
BEGIN
    :NEW.id_estudiante := seq_estudiantes.NEXTVAL;
END;
/

-- Secuencia y Trigger para Profesores
CREATE SEQUENCE seq_profesores START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_profesores
BEFORE INSERT ON Profesores
FOR EACH ROW
BEGIN
    :NEW.id_profesor := seq_profesores.NEXTVAL;
END;
/

-- Secuencia y Trigger para Materias
CREATE SEQUENCE seq_materias START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_materias
BEFORE INSERT ON Materias
FOR EACH ROW
BEGIN
    :NEW.id_materia := seq_materias.NEXTVAL;
END;
/

-- Secuencia y Trigger para Horarios
CREATE SEQUENCE seq_horarios START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_horarios
BEFORE INSERT ON Horarios
FOR EACH ROW
BEGIN
    :NEW.id_horario := seq_horarios.NEXTVAL;
END;
/

-- Secuencia y Trigger para Usuarios
CREATE SEQUENCE seq_usuario START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_usuario
BEFORE INSERT ON Usuarios
FOR EACH ROW
BEGIN
    :NEW.id_usuario := seq_usuario.NEXTVAL;
END;
/

-- Secuencia y Trigger para Roles
CREATE SEQUENCE seq_rol START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_rol
BEFORE INSERT ON Rol
FOR EACH ROW
BEGIN
    :NEW.id_rol := seq_rol.NEXTVAL;
END;
/

-- Secuencia y Trigger para Archivos
CREATE SEQUENCE seq_archivos START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_archivo
BEFORE INSERT ON Archivos
FOR EACH ROW
BEGIN
    :NEW.id_archivo := seq_archivos.NEXTVAL;
END;
/

-- Secuencia y Trigger para Congelamientos
CREATE SEQUENCE seq_congelamientos START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_congelamientos
BEFORE INSERT ON Congelamiento
FOR EACH ROW
BEGIN
    :NEW.id_congelamiento := seq_congelamientos.NEXTVAL;
END;
/

-- Secuencia y Trigger para Notas
CREATE SEQUENCE seq_notas START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_notas
BEFORE INSERT ON Notas
FOR EACH ROW
BEGIN
    :NEW.id_nota := seq_notas.NEXTVAL;
END;
/

-- Crear secuencia
CREATE SEQUENCE seq_aulas START WITH 1 INCREMENT BY 1;

-- Crear trigger
CREATE OR REPLACE TRIGGER trg_aulas
BEFORE INSERT ON Aulas
FOR EACH ROW
BEGIN
    :NEW.id_aula := seq_aulas.NEXTVAL;
END;
/


-- Crear secuencia
CREATE SEQUENCE seq_clases START WITH 1 INCREMENT BY 1;

-- Crear trigger
CREATE OR REPLACE TRIGGER trg_clases
BEFORE INSERT ON Clases
FOR EACH ROW
BEGIN
    :NEW.id_clase := seq_clases.NEXTVAL;
END;
/

-- Crear secuencia
CREATE SEQUENCE seq_inscripciones START WITH 1 INCREMENT BY 1;

-- Crear trigger
CREATE OR REPLACE TRIGGER trg_inscripciones
BEFORE INSERT ON Inscripciones
FOR EACH ROW
BEGIN
    :NEW.id_inscripcion := seq_inscripciones.NEXTVAL;
END;
/

-- ==========================
-- CREACIÓN DE PROCEDURES PROYECTO BASE DE DATOS
-- ==========================


-- Procedimiento para insertar un nuevo Estudiante
CREATE OR REPLACE PROCEDURE insertar_estudiante (
    p_cedula IN VARCHAR2,
    p_nombre IN VARCHAR2,
    p_apellido IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_telefono IN VARCHAR2,
    p_correo IN VARCHAR2
) AS
BEGIN
    INSERT INTO Estudiantes (cedula_estudiante, nombre, apellido, direccion, telefono, correo_electronico)
    VALUES (p_cedula, p_nombre, p_apellido, p_direccion, p_telefono, p_correo);
END insertar_estudiante;
/

-- Procedimiento para insertar un nuevo Profesor
CREATE OR REPLACE PROCEDURE insertar_profesor (
    p_cedula IN VARCHAR2,
    p_nombre IN VARCHAR2,
    p_apellido IN VARCHAR2
) AS
BEGIN
    INSERT INTO Profesores (cedula_profesor, nombre, apellido)
    VALUES (p_cedula, p_nombre, p_apellido);
END insertar_profesor;
/

-- Procedimiento para insertar una nueva Materia
CREATE OR REPLACE PROCEDURE insertar_materia (
    p_nombre_materia IN VARCHAR2,
    p_descripcion IN VARCHAR2
) AS
BEGIN
    INSERT INTO Materias (nombre_materia, descripcion)
    VALUES (p_nombre_materia, p_descripcion);
END insertar_materia;
/

-- Procedimiento para insertar una nueva Clase
CREATE OR REPLACE PROCEDURE insertar_clase (
    p_id_horario IN INT,
    p_id_profesor IN INT,
    p_id_materia IN INT,
    p_id_aula IN INT
) AS
BEGIN
    INSERT INTO Clases (id_horario, id_profesor, id_materia, id_aula)
    VALUES (p_id_horario, p_id_profesor, p_id_materia, p_id_aula);
END insertar_clase;
/

-- Procedimiento para insertar una nueva Inscripción
CREATE OR REPLACE PROCEDURE insertar_inscripcion (
    p_id_estudiante IN INT,
    p_id_materia IN INT
) AS
BEGIN
    INSERT INTO Inscripciones (id_estudiante, id_materia)
    VALUES (p_id_estudiante, p_id_materia);
END insertar_inscripcion;
/

-- Procedimiento para insertar una nueva Nota
CREATE OR REPLACE PROCEDURE insertar_nota (
    p_id_profesor IN INT,
    p_id_materia IN INT,
    p_id_estudiante IN INT,
    p_calificacion IN DECIMAL
) AS
BEGIN
    INSERT INTO Notas (id_profesor, id_materia, id_estudiante, calificacion)
    VALUES (p_id_profesor, p_id_materia, p_id_estudiante, p_calificacion);
END insertar_nota;
/

-- Procedimiento para insertar un nuevo Congelamiento
CREATE OR REPLACE PROCEDURE insertar_congelamiento (
    p_id_estudiante IN INT,
    p_fecha_complemento IN DATE
) AS
BEGIN
    INSERT INTO Congelamiento (id_estudiante, fechaComplemento)
    VALUES (p_id_estudiante, p_fecha_complemento);
END insertar_congelamiento;
/

-- Procedimiento para obtener la cantidad de inscripciones de un estudiante
CREATE OR REPLACE PROCEDURE cantidad_inscripciones (
    p_id_estudiante IN INT,
    p_cantidad OUT INT
) AS
BEGIN
    SELECT COUNT(*)
    INTO p_cantidad
    FROM Inscripciones
    WHERE id_estudiante = p_id_estudiante;
END cantidad_inscripciones;
/

-- Procedimiento para obtener las notas de un estudiante en una materia
CREATE OR REPLACE PROCEDURE obtener_notas_estudiante (
    p_id_estudiante IN INT,
    p_id_materia IN INT,
    p_calificacion OUT DECIMAL
) AS
BEGIN
    SELECT n.calificacion
    INTO p_calificacion
    FROM Notas n
    WHERE n.id_estudiante = p_id_estudiante AND n.id_materia = p_id_materia;
END obtener_notas_estudiante;
/

-- Procedimiento para eliminar una inscripción
CREATE OR REPLACE PROCEDURE eliminar_inscripcion (
    p_id_estudiante IN INT,
    p_id_materia IN INT
) AS
BEGIN
    DELETE FROM Inscripciones
    WHERE id_estudiante = p_id_estudiante AND id_materia = p_id_materia;
END eliminar_inscripcion;
/

-- Procedimiento para actualizar la dirección de un estudiante
CREATE OR REPLACE PROCEDURE actualizar_direccion_estudiante (
    p_id_estudiante IN INT,
    p_nueva_direccion IN VARCHAR2
) AS
BEGIN
    UPDATE Estudiantes
    SET direccion = p_nueva_direccion
    WHERE id_estudiante = p_id_estudiante;
END actualizar_direccion_estudiante;
/

-- Procedimiento para actualizar el correo de un estudiante
CREATE OR REPLACE PROCEDURE actualizar_correo_estudiante (
    p_id_estudiante IN INT,
    p_nuevo_correo IN VARCHAR2
) AS
BEGIN
    UPDATE Estudiantes
    SET correo_electronico = p_nuevo_correo
    WHERE id_estudiante = p_id_estudiante;
END actualizar_correo_estudiante;
/

-- Procedimiento para obtener todos los estudiantes de una materia
CREATE OR REPLACE PROCEDURE obtener_estudiantes_materia (
    p_id_materia IN INT
) AS
BEGIN
    FOR rec IN (SELECT e.nombre, e.apellido FROM Estudiantes e
                JOIN Inscripciones i ON e.id_estudiante = i.id_estudiante
                WHERE i.id_materia = p_id_materia)
    LOOP
        DBMS_OUTPUT.PUT_LINE('Estudiante: ' || rec.nombre || ' ' || rec.apellido);
    END LOOP;
END obtener_estudiantes_materia;
/

-- Procedimiento para obtener todos los profesores de una materia
CREATE OR REPLACE PROCEDURE obtener_profesores_materia (
    p_id_materia IN INT
) AS
BEGIN
    FOR rec IN (SELECT p.nombre, p.apellido FROM Profesores p
                JOIN Horarios h ON p.id_profesor = h.id_profesor
                WHERE h.id_materia = p_id_materia)
    LOOP
        DBMS_OUTPUT.PUT_LINE('Profesor: ' || rec.nombre || ' ' || rec.apellido);
    END LOOP;
END obtener_profesores_materia;
/

-- Procedimiento para verificar si un estudiante está inscrito en una materia

CREATE OR REPLACE PROCEDURE verificar_inscripcion (
    p_id_estudiante IN INT,
    p_id_materia IN INT,
    p_resultado OUT VARCHAR2
) IS
    v_estatus VARCHAR2(20);
BEGIN
    -- Verificar si el estudiante está inscrito en la materia
    SELECT CASE 
             WHEN EXISTS (
                 SELECT 1 
                 FROM Inscripciones i
                 WHERE i.id_estudiante = p_id_estudiante 
                   AND i.id_materia = p_id_materia
             ) THEN 'Inscrito'
             ELSE 'No Inscrito'
           END
    INTO v_estatus
    FROM dual;

    -- Retornar el estatus
    p_resultado := v_estatus;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_resultado := 'No se encontró la inscripción';
    WHEN OTHERS THEN
        p_resultado := 'Error en la consulta';
END verificar_inscripcion;
/


-- Procedimiento para cambiar la calificación de un estudiante
CREATE OR REPLACE PROCEDURE cambiar_calificacion (
    p_id_estudiante IN INT,
    p_id_materia IN INT,
    p_nueva_calificacion IN DECIMAL
) AS
BEGIN
    UPDATE Notas
    SET calificacion = p_nueva_calificacion
    WHERE id_estudiante = p_id_estudiante AND id_materia = p_id_materia;
END cambiar_calificacion;
/

-- Procedimiento para eliminar un congelamiento
CREATE OR REPLACE PROCEDURE eliminar_congelamiento (
    p_id_estudiante IN INT
) AS
BEGIN
    DELETE FROM Congelamiento
    WHERE id_estudiante = p_id_estudiante;
END eliminar_congelamiento;
/

-- Procedimiento para obtener los detalles de un aula
CREATE OR REPLACE PROCEDURE obtener_detalle_aula (
    p_id_aula IN INT
) AS
BEGIN
    FOR rec IN (SELECT * FROM Aulas WHERE id_aula = p_id_aula)
    LOOP
        DBMS_OUTPUT.PUT_LINE('Aula: ' || rec.numeroAula || ' - Capacidad: ' || rec.Capacidad);
    END LOOP;
END obtener_detalle_aula;
/

-- Procedimiento para eliminar una clase
CREATE OR REPLACE PROCEDURE eliminar_clase (
    p_id_clase IN INT
) AS
BEGIN
    DELETE FROM Clases
    WHERE id_clase = p_id_clase;
END eliminar_clase;
/

-- Procedimiento para asignar un profesor a una materia
CREATE OR REPLACE PROCEDURE asignar_profesor_materia (
    p_id_profesor IN INT,
    p_id_materia IN INT
) AS
BEGIN
    UPDATE Horarios
    SET id_profesor = p_id_profesor
    WHERE id_materia = p_id_materia;
END asignar_profesor_materia;
/

-- Procedimiento para asignar un aula a una clase
CREATE OR REPLACE PROCEDURE asignar_aula_clase (
    p_id_clase IN INT,
    p_id_aula IN INT
) AS
BEGIN
    UPDATE Clases
    SET id_aula = p_id_aula
    WHERE id_clase = p_id_clase;
END asignar_aula_clase;
/

-- Procedimiento para obtener las calificaciones de un estudiante
CREATE OR REPLACE PROCEDURE obtener_calificaciones (
    p_id_estudiante IN INT
) AS
BEGIN
    FOR rec IN (SELECT m.nombre_materia, n.calificacion FROM Notas n
                JOIN Materias m ON m.id_materia = n.id_materia
                WHERE n.id_estudiante = p_id_estudiante)
    LOOP
        DBMS_OUTPUT.PUT_LINE('Materia: ' || rec.nombre_materia || ' - Calificación: ' || rec.calificacion);
    END LOOP;
END obtener_calificaciones;
/

-- Procedimiento para obtener la cantidad de estudiantes inscritos
CREATE OR REPLACE PROCEDURE obtener_cantidad_estudiantes (
    p_id_materia IN INT,
    p_cantidad OUT INT
) AS
BEGIN
    SELECT COUNT(*)
    INTO p_cantidad
    FROM Inscripciones
    WHERE id_materia = p_id_materia;
END obtener_cantidad_estudiantes;
/

-- ==========================
-- CREACIÓN DE VISTAS PROYECTO BASE DE DATOS
-- ==========================

-- Vista de estudiantes con sus inscripciones
CREATE OR REPLACE VIEW vista_estudiantes_inscripciones AS
SELECT e.id_estudiante, e.nombre, e.apellido, i.id_materia
FROM Estudiantes e
JOIN Inscripciones i ON e.id_estudiante = i.id_estudiante;

-- Vista de profesores con las clases que imparten
CREATE OR REPLACE VIEW vista_profesores_clases AS
SELECT p.id_profesor, p.nombre, p.apellido, c.id_clase
FROM Profesores p
JOIN Clases c ON p.id_profesor = c.id_profesor;

-- Vista de materias y sus notas
CREATE OR REPLACE VIEW vista_materias_notas AS
SELECT m.nombre_materia, n.calificacion, e.nombre AS estudiante
FROM Materias m
JOIN Notas n ON m.id_materia = n.id_materia
JOIN Estudiantes e ON e.id_estudiante = n.id_estudiante;

-- Vista de aulas y su capacidad
CREATE OR REPLACE VIEW vista_aulas_capacidad AS
SELECT a.numero_Aula, a.Capacidad
FROM Aulas a;

-- Vista de estudiantes con congelamientos
CREATE OR REPLACE VIEW vista_estudiantes_congelados AS
SELECT e.nombre, e.apellido, c.fecha_Complemento
FROM Estudiantes e
JOIN Congelamiento c ON e.id_estudiante = c.id_estudiante;

-- Vista de materias sin inscripciones
CREATE OR REPLACE VIEW vista_materias_sin_inscripciones AS
SELECT m.nombre_materia
FROM Materias m
WHERE NOT EXISTS (
    SELECT 1 FROM Inscripciones i WHERE i.id_materia = m.id_materia
);

-- Vista de estudiantes con sus calificaciones
CREATE OR REPLACE VIEW vista_estudiantes_calificaciones AS
SELECT e.id_estudiante, e.nombre, e.apellido, m.nombre_materia, n.calificacion
FROM Estudiantes e
JOIN Notas n ON e.id_estudiante = n.id_estudiante
JOIN Materias m ON m.id_materia = n.id_materia;

-- Vista de clases y sus detalles
CREATE OR REPLACE VIEW vista_clases_detalles AS
SELECT c.id_clase, c.id_horario, a.numero_Aula, p.nombre AS profesor, m.nombre_materia
FROM Clases c
JOIN Aulas a ON a.id_aula = c.id_aula
JOIN Profesores p ON p.id_profesor = c.id_profesor
JOIN Materias m ON m.id_materia = c.id_materia;

-- Vista de aulas ocupadas
CREATE OR REPLACE VIEW vista_aulas_ocupadas AS
SELECT a.numero_Aula, COUNT(c.id_clase) AS clases_ocupando
FROM Aulas a
JOIN Clases c ON a.id_aula = c.id_aula
GROUP BY a.numero_Aula;

-- Vista de profesores con materias que imparten
CREATE OR REPLACE VIEW vista_profesores_materias AS
SELECT p.nombre, p.apellido, m.nombre_materia
FROM Profesores p
JOIN Horarios h ON p.id_profesor = h.id_profesor
JOIN Materias m ON m.id_materia = h.id_materia;
/


-- Vista de estudiantes con sus inscripciones
CREATE OR REPLACE VIEW vista_estudiantes_inscripciones AS
SELECT e.id_estudiante, e.nombre, e.apellido, i.id_materia
FROM Estudiantes e
JOIN Inscripciones i ON e.id_estudiante = i.id_estudiante;

-- Vista de profesores con las clases que imparten
CREATE OR REPLACE VIEW vista_profesores_clases AS
SELECT p.id_profesor, p.nombre, p.apellido, c.id_clase
FROM Profesores p
JOIN Clases c ON p.id_profesor = c.id_profesor;

-- Vista de materias y sus notas
CREATE OR REPLACE VIEW vista_materias_notas AS
SELECT m.nombre_materia, n.calificacion, e.nombre AS estudiante
FROM Materias m
JOIN Notas n ON m.id_materia = n.id_materia
JOIN Estudiantes e ON e.id_estudiante = n.id_estudiante;

-- Vista de aulas y su capacidad
CREATE OR REPLACE VIEW vista_aulas_capacidad AS
SELECT a.numero_Aula, a.Capacidad
FROM Aulas a;

-- Vista de estudiantes con congelamientos
CREATE OR REPLACE VIEW vista_estudiantes_congelados AS
SELECT e.nombre, e.apellido, c.fechaComplemento
FROM Estudiantes e
JOIN Congelamiento c ON e.id_estudiante = c.id_estudiante;

-- Vista de materias sin inscripciones
CREATE OR REPLACE VIEW vista_materias_sin_inscripciones AS
SELECT m.nombre_materia
FROM Materias m
WHERE NOT EXISTS (
    SELECT 1 FROM Inscripciones i WHERE i.id_materia = m.id_materia
);

-- Vista de estudiantes con sus calificaciones
CREATE OR REPLACE VIEW vista_estudiantes_calificaciones AS
SELECT e.id_estudiante, e.nombre, e.apellido, m.nombre_materia, n.calificacion
FROM Estudiantes e
JOIN Notas n ON e.id_estudiante = n.id_estudiante
JOIN Materias m ON m.id_materia = n.id_materia;

-- Vista de clases y sus detalles
CREATE OR REPLACE VIEW vista_clases_detalles AS
SELECT c.id_clase, c.id_horario, a.numero_Aula, p.nombre AS profesor, m.nombre_materia
FROM Clases c
JOIN Aulas a ON a.id_aula = c.id_aula
JOIN Profesores p ON p.id_profesor = c.id_profesor
JOIN Materias m ON m.id_materia = c.id_materia;

-- Vista de aulas ocupadas
CREATE OR REPLACE VIEW vista_aulas_ocupadas AS
SELECT a.numero_Aula, COUNT(c.id_clase) AS clases_ocupando
FROM Aulas a
JOIN Clases c ON a.id_aula = c.id_aula
GROUP BY a.numero_Aula;

-- Vista de profesores con materias que imparten
CREATE OR REPLACE VIEW vista_profesores_materias AS
SELECT p.nombre, p.apellido, m.nombre_materia
FROM Profesores p
JOIN Horarios h ON p.id_profesor = h.id_profesor
JOIN Materias m ON m.id_materia = h.id_materia;
/

-- ==========================
-- CREACIÓN DE FUNCIONES PROYECTO BASE DE DATOS
-- ==========================


-- Función para obtener el promedio de calificaciones de un estudiante
CREATE OR REPLACE FUNCTION obtener_promedio_estudiante (p_id_estudiante INT) RETURN DECIMAL IS
    v_promedio DECIMAL(5,2);
BEGIN
    SELECT AVG(n.calificacion)
    INTO v_promedio
    FROM Notas n
    WHERE n.id_estudiante = p_id_estudiante;
    RETURN v_promedio;
END obtener_promedio_estudiante;
/

-- Función para obtener la cantidad de inscripciones de un estudiante
CREATE OR REPLACE FUNCTION obtener_cantidad_inscripciones (p_id_estudiante INT) RETURN INT IS
    v_cantidad INT;
BEGIN
    SELECT COUNT(*)
    INTO v_cantidad
    FROM Inscripciones i
    WHERE i.id_estudiante = p_id_estudiante;
    RETURN v_cantidad;
END obtener_cantidad_inscripciones;
/

-- Función para verificar si un estudiante está inscrito en una materia
CREATE OR REPLACE FUNCTION verificar_inscripcion (p_id_estudiante INT, p_id_materia INT) RETURN VARCHAR2 IS
    v_estatus VARCHAR2(20);
BEGIN
    IF EXISTS (SELECT 1 FROM Inscripciones WHERE id_estudiante = p_id_estudiante AND id_materia = p_id_materia) THEN
        v_estatus := 'Inscrito';
    ELSE
        v_estatus := 'No inscrito';
    END IF;
    RETURN v_estatus;
END verificar_inscripcion;
/

-- Función para obtener la calificación de un estudiante en una materia
CREATE OR REPLACE FUNCTION obtener_calificacion_estudiante (p_id_estudiante INT, p_id_materia INT) RETURN DECIMAL IS
    v_calificacion DECIMAL(5,2);
BEGIN
    SELECT n.calificacion
    INTO v_calificacion
    FROM Notas n
    WHERE n.id_estudiante = p_id_estudiante AND n.id_materia = p_id_materia;
    RETURN v_calificacion;
END obtener_calificacion_estudiante;
/

-- Función para obtener el nombre de un aula por su ID
CREATE OR REPLACE FUNCTION obtener_nombre_aula (p_id_aula INT) RETURN VARCHAR2 IS
    v_nombre_aula VARCHAR2(50);
BEGIN
    SELECT a.numero_Aula
    INTO v_nombre_aula
    FROM Aulas a
    WHERE a.id_aula = p_id_aula;
    RETURN v_nombre_aula;
END obtener_nombre_aula;
/

-- Función para obtener la cantidad de clases que imparte un profesor
CREATE OR REPLACE FUNCTION obtener_clases_profesor (p_id_profesor INT) RETURN INT IS
    v_cantidad INT;
BEGIN
    SELECT COUNT(*)
    INTO v_cantidad
    FROM Clases c
    WHERE c.id_profesor = p_id_profesor;
    RETURN v_cantidad;
END obtener_clases_profesor;
/

-- Función para obtener la cantidad de aulas ocupadas

CREATE OR REPLACE FUNCTION obtener_aulas_ocupadas 
RETURN INT 
IS
    v_cantidad INT;
BEGIN
    SELECT COUNT(DISTINCT a.id_aula)
    INTO v_cantidad
    FROM Aulas a
    JOIN Clases c ON a.id_aula = c.id_aula;

    RETURN v_cantidad;
END obtener_aulas_ocupadas;


-- Función para obtener el profesor de una materia
CREATE OR REPLACE FUNCTION obtener_profesor_materia (p_id_materia INT) RETURN VARCHAR2 IS
    v_profesor VARCHAR2(255);
BEGIN
    SELECT p.nombre || ' ' || p.apellido
    INTO v_profesor
    FROM Profesores p
    JOIN Horarios h ON p.id_profesor = h.id_profesor
    WHERE h.id_materia = p_id_materia;
    RETURN v_profesor;
END obtener_profesor_materia;
/

-- Función para obtener los estudiantes inscritos en una materia
CREATE OR REPLACE FUNCTION obtener_estudiantes_materia (p_id_materia INT) RETURN SYS_REFCURSOR IS
    v_cursor SYS_REFCURSOR;
BEGIN
    OPEN v_cursor FOR
    SELECT e.nombre, e.apellido
    FROM Estudiantes e
    JOIN Inscripciones i ON e.id_estudiante = i.id_estudiante
    WHERE i.id_materia = p_id_materia;
    RETURN v_cursor;
END obtener_estudiantes_materia;
/

-- Función para obtener la capacidad de un aula
CREATE OR REPLACE FUNCTION obtener_capacidad_aula (p_id_aula INT) RETURN INT IS
    v_capacidad INT;
BEGIN
    SELECT a.Capacidad
    INTO v_capacidad
    FROM Aulas a
    WHERE a.id_aula = p_id_aula;
    RETURN v_capacidad;
END obtener_capacidad_aula;
/

-- Función para obtener el nombre completo de un estudiante
CREATE OR REPLACE FUNCTION obtener_nombre_estudiante (p_id_estudiante INT) RETURN VARCHAR2 IS
    v_nombre_estudiante VARCHAR2(255);
BEGIN
    SELECT e.nombre || ' ' || e.apellido
    INTO v_nombre_estudiante
    FROM Estudiantes e
    WHERE e.id_estudiante = p_id_estudiante;
    RETURN v_nombre_estudiante;
END obtener_nombre_estudiante;
/

-- Función para obtener las materias en las que está inscrito un estudiante
CREATE OR REPLACE FUNCTION obtener_materias_estudiante (p_id_estudiante INT) RETURN SYS_REFCURSOR IS
    v_cursor SYS_REFCURSOR;
BEGIN
    OPEN v_cursor FOR
    SELECT m.nombre_materia
    FROM Materias m
    JOIN Inscripciones i ON m.id_materia = i.id_materia
    WHERE i.id_estudiante = p_id_estudiante;
    RETURN v_cursor;
END obtener_materias_estudiante;
/

-- Función para obtener las clases programadas para un aula
CREATE OR REPLACE FUNCTION obtener_clases_aula (p_id_aula INT) RETURN SYS_REFCURSOR IS
    v_cursor SYS_REFCURSOR;
BEGIN
    OPEN v_cursor FOR
    SELECT c.id_clase, p.nombre AS profesor, m.nombre_materia
    FROM Clases c
    JOIN Profesores p ON c.id_profesor = p.id_profesor
    JOIN Materias m ON c.id_materia = m.id_materia
    WHERE c.id_aula = p_id_aula;
    RETURN v_cursor;
END obtener_clases_aula;
/

-- Función para obtener el horario de una clase
CREATE OR REPLACE FUNCTION obtener_horario_clase (p_id_clase INT) RETURN VARCHAR2 IS
    v_horario VARCHAR2(255);
BEGIN
    SELECT 'De ' || TO_CHAR(h.horario_Inc, 'HH:MI AM') || ' a ' || TO_CHAR(h.horario_Fin, 'HH:MI AM')
    INTO v_horario
    FROM Horarios h
    WHERE h.id_horario = (SELECT id_horario FROM Clases WHERE id_clase = p_id_clase);
    RETURN v_horario;
END obtener_horario_clase;
/

-- Función para verificar si un profesor tiene alguna clase programada

CREATE OR REPLACE FUNCTION verificar_clases_profesor (p_id_profesor INT) 
RETURN VARCHAR2 IS
    v_estatus VARCHAR2(20);
BEGIN
    SELECT CASE 
             WHEN COUNT(1) > 0 THEN 'Tiene clases'
             ELSE 'No tiene clases'
           END
    INTO v_estatus
    FROM Clases c
    WHERE c.id_profesor = p_id_profesor;

    RETURN v_estatus;
END verificar_clases_profesor;
