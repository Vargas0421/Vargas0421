package com.centroAcademico.SistemaAcademico.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.centroAcademico.SistemaAcademico.Domain.Archivos;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ArchivosDao extends JpaRepository<Archivos, Long> {

    @Procedure(name = "insertar_archivo")
    void insertarArchivo(
            @Param("p_id_estudiante") Long idEstudiante,
            @Param("p_tipo_documento") String tipoDocumento
    );

    @Procedure(name = "obtener_archivo_por_id")
    List<Archivos> obtenerArchivoPorId(
            @Param("p_id_archivo") Long idArchivo
    );

    @Procedure(name = "actualizar_archivo")
    void actualizarArchivo(
            @Param("p_id_archivo") Long idArchivo,
            @Param("p_id_estudiante") Long idEstudiante,
            @Param("p_tipo_documento") String tipoDocumento
    );

    @Procedure(name = "eliminar_archivo")
    void eliminarArchivo(@Param("p_id_archivo") Long idArchivo);

    @Procedure(name = "obtener_archivos")
    List<Archivos> getArchivos();

}
