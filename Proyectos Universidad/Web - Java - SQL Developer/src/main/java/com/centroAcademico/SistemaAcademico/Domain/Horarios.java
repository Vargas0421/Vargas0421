/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Domain;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "obtener_horarios",
        procedureName = "pkg_horarios.obtener_horarios",
        resultClasses = Horarios.class,
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
        }
    ),
    @NamedStoredProcedureQuery(
        name = "obtener_horario_por_id",
        procedureName = "pkg_horarios.obtener_horario_por_id",
        resultClasses = Horarios.class,
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_horario", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
        }
    ),
    @NamedStoredProcedureQuery(
        name = "insertar_horario",
        procedureName = "pkg_horarios.insertar_horario",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_profesor", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_materia", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dia_semana", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_horario_inc", type = Date.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_horario_fin", type = Date.class)
        }
    ),
    @NamedStoredProcedureQuery(
        name = "actualizar_horario",
        procedureName = "pkg_horarios.actualizar_horario",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_horario", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_profesor", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_materia", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_dia_semana", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_horario_inc", type = Date.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_horario_fin", type = Date.class)
        }
    ),
    @NamedStoredProcedureQuery(
        name = "eliminar_horario",
        procedureName = "pkg_horarios.eliminar_horario",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_horario", type = Integer.class)
        }
    )
})

@Data
@Table(name = "horarios")
public class Horarios implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    private Long idHorario;
    
    
    @Column(name = "dia_Semana")
    private String diaSemana;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "horario_Inc")
    private Date horarioInc;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "horario_Fin")
    private Date horarioFin;
//
//    @Column(name = "id_profesor")
//    private Long idProfesor;
//
//    @Column(name = "id_materia")
//    private Long idMateria;

    @ManyToOne
    @JoinColumn(name = "id_profesor", nullable = true)
    private Profesores profesor;
    
    @ManyToOne
    @JoinColumn(name = "id_materia", nullable = true)
    private Materias materia;
    
    
    
     
}
