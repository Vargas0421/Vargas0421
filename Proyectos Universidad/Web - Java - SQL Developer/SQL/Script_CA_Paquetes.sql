-- ==========================
-- CREACIÓN DE PAQUETES PROYECTO BASE DE DATOS
-- ==========================

----------------------------------------------

-- =======================================
-- Paquete para la tabla Estudiantes
-- =======================================
create or replace PACKAGE pkg_estudiante AS

  PROCEDURE obtener_estudiantes(p_result OUT SYS_REFCURSOR);

  PROCEDURE obtener_estudiante_por_id(
    p_id_estudiante IN INT,
    p_result OUT SYS_REFCURSOR
  );

  PROCEDURE actualizar_estudiante(
    p_id_estudiante       IN  INT,
    p_cedula_estudiante   IN  VARCHAR2,
    p_nombre              IN  VARCHAR2,
    p_apellido            IN  VARCHAR2,
    p_direccion           IN  VARCHAR2,
    p_telefono            IN  VARCHAR2,
    p_correo_electronico  IN  VARCHAR2,
    p_codigo_estudiante   IN  VARCHAR2
  );

  PROCEDURE eliminar_estudiante(
    p_id_estudiante IN INT
  );

  PROCEDURE insertar_estudiante (
    p_cedula IN VARCHAR2,
    p_nombre IN VARCHAR2,
    p_apellido IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_telefono IN VARCHAR2,
    p_correo IN VARCHAR2,
    p_codigo_estudiante IN VARCHAR2
  );

END pkg_estudiante;
/

create or replace NONEDITIONABLE PACKAGE BODY pkg_estudiante AS

  PROCEDURE obtener_estudiantes(p_result OUT SYS_REFCURSOR) IS
  BEGIN
    OPEN p_result FOR SELECT * FROM Estudiantes;
  END obtener_estudiantes;

  PROCEDURE obtener_estudiante_por_id(
    p_id_estudiante IN INT,
    p_result OUT SYS_REFCURSOR
  ) IS
  BEGIN
    OPEN p_result FOR
      SELECT id_estudiante, cedula_estudiante, nombre, apellido, direccion, telefono, correo_electronico, codigo_estudiante
      FROM Estudiantes
      WHERE id_estudiante = p_id_estudiante;
  END obtener_estudiante_por_id;

  PROCEDURE actualizar_estudiante(
    p_id_estudiante       IN  INT,
    p_cedula_estudiante   IN  VARCHAR2,
    p_nombre              IN  VARCHAR2,
    p_apellido            IN  VARCHAR2,
    p_direccion           IN  VARCHAR2,
    p_telefono            IN  VARCHAR2,
    p_correo_electronico  IN  VARCHAR2,
    p_codigo_estudiante   IN  VARCHAR2
  ) IS
  BEGIN
    UPDATE Estudiantes
    SET
      cedula_estudiante   = p_cedula_estudiante,
      nombre              = p_nombre,
      apellido            = p_apellido,
      direccion           = p_direccion,
      telefono            = p_telefono,
      correo_electronico  = p_correo_electronico,
      codigo_estudiante   = p_codigo_estudiante
    WHERE id_estudiante = p_id_estudiante;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END actualizar_estudiante;

  PROCEDURE eliminar_estudiante(
    p_id_estudiante IN INT
  ) IS
  BEGIN
    DELETE FROM Estudiantes
    WHERE id_estudiante = p_id_estudiante;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END eliminar_estudiante;

  PROCEDURE insertar_estudiante (
    p_cedula IN VARCHAR2,
    p_nombre IN VARCHAR2,
    p_apellido IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_telefono IN VARCHAR2,
    p_correo IN VARCHAR2,
    p_codigo_estudiante IN VARCHAR2
  ) IS
  BEGIN
    INSERT INTO Estudiantes (cedula_estudiante, nombre, apellido, direccion, telefono, correo_electronico, codigo_estudiante)
    VALUES (p_cedula, p_nombre, p_apellido, p_direccion, p_telefono, p_correo,p_codigo_estudiante);
  END insertar_estudiante;

END pkg_estudiante;
/




