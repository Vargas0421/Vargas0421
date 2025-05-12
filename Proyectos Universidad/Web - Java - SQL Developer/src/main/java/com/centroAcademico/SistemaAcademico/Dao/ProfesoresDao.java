/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.centroAcademico.SistemaAcademico.Domain.Profesores;
import java.util.List;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ProfesoresDao extends JpaRepository<Profesores, Long> {

    @Procedure(name = "obtener_profesores")
    List<Profesores> getProfesores();

    @Procedure(name = "obtener_profesor_por_id")
    List<Profesores> getProfesor(@Param("p_id_profesor") Long idProfesor);

    @Procedure(name = "insertar_profesor")
    void insertarProfesor(
            @Param("p_cedula_profesor") String cedulaProfesor,
            @Param("p_nombre") String nombre,
            @Param("p_apellido") String apellido,
            @Param("p_codigo_profesor") String codigoProfesor
    );

    @Procedure(name = "actualizar_profesor")
    void actualizarProfesor(
            @Param("p_id_profesor") Long idProfesor,
            @Param("p_cedula_profesor") String cedulaProfesor,
            @Param("p_nombre") String nombre,
            @Param("p_apellido") String apellido,
            @Param("p_codigo_profesor") String codigoProfesor
    );

    @Procedure(name = "eliminar_profesor")
    void eliminar(@Param("p_id_profesor") Long idProfesor);

}
