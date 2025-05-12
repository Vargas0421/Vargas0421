/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.centroAcademico.SistemaAcademico.Domain.Horarios;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface HorariosDao  extends JpaRepository<Horarios,Long> {
    
     @Procedure(name = "obtener_horarios")
    List<Horarios> getHorarios();

    @Procedure(name = "obtener_horario_por_id")
    List<Horarios> getHorario(@Param("p_id_horario") Long idHorario);

    @Procedure(name = "insertar_horario")
    void insertarHorario(
        @Param("p_id_profesor") Long idProfesor,
        @Param("p_id_materia") Long idMateria,
        @Param("p_dia_semana") String diaSemana,
        @Param("p_horario_inc") Date horarioInc,
        @Param("p_horario_fin") Date horarioFin
    );

    @Procedure(name = "actualizar_horario")
    void actualizarHorario(
        @Param("p_id_horario") Long idHorario,
        @Param("p_id_profesor") Long idProfesor,
        @Param("p_id_materia") Long idMateria,
        @Param("p_dia_semana") String diaSemana,
        @Param("p_horario_inc") Date horarioInc,
        @Param("p_horario_fin") Date horarioFin
    );

    @Procedure(name = "eliminar_horario")
    void eliminarHorario(@Param("p_id_horario") Long idHorario);
}