-- =======================================
-- Paquete para la tabla Archivos
-- =======================================
CREATE OR REPLACE NONEDITIONABLE PACKAGE pkg_archivos AS

  PROCEDURE obtener_archivos(
    p_result OUT SYS_REFCURSOR
  );

  PROCEDURE obtener_archivo_por_id(
    p_id_archivo IN INT,
    p_result OUT SYS_REFCURSOR
  );

  PROCEDURE insertar_archivo(
    p_id_archivo     IN INT,
    p_id_estudiante  IN INT,
    p_tipo_documento IN VARCHAR2
  );

  PROCEDURE actualizar_archivo(
    p_id_archivo     IN INT,
    p_id_estudiante  IN INT,
    p_tipo_documento IN VARCHAR2
  );

  PROCEDURE eliminar_archivo(
    p_id_archivo IN INT
  );

END pkg_archivos;
/

CREATE OR REPLACE PACKAGE BODY pkg_archivos AS

    PROCEDURE obtener_archivos(p_result OUT SYS_REFCURSOR) IS
    BEGIN
    OPEN p_result FOR SELECT * FROM archivos;
    END obtener_archivos;

  PROCEDURE obtener_archivo_por_id(
    p_id_archivo IN INT,
    p_result OUT SYS_REFCURSOR
  ) IS
  BEGIN
    OPEN p_result FOR
      SELECT id_archivo, id_estudiante, tipo_Documento
      FROM Archivos
      WHERE id_archivo = p_id_archivo;
  END obtener_archivo_por_id;

  PROCEDURE insertar_archivo(
    p_id_archivo     IN INT,
    p_id_estudiante  IN INT,
    p_tipo_documento IN VARCHAR2
  ) IS
  BEGIN
    INSERT INTO Archivos (id_archivo, id_estudiante, tipo_Documento)
    VALUES (p_id_archivo, p_id_estudiante, p_tipo_documento);
  END insertar_archivo;

  PROCEDURE actualizar_archivo(
    p_id_archivo     IN INT,
    p_id_estudiante  IN INT,
    p_tipo_documento IN VARCHAR2
  ) IS
  BEGIN
    UPDATE Archivos
    SET id_estudiante = p_id_estudiante,
        tipo_Documento = p_tipo_documento
    WHERE id_archivo = p_id_archivo;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END actualizar_archivo;

  PROCEDURE eliminar_archivo(
    p_id_archivo IN INT
  ) IS
  BEGIN
    DELETE FROM Archivos
    WHERE id_archivo = p_id_archivo;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END eliminar_archivo;

END pkg_archivos;
/


-- =======================================
-- Paquete para la tabla Profesores
-- =======================================

CREATE OR REPLACE PACKAGE pkg_profesores AS

  PROCEDURE obtener_profesores(
    p_result OUT SYS_REFCURSOR
  );

  PROCEDURE obtener_profesor_por_id(
    p_id_profesor IN INT,
    p_result OUT SYS_REFCURSOR
  );

  PROCEDURE insertar_profesor(
    p_id_profesor      IN INT,
    p_cedula_profesor  IN VARCHAR2,
    p_nombre           IN VARCHAR2,
    p_codigo_profesor  IN VARCHAR2,
    p_apellido         IN VARCHAR2
  );

  PROCEDURE actualizar_profesor(
    p_id_profesor      IN INT,
    p_cedula_profesor  IN VARCHAR2,
    p_nombre           IN VARCHAR2,
    p_codigo_profesor  IN VARCHAR2,
    p_apellido         IN VARCHAR2
  );

  PROCEDURE eliminar_profesor(
    p_id_profesor IN INT
  );

END pkg_profesores;
/

