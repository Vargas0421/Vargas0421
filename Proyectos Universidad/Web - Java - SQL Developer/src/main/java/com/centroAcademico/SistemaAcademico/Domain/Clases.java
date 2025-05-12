package com.centroAcademico.SistemaAcademico.Domain;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NamedStoredProcedureQuery(
    name = "obtener_clases",
    procedureName = "pkg_clases.obtener_clases",
    resultClasses = Clases.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
)
@NamedStoredProcedureQuery(
    name = "obtener_clase_por_id",
    procedureName = "pkg_clases.obtener_clase_por_id",
    resultClasses = Clases.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_clase", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
)
@NamedStoredProcedureQuery(
    name = "insertar_clase",
    procedureName = "pkg_clases.insertar_clase",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_horario", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_profesor", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_materia", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_aula", type = Long.class)
    }
)
@NamedStoredProcedureQuery(
    name = "actualizar_clase",
    procedureName = "pkg_clases.actualizar_clase",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_clase", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_horario", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_profesor", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_materia", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_aula", type = Long.class)
    }
)
@NamedStoredProcedureQuery(
    name = "eliminar_clase",
    procedureName = "pkg_clases.eliminar_clase",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_clase", type = Long.class)
    }
)

@Data
@Table(name = "clases")
public class Clases implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clase")
    private Long idClase;
    
    @ManyToOne
    @JoinColumn(name = "id_horario", nullable = true)
    private Horarios horario;
    
    @ManyToOne
    @JoinColumn(name = "id_profesor", nullable = true)
    private Profesores profesor;
    
    @ManyToOne
    @JoinColumn(name = "id_materia", nullable = false)
    private Materias materia;
    
    @ManyToOne
    @JoinColumn(name = "id_aula", nullable = false)
    private Aulas aula;
    
    //Pruebas por error de compilación - Anthony
    
}
