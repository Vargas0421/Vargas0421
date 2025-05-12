package com.centroAcademico.SistemaAcademico.Dao;

import com.centroAcademico.SistemaAcademico.Domain.Clases;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ClasesDao extends JpaRepository<Clases, Long>{
    
    @Procedure(name = "obtener_clases")
    List<Clases> obtenerClases();

    @Procedure(name = "obtener_clase_por_id")
    List<Clases> obtenerClasePorId(@Param("p_id_clase") Long idClase);

    @Procedure(name = "insertar_clase")
    void insertarClase(
        @Param("p_id_horario") Long idHorario,
        @Param("p_id_profesor") Long idProfesor,
        @Param("p_id_materia") Long idMateria,
        @Param("p_id_aula") Long idAula
    );

    @Procedure(name = "actualizar_clase")
    void actualizarClase(
        @Param("p_id_clase") Long idClase,
        @Param("p_id_horario") Long idHorario,
        @Param("p_id_profesor") Long idProfesor,
        @Param("p_id_materia") Long idMateria,
        @Param("p_id_aula") Long idAula
    );

    @Procedure(name = "eliminar_clase")
    void eliminarClase(@Param("p_id_clase") Long idClase);
    
}