CREATE OR REPLACE NONEDITIONABLE PACKAGE BODY pkg_profesores AS

    PROCEDURE obtener_profesores(   
    p_result OUT SYS_REFCURSOR
    )
    IS
    BEGIN
        OPEN p_result FOR SELECT * FROM Profesores;
    END;

  PROCEDURE obtener_profesor_por_id(
    p_id_profesor IN INT,
    p_result OUT SYS_REFCURSOR
  ) IS
  BEGIN
    OPEN p_result FOR
      SELECT id_profesor, cedula_profesor, nombre, codigo_profesor, apellido
      FROM Profesores
      WHERE id_profesor = p_id_profesor;
  END obtener_profesor_por_id;

  PROCEDURE insertar_profesor(
    p_id_profesor      IN INT,
    p_cedula_profesor  IN VARCHAR2,
    p_nombre           IN VARCHAR2,
    p_codigo_profesor  IN VARCHAR2,
    p_apellido         IN VARCHAR2
  ) IS
  BEGIN
    INSERT INTO Profesores (id_profesor, cedula_profesor, nombre, codigo_profesor, apellido)
    VALUES (p_id_profesor, p_cedula_profesor, p_nombre, p_codigo_profesor, p_apellido);
  END insertar_profesor;

  PROCEDURE actualizar_profesor(
    p_id_profesor      IN INT,
    p_cedula_profesor  IN VARCHAR2,
    p_nombre           IN VARCHAR2,
    p_codigo_profesor  IN VARCHAR2,
    p_apellido         IN VARCHAR2
  ) IS
  BEGIN
    UPDATE Profesores
    SET cedula_profesor = p_cedula_profesor,
        nombre          = p_nombre,
        codigo_profesor = p_codigo_profesor,
        apellido        = p_apellido
    WHERE id_profesor = p_id_profesor;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END actualizar_profesor;

  PROCEDURE eliminar_profesor(
    p_id_profesor IN INT
  ) IS
  BEGIN
    DELETE FROM Profesores
    WHERE id_profesor = p_id_profesor;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END eliminar_profesor;

END pkg_profesores;
/

-- =======================================
-- Paquete para la tabla Materias
-- =======================================
CREATE OR REPLACE NONEDITIONABLE PACKAGE pkg_materias AS

   PROCEDURE obtener_materias(
    p_result OUT SYS_REFCURSOR
   );

  PROCEDURE obtener_materia_por_id(
    p_id_materia IN INT,
    p_result OUT SYS_REFCURSOR
  );

  PROCEDURE insertar_materia(
    p_id_materia      IN INT,
    p_nombre_materia  IN VARCHAR2,
    p_codigo_materia  IN VARCHAR2,
    p_descripcion     IN VARCHAR2
  );

  PROCEDURE actualizar_materia(
    p_id_materia      IN INT,
    p_nombre_materia  IN VARCHAR2,
    p_codigo_materia  IN VARCHAR2,
    p_descripcion     IN VARCHAR2
  );

  PROCEDURE eliminar_materia(
    p_id_materia IN INT
  );

END pkg_materias;
/

CREATE OR REPLACE NONEDITIONABLE PACKAGE BODY pkg_materias AS

    PROCEDURE obtener_materias(
        p_result OUT SYS_REFCURSOR
    )
    IS
    BEGIN
        OPEN p_result FOR SELECT * FROM materias;
    END;


  PROCEDURE obtener_materia_por_id(
    p_id_materia IN INT,
    p_result OUT SYS_REFCURSOR
  ) IS
  BEGIN
    OPEN p_result FOR
      SELECT id_materia, nombre_materia, codigo_materia, descripcion
      FROM Materias
      WHERE id_materia = p_id_materia;
  END obtener_materia_por_id;

  PROCEDURE insertar_materia(
    p_id_materia      IN INT,
    p_nombre_materia  IN VARCHAR2,
    p_codigo_materia  IN VARCHAR2,
    p_descripcion     IN VARCHAR2
  ) IS
  BEGIN
    INSERT INTO Materias (id_materia, nombre_materia, codigo_materia, descripcion)
    VALUES (p_id_materia, p_nombre_materia, p_codigo_materia, p_descripcion);
  END insertar_materia;

  PROCEDURE actualizar_materia(
    p_id_materia      IN INT,
    p_nombre_materia  IN VARCHAR2,
    p_codigo_materia  IN VARCHAR2,
    p_descripcion     IN VARCHAR2
  ) IS
  BEGIN
    UPDATE Materias
    SET nombre_materia = p_nombre_materia,
        codigo_materia = p_codigo_materia,
        descripcion    = p_descripcion
    WHERE id_materia = p_id_materia;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END actualizar_materia;

  PROCEDURE eliminar_materia(
    p_id_materia IN INT
  ) IS
  BEGIN
    DELETE FROM Materias
    WHERE id_materia = p_id_materia;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END eliminar_materia;

END pkg_materias;
/


