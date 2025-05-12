/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.centroAcademico.SistemaAcademico.Domain.Estudiante;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EstudianteDao extends JpaRepository<Estudiante, Long> {

    @Procedure(name = "obtener_estudiantes")
    List<Estudiante> getEstudiantes();

   @Procedure(name = "obtener_estudiante_por_id")
    List<Estudiante> obtenerEstudiante(@Param("p_id_estudiante") Long idEstudiante);

    @Procedure(name = "insertar_estudiante")
    void insertarEstudiante(
        @Param("p_cedula") String cedula,
        @Param("p_nombre") String nombre,
        @Param("p_apellido") String apellido,
        @Param("p_direccion") String direccion,
        @Param("p_telefono") String telefono,
        @Param("p_correo") String correo,
        @Param("p_codigo_estudiante") String codigoEstudiante
    );
   
     @Procedure(name = "actualizar_estudiante")
    void actualizarEstudiante(
        @Param("p_id_estudiante") Long idEstudiante,
        @Param("p_cedula_estudiante") String cedulaEstudiante,
        @Param("p_nombre") String nombre,
        @Param("p_apellido") String apellido,
        @Param("p_direccion") String direccion,
        @Param("p_telefono") String telefono,
        @Param("p_correo_electronico") String correoElectronico,
        @Param("p_codigo_estudiante") String codigoEstudiante
    );
    
    @Procedure(name = "eliminar_estudiante")
    void eliminarEstudiante(@Param("p_id_estudiante") Long idEstudiante);
    
}
