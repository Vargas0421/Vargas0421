package com.centroAcademico.SistemaAcademico.Domain;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@NamedStoredProcedureQuery ( // se definen los procedimientos almacenados que se van a utilizar 
    name = "obtener_inscripciones",// es que se llama no necesariamente igual al nombre del procedimiento almacenado enm la db 
    procedureName = "pkg_inscripciones.obtener_inscripciones",// este es el que esta en la db
    resultClasses = Inscripciones.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
    )

@NamedStoredProcedureQuery(
    name = "obtener_inscripcion_por_id",
    procedureName = "pkg_inscripciones.obtener_inscripcion_por_id",
    resultClasses = Inscripciones.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_inscripcion", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
)

@NamedStoredProcedureQuery(
    name = "insertar_inscripcion",
    procedureName = "pkg_inscripciones.insertar_inscripcion",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_materia", type = Long.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_estudiante", type = Long.class)
    }
)
@NamedStoredProcedureQuery(
    name = "actualizar_inscripcion",
    procedureName = "pkg_inscripciones.actualizar_inscripcion",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_inscripcion", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_materia", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_estudiante", type = Long.class)
    }
)
@NamedStoredProcedureQuery(
    name = "eliminar_inscripcion",
    procedureName = "pkg_inscripciones.eliminar_inscripcion",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_inscripcion", type = Long.class)
    }
)

@Data
@Table(name = "inscripciones")
public class Inscripciones implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscripcion")
    private Long idInscripcion;
    
    @ManyToOne
    @JoinColumn(name = "id_materia", nullable = false)
    private Materias materia;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;
    
    // Getters y Setters

//    public Long getIdInscripcion() {
//        return idInscripcion;
//    }
//
//    public void setIdInscripcion(Long idInscripcion) {
//        this.idInscripcion = idInscripcion;
//    }
//
//    public Materias getMateria() {
//        return materia;
//    }
//
//    public void setMateria(Materias materia) {
//        this.materia = materia;
//    }
//
//    public Estudiante getEstudiante() {
//        return estudiante;
//    }
//
//    public void setEstudiante(Estudiante estudiante) {
//        this.estudiante = estudiante;
//    }
    
   
    
}