-- =======================================
-- Paquete para la tabla Aulas
-- =======================================
CREATE OR REPLACE PACKAGE pkg_aulas AS
   PROCEDURE obtener_aulas(
    p_result OUT SYS_REFCURSOR
   );

  PROCEDURE obtener_aula_por_id(
    p_id_aula IN INT,
    p_result OUT SYS_REFCURSOR
  );

  PROCEDURE insertar_aula(
    p_numero_Aula  IN VARCHAR2,
    p_capacidad  IN INT
    
  );

  PROCEDURE actualizar_aula(
    p_id_aula      IN INT,
    p_numero_Aula  IN VARCHAR2,
    p_capacidad  IN INT
  );

  PROCEDURE eliminar_aula(
    p_id_aula IN INT
  );
END pkg_aulas;
/

CREATE OR REPLACE PACKAGE BODY pkg_aulas AS

      PROCEDURE obtener_aulas(
        p_result OUT SYS_REFCURSOR
    )
    IS
    BEGIN
        OPEN p_result FOR SELECT * FROM aulas;
    END;


  PROCEDURE obtener_aula_por_id(
    p_id_aula IN INT,
    p_result OUT SYS_REFCURSOR
  ) IS
  BEGIN
    OPEN p_result FOR
      SELECT id_aula, numero_Aula, capacidad
      FROM Aulas
      WHERE id_aula = p_id_aula;
  END obtener_aula_por_id;

  PROCEDURE insertar_aula(
     p_numero_Aula  IN VARCHAR2,
    p_capacidad  IN INT
  ) IS
  BEGIN
    INSERT INTO Aulas (numero_Aula, capacidad)
    VALUES (p_numero_Aula, p_capacidad);
  END insertar_aula;

  PROCEDURE actualizar_aula(
    p_id_aula  IN INT,
    p_numero_Aula  IN VARCHAR2,
    p_capacidad  IN INT
  ) IS
  BEGIN
    UPDATE Aulas
    SET numero_Aula = p_numero_Aula,
        capacidad = p_capacidad
        
    WHERE id_aula = p_id_aula;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END actualizar_aula;

  PROCEDURE eliminar_aula(
    p_id_aula IN INT
  ) IS
  BEGIN
    DELETE FROM Aulas
    WHERE id_aula = p_id_aula;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END eliminar_aula;

END pkg_aulas;
/


-- =======================================
-- Paquete para la tabla Horarios
-- =======================================
CREATE OR REPLACE NONEDITIONABLE PACKAGE pkg_horarios AS
  
  PROCEDURE obtener_horarios(
    p_result OUT SYS_REFCURSOR
   );

  PROCEDURE obtener_horario_por_id(
    p_id_horario IN INT,
    p_result OUT SYS_REFCURSOR
  );

  PROCEDURE insertar_horario(
    p_id_profesor IN INT,
    p_id_materia IN INT,
    p_dia_Semana IN VARCHAR2,
    p_horario_Inc IN TIMESTAMP,
    p_horario_Fin IN TIMESTAMP
  );

  PROCEDURE actualizar_horario(
    p_id_horario IN INT,
    p_id_profesor IN INT,
    p_id_materia IN INT,
    p_dia_Semana IN VARCHAR2,
    p_horario_Inc IN TIMESTAMP,
    p_horario_Fin IN TIMESTAMP
  );

  PROCEDURE eliminar_horario(
    p_id_horario IN INT
  );
  
END pkg_horarios;
/

create or replace NONEDITIONABLE PACKAGE BODY pkg_horarios AS
    
    PROCEDURE obtener_horarios(
    p_result OUT SYS_REFCURSOR
     )
    IS
    BEGIN
    OPEN p_result FOR
    SELECT * FROM Horarios;
    END obtener_horarios;

    PROCEDURE obtener_horario_por_id(
    p_id_horario IN INT,
    p_result OUT SYS_REFCURSOR
    )
    IS
    BEGIN
        OPEN p_result FOR
        SELECT * FROM Horarios
        WHERE id_horario = p_id_horario;
    END obtener_horario_por_id;

    PROCEDURE insertar_horario(
    p_id_profesor IN INT,
    p_id_materia IN INT,
    p_dia_Semana IN VARCHAR2,
    p_horario_Inc IN TIMESTAMP,
    p_horario_Fin IN TIMESTAMP
    )
    IS
    BEGIN
        INSERT INTO Horarios (id_profesor, id_materia, dia_Semana, horario_Inc, horario_Fin)
        VALUES (p_id_profesor, p_id_materia, p_dia_Semana, p_horario_Inc, p_horario_Fin);
    END insertar_horario;

    PROCEDURE actualizar_horario(
    p_id_horario IN INT,
    p_id_profesor IN INT,
    p_id_materia IN INT,
    p_dia_Semana IN VARCHAR2,
    p_horario_Inc IN TIMESTAMP,
    p_horario_Fin IN TIMESTAMP
    )
    IS
    BEGIN
        UPDATE Horarios
        SET
            id_profesor = p_id_profesor,
            id_materia = p_id_materia,
            dia_Semana = p_dia_Semana,
            horario_Inc = p_horario_Inc,
            horario_Fin = p_horario_Fin
        WHERE id_horario = p_id_horario;

        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END actualizar_horario;

    PROCEDURE eliminar_horario(
    p_id_horario IN INT
    )
    IS
    BEGIN
        DELETE FROM Horarios
        WHERE id_horario = p_id_horario;

        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END eliminar_horario;

