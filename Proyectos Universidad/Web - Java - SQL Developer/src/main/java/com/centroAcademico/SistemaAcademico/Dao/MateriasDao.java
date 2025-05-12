/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.centroAcademico.SistemaAcademico.Domain.Materias;
import java.util.List;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface MateriasDao extends JpaRepository<Materias, Long> {

    @Procedure(name = "obtener_materias")
    List<Materias> getMaterias();

    @Procedure(name = "obtener_materia_por_id")
    List<Materias> getMateria(@Param("p_id_materia") Long idMateria);

    @Procedure(name = "insertar_materia")
    void insertarMateria(
            @Param("p_codigo_materia") String codigoMateria,
            @Param("p_nombre_materia") String nombreMateria,
            @Param("p_descripcion") String descripcion
    );

    @Procedure(name = "actualizar_materia")
    void actualizarMateria(
            @Param("p_id_materia") Long idMateria,
            @Param("p_codigo_materia") String codigoMateria,
            @Param("p_nombre_materia") String nombreMateria,
            @Param("p_descripcion") String descripcion
    );

    @Procedure(name = "eliminar_materia")
    void eliminarMateria(@Param("p_id_materia") Long idMateria);
}
