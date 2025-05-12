package com.centroAcademico.SistemaAcademico.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import com.centroAcademico.SistemaAcademico.Domain.Congelamiento;
import java.util.Date;
import java.util.List;

public interface CongelamientoDao extends JpaRepository<Congelamiento, Long> {

     @Procedure(name = "insertar_congelamiento")
    void insertarCongelamiento(
            @Param("p_id_estudiante") Long idEstudiante,
            @Param("p_fecha_complemento") Date fechaComplemento
    );

    @Procedure(name = "actualizar_congelamiento")
    void actualizarCongelamiento(
            @Param("p_id_congelamiento") Long idCongelamiento,
            @Param("p_id_estudiante") Long idEstudiante,
            @Param("p_fecha_complemento") Date fechaComplemento
    );

    @Procedure(name = "eliminar_congelamiento")
    void eliminarCongelamiento(
            @Param("p_id_congelamiento") Long idCongelamiento
    );

    @Procedure(name = "obtener_congelamiento_por_id")
    List<Congelamiento> obtenerCongelamientoPorId(
            @Param("p_id_congelamiento") Long idCongelamiento
    );

    @Procedure(name = "obtener_congelamientos")
    List<Congelamiento> obtenerCongelamientos();
}