END pkg_horarios;


-- =======================================
-- Paquete para la tabla Clases
-- =======================================
CREATE OR REPLACE PACKAGE pkg_clases AS

  PROCEDURE obtener_clases(p_result OUT SYS_REFCURSOR);

  PROCEDURE obtener_clase_por_id(
    p_id_clase IN INT,
    p_result OUT SYS_REFCURSOR
  );

  PROCEDURE insertar_clase(
    p_id_horario IN INT,
    p_id_profesor IN INT,
    p_id_materia IN INT,
    p_id_aula IN INT
  );

  PROCEDURE actualizar_clase(
    p_id_clase IN INT,
    p_id_horario IN INT,
    p_id_profesor IN INT,
    p_id_materia IN INT,
    p_id_aula IN INT
  );

  PROCEDURE eliminar_clase(
    p_id_clase IN INT
  );

END pkg_clases;
/

CREATE OR REPLACE NONEDITIONABLE PACKAGE BODY pkg_clases AS

  PROCEDURE obtener_clases(p_result OUT SYS_REFCURSOR) IS
  BEGIN
    OPEN p_result FOR
      SELECT id_clase, id_horario, id_profesor, id_materia, id_aula
      FROM Clases;
  END obtener_clases;

  PROCEDURE obtener_clase_por_id(
    p_id_clase IN INT,
    p_result OUT SYS_REFCURSOR
  ) IS
  BEGIN
    OPEN p_result FOR
      SELECT id_clase, id_horario, id_profesor, id_materia, id_aula
      FROM Clases
      WHERE id_clase = p_id_clase;
  END obtener_clase_por_id;

  PROCEDURE insertar_clase(
    p_id_horario IN INT,
    p_id_profesor IN INT,
    p_id_materia IN INT,
    p_id_aula IN INT
  ) IS
  BEGIN
    INSERT INTO Clases (id_horario, id_profesor, id_materia, id_aula)
    VALUES (p_id_horario, p_id_profesor, p_id_materia, p_id_aula);
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END insertar_clase;

  PROCEDURE actualizar_clase(
    p_id_clase IN INT,
    p_id_horario IN INT,
    p_id_profesor IN INT,
    p_id_materia IN INT,
    p_id_aula IN INT
  ) IS
  BEGIN
    UPDATE Clases
    SET id_horario = p_id_horario,
        id_profesor = p_id_profesor,
        id_materia = p_id_materia,
        id_aula = p_id_aula
    WHERE id_clase = p_id_clase;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END actualizar_clase;

  PROCEDURE eliminar_clase(
    p_id_clase IN INT
  ) IS
  BEGIN
    DELETE FROM Clases
    WHERE id_clase = p_id_clase;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END eliminar_clase;

END pkg_clases;
/

-- =======================================
-- Paquete para la tabla Inscripciones
-- =======================================
CREATE OR REPLACE PACKAGE pkg_inscripciones AS

  PROCEDURE obtener_inscripciones(p_result OUT SYS_REFCURSOR);

  PROCEDURE obtener_inscripcion_por_id(
    p_id_inscripcion IN INT,
    p_result OUT SYS_REFCURSOR
  );

  PROCEDURE insertar_inscripcion(
    p_id_materia IN INT,
    p_id_estudiante IN INT
  );

  PROCEDURE actualizar_inscripcion(
    p_id_inscripcion IN INT,
    p_id_materia IN INT,
    p_id_estudiante IN INT
  );

  PROCEDURE eliminar_inscripcion(
    p_id_inscripcion IN INT
  );

