package com.centroAcademico.SistemaAcademico.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.centroAcademico.SistemaAcademico.Domain.Notas;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface NotasDao extends JpaRepository<Notas, Long> {

    @Procedure(name = "obtener_notas")
    List<Notas> obtenerNotas();
    
   @Procedure(name = "insertar_nota")
   List<Notas> insertarNotas(
        @Param("p_id_profesor") Long idProfesor,
        @Param("p_id_materia") Long idMateria, // Cambiado de Double a Long
        @Param("p_id_estudiante") Long idEstudiante, // Cambiado de Double a Long
        @Param("p_calificacion") BigDecimal calificacion
    );

    @Procedure(name = "actualizar_nota")
    void actualizarNotas(
        @Param("p_id_nota") Long idNota,
        @Param("p_id_profesor") Long idProfesor,
        @Param("p_id_materia") Long idMateria, // Cambiado de Double a Long
        @Param("p_id_estudiante") Long idEstudiante, // Cambiado de Double a Long
        @Param("p_calificacion") BigDecimal calificacion
    );

    @Procedure(name = "eliminar_nota")
    void eliminarNotas(@Param("p_id_nota") Long idNota); // Corregido el nombre del parámetro

    @Procedure(name = "obtener_nota_por_id")
    List<Notas> getNotaPorId(@Param("p_id_nota") Long idNota);
}