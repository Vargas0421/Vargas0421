package com.centroAcademico.SistemaAcademico.Dao;

import com.centroAcademico.SistemaAcademico.Domain.Inscripciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface InscripcionesDao extends JpaRepository<Inscripciones, Long>{
    @Procedure(name = "insertar_inscripcion")
    void insertarInscripcion(
            @Param("p_id_materia") Long materia,
            @Param("p_id_estudiante") Long estudiante
    );

    @Procedure(name = "actualizar_inscripcion")
    void actualizarInscripcion(
            @Param("p_id_inscripcion") Long idInscripcion,
            @Param("p_id_materia") Long materia,
            @Param("p_id_estudiante") Long estudiante
    );

    @Procedure(name = "eliminar_inscripcion")
    void eliminar(@Param("p_id_inscripcion") Long idInscripcion);

    @Procedure(name = "obtener_inscripciones")
    List<Inscripciones> obtenerInscripciones();
    
    @Procedure(name = "obtener_inscripcion_por_id")
    List<Inscripciones> obtenerInscripcionPorId(@Param("p_id_inscripcion") Long idInscripcion);
}