END pkg_inscripciones;
/

CREATE OR REPLACE NONEDITIONABLE PACKAGE BODY pkg_inscripciones AS

  PROCEDURE obtener_inscripciones(p_result OUT SYS_REFCURSOR) IS
  BEGIN
    OPEN p_result FOR
      SELECT id_inscripcion, id_materia, id_estudiante
      FROM Inscripciones;
  END obtener_inscripciones;

  PROCEDURE obtener_inscripcion_por_id(
    p_id_inscripcion IN INT,
    p_result OUT SYS_REFCURSOR
  ) IS
  BEGIN
    OPEN p_result FOR
      SELECT id_inscripcion, id_materia, id_estudiante
      FROM Inscripciones
      WHERE id_inscripcion = p_id_inscripcion;
  END obtener_inscripcion_por_id;

  PROCEDURE insertar_inscripcion(
    p_id_materia IN INT,
    p_id_estudiante IN INT
  ) IS
  BEGIN
    INSERT INTO Inscripciones (id_materia, id_estudiante)
    VALUES (p_id_materia, p_id_estudiante);
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END insertar_inscripcion;

  PROCEDURE actualizar_inscripcion(
    p_id_inscripcion IN INT,
    p_id_materia IN INT,
    p_id_estudiante IN INT
  ) IS
  BEGIN
    UPDATE Inscripciones
    SET id_materia = p_id_materia,
        id_estudiante = p_id_estudiante
    WHERE id_inscripcion = p_id_inscripcion;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END actualizar_inscripcion;

  PROCEDURE eliminar_inscripcion(
    p_id_inscripcion IN INT
  ) IS
  BEGIN
    DELETE FROM Inscripciones
    WHERE id_inscripcion = p_id_inscripcion;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END eliminar_inscripcion;

END pkg_inscripciones;
/


-- =======================================
-- Paquete para la tabla Notas
-- =======================================
CREATE OR REPLACE PACKAGE pkg_notas AS

  PROCEDURE obtener_notas(p_result OUT SYS_REFCURSOR);

  PROCEDURE obtener_nota_por_id(
    p_id_nota IN INT,
    p_result OUT SYS_REFCURSOR
  );

  PROCEDURE insertar_nota(
    p_id_profesor IN INT,
    p_id_materia IN INT,
    p_id_estudiante IN INT,
    p_calificacion IN DECIMAL
  );

  PROCEDURE actualizar_nota(
    p_id_nota IN INT,
    p_id_profesor IN INT,
    p_id_materia IN INT,
    p_id_estudiante IN INT,
    p_calificacion IN DECIMAL
  );

  PROCEDURE eliminar_nota(
    p_id_nota IN INT
  );

END pkg_notas;
/

CREATE OR REPLACE NONEDITIONABLE PACKAGE BODY pkg_notas AS

  PROCEDURE obtener_notas(p_result OUT SYS_REFCURSOR) IS
  BEGIN
    OPEN p_result FOR
      SELECT id_nota, id_profesor, id_materia, id_estudiante, calificacion
      FROM Notas;
  END obtener_notas;

  PROCEDURE obtener_nota_por_id(
    p_id_nota IN INT,
    p_result OUT SYS_REFCURSOR
  ) IS
  BEGIN
    OPEN p_result FOR
      SELECT id_nota, id_profesor, id_materia, id_estudiante, calificacion
      FROM Notas
      WHERE id_nota = p_id_nota;
  END obtener_nota_por_id;

  PROCEDURE insertar_nota(
    p_id_profesor IN INT,
    p_id_materia IN INT,
    p_id_estudiante IN INT,
    p_calificacion IN DECIMAL
  ) IS
  BEGIN
    INSERT INTO Notas (id_profesor, id_materia, id_estudiante, calificacion)
    VALUES (p_id_profesor, p_id_materia, p_id_estudiante, p_calificacion);
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END insertar_nota;

  PROCEDURE actualizar_nota(
    p_id_nota IN INT,
    p_id_profesor IN INT,
    p_id_materia IN INT,
    p_id_estudiante IN INT,
    p_calificacion IN DECIMAL
  ) IS
  BEGIN
    UPDATE Notas
    SET id_profesor = p_id_profesor,
        id_materia = p_id_materia,
        id_estudiante = p_id_estudiante,
        calificacion = p_calificacion
    WHERE id_nota = p_id_nota;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END actualizar_nota;

  PROCEDURE eliminar_nota(
    p_id_nota IN INT
  ) IS
  BEGIN
    DELETE FROM Notas
    WHERE id_nota = p_id_nota;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END eliminar_nota;

