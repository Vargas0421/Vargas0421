package com.centroAcademico.SistemaAcademico.Domain;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

import org.aspectj.apache.bcel.generic.LOOKUPSWITCH;


@Entity
@NamedStoredProcedureQuery ( // se definen los procedimientos almacenados que se van a utilizar 
    name = "obtener_archivos",// es que se llama no necesariamente igual al nombre del procedimiento almacenado enm la db 
    procedureName = "pkg_archivos.obtener_archivos",// este es el que esta en la db
    resultClasses = Archivos.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
    )
@NamedStoredProcedureQuery(
    name = "obtener_archivo_por_id",
    procedureName = "pkg_archivos.obtener_archivo_por_id",
    resultClasses = Archivos.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_archivo", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
)
@NamedStoredProcedureQuery(
    name = "insertar_archivo",
    procedureName = "pkg_archivos.insertar_archivo",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_estudiante", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_tipo_documento", type = String.class)
    }
)
@NamedStoredProcedureQuery(
    name = "actualizar_archivo",
    procedureName = "pkg_archivos.actualizar_archivo",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_archivo", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_estudiante", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_tipo_documento", type = String.class)
    }
)
@NamedStoredProcedureQuery(
    name = "eliminar_archivo",
    procedureName = "pkg_archivos.eliminar_archivo",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_archivo", type = Long.class)
    }
)


@Data
@Table(name = "archivos")
public class Archivos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo")
    private Long idArchivo;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    @Column(name = "tipo_Documento")
    private String tipoDocumento;
    
    
}