END pkg_notas;
/


-- =======================================
-- Paquete para la tabla Congelamiento
-- =======================================
CREATE OR REPLACE PACKAGE pkg_congelamiento AS

  PROCEDURE obtener_congelamientos(p_result OUT SYS_REFCURSOR);

  PROCEDURE obtener_congelamiento_por_id(
    p_id_congelamiento IN INT,
    p_result OUT SYS_REFCURSOR
  );

  PROCEDURE insertar_congelamiento(
    p_id_estudiante IN INT,
    p_fechaComplemento IN DATE
  );

  PROCEDURE actualizar_congelamiento(
    p_id_congelamiento IN INT,
    p_id_estudiante IN INT,
    p_fechaComplemento IN DATE
  );

  PROCEDURE eliminar_congelamiento(
    p_id_congelamiento IN INT
  );

END pkg_congelamiento;
/

CREATE OR REPLACE NONEDITIONABLE PACKAGE BODY pkg_congelamiento AS

  PROCEDURE obtener_congelamientos(p_result OUT SYS_REFCURSOR) IS
  BEGIN
    OPEN p_result FOR
      SELECT id_congelamiento, id_estudiante, fechaComplemento
      FROM Congelamiento;
  END obtener_congelamientos;

  PROCEDURE obtener_congelamiento_por_id(
    p_id_congelamiento IN INT,
    p_result OUT SYS_REFCURSOR
  ) IS
  BEGIN
    OPEN p_result FOR
      SELECT id_congelamiento, id_estudiante, fechaComplemento
      FROM Congelamiento
      WHERE id_congelamiento = p_id_congelamiento;
  END obtener_congelamiento_por_id;

  PROCEDURE insertar_congelamiento(
    p_id_estudiante IN INT,
    p_fechaComplemento IN DATE
  ) IS
  BEGIN
    INSERT INTO Congelamiento (id_estudiante, fechaComplemento)
    VALUES (p_id_estudiante, p_fechaComplemento);
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END insertar_congelamiento;

  PROCEDURE actualizar_congelamiento(
    p_id_congelamiento IN INT,
    p_id_estudiante IN INT,
    p_fechaComplemento IN DATE
  ) IS
  BEGIN
    UPDATE Congelamiento
    SET id_estudiante = p_id_estudiante,
        fechaComplemento = p_fechaComplemento
    WHERE id_congelamiento = p_id_congelamiento;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END actualizar_congelamiento;

  PROCEDURE eliminar_congelamiento(
    p_id_congelamiento IN INT
  ) IS
  BEGIN
    DELETE FROM Congelamiento
    WHERE id_congelamiento = p_id_congelamiento;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END eliminar_congelamiento;

END pkg_congelamiento;
/


-- =======================================
-- Paquete para la tabla Usuarios
-- =======================================
CREATE OR REPLACE PACKAGE pkg_usuarios AS

  PROCEDURE obtener_usuarios(
  p_result OUT SYS_REFCURSOR
  );

  PROCEDURE obtener_usuario_por_id(
    p_id_usuario IN INT,
    p_result OUT SYS_REFCURSOR
  );

  PROCEDURE actualizar_usuario(
    p_id_usuario IN INT,
    p_username IN VARCHAR2,
    p_password IN VARCHAR2,
    p_nombre IN VARCHAR2,
    p_apellidos IN VARCHAR2,
    p_correo IN VARCHAR2,
    p_activo IN VARCHAR2
  );

  PROCEDURE eliminar_usuario(
    p_id_usuario IN INT
  );

  PROCEDURE insertar_usuario (
    p_username IN VARCHAR2,
    p_password IN VARCHAR2,
    p_nombre IN VARCHAR2,
    p_apellidos IN VARCHAR2,
    p_correo IN VARCHAR2,
    p_activo IN VARCHAR2
  );
  
  
  
  
END pkg_usuarios;
/

CREATE OR REPLACE PACKAGE BODY pkg_usuarios AS

    PROCEDURE obtener_usuarios(
     p_result OUT SYS_REFCURSOR
    ) IS
    BEGIN
         OPEN p_result FOR SELECT * FROM Usuarios;
    END obtener_usuarios;

PROCEDURE obtener_usuario_por_id(
    p_id_usuario IN INT,
    p_result OUT SYS_REFCURSOR
) IS
BEGIN
    OPEN p_result FOR
    SELECT * FROM Usuarios
    WHERE id_usuario = p_id_usuario;
END obtener_usuario_por_id;
  
PROCEDURE actualizar_usuario(
    p_id_usuario IN INT,
    p_username IN VARCHAR2,
    p_password IN VARCHAR2,
    p_nombre IN VARCHAR2,
    p_apellidos IN VARCHAR2,
    p_correo IN VARCHAR2,
    p_activo IN VARCHAR2
) IS
BEGIN
    UPDATE Usuarios
    SET
        username = p_username,
        password = p_password,
        nombre = p_nombre,
        apellidos = p_apellidos,
        correo = p_correo,
        activo = p_activo
    WHERE id_usuario = p_id_usuario;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END actualizar_usuario;

PROCEDURE eliminar_usuario(
    p_id_usuario IN INT
) IS
BEGIN
    DELETE FROM Usuarios
    WHERE id_usuario = p_id_usuario;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END eliminar_usuario;

PROCEDURE insertar_usuario (
    p_username IN VARCHAR2,
    p_password IN VARCHAR2,
    p_nombre IN VARCHAR2,
    p_apellidos IN VARCHAR2,
    p_correo IN VARCHAR2,
    p_activo IN VARCHAR2
) AS
BEGIN
    INSERT INTO Usuarios (username, password, nombre, apellidos, correo, activo)
    VALUES (p_username, p_password, p_nombre, p_apellidos, p_correo, p_activo);
END insertar_usuario;


END pkg_usuarios;
/


-- =======================================
-- Paquete para la tabla Rol
-- =======================================
CREATE OR REPLACE PACKAGE pkg_rol AS
  PROCEDURE obtener_roles(
    p_result OUT SYS_REFCURSOR
    );
    
    PROCEDURE obtener_rol_por_id(
        p_id_rol IN INT,
        p_cursor OUT SYS_REFCURSOR
    );
    
    PROCEDURE insertar_rol (
        p_nombre IN VARCHAR2,
        p_id_usuario IN INT
    );
    
    PROCEDURE actualizar_rol(
        p_id_rol IN INT,
        p_nombre IN VARCHAR2,
        p_id_usuario IN INT
    );
    
    PROCEDURE eliminar_rol(
    p_id_rol IN INT
    );
    
END pkg_rol;
/

CREATE OR REPLACE PACKAGE BODY pkg_rol AS

    PROCEDURE obtener_roles(
    p_result OUT SYS_REFCURSOR
    ) IS
    BEGIN
    OPEN p_result FOR SELECT * FROM Rol;
    END obtener_roles;

    PROCEDURE obtener_rol_por_id(
        p_id_rol IN INT,
        p_cursor OUT SYS_REFCURSOR
    ) IS
    BEGIN
        OPEN p_cursor FOR
        SELECT * FROM Rol
        WHERE id_rol = p_id_rol;
    END obtener_rol_por_id;

    PROCEDURE insertar_rol (
        p_nombre IN VARCHAR2,
        p_id_usuario IN INT
    )   AS
    BEGIN
        INSERT INTO Rol (nombre_rol, id_usuario)
        VALUES (p_nombre, p_id_usuario);
    END insertar_rol;

    PROCEDURE actualizar_rol(
        p_id_rol IN INT,
        p_nombre IN VARCHAR2,
        p_id_usuario IN INT
    ) IS
    BEGIN
        UPDATE Rol
        SET
            nombre_rol = p_nombre,
            id_usuario = p_id_usuario
        WHERE id_rol = p_id_rol;

        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END actualizar_rol;
    
     PROCEDURE eliminar_rol(
    p_id_rol IN INT
    ) IS
    BEGIN
        DELETE FROM Rol
        WHERE id_rol = p_id_rol;

        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
END eliminar_rol;

END pkg_rol;
/
