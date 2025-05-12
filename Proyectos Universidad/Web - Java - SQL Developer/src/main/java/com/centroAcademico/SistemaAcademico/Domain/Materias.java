/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Domain;

import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedStoredProcedureQuery(
    name = "obtener_materias",
    procedureName = "pkg_materias.obtener_materias",
    resultClasses = Materias.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
)

@NamedStoredProcedureQuery(
    name = "obtener_materia_por_id",
    procedureName = "pkg_materias.obtener_materia_por_id",
    resultClasses = Materias.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_materia", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
)

@NamedStoredProcedureQuery(
    name = "insertar_materia",
    procedureName = "pkg_materias.insertar_materia",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_codigo_materia", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre_materia", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_descripcion", type = String.class)
        
    }
)

@NamedStoredProcedureQuery(
    name = "actualizar_materia",
    procedureName = "pkg_materias.actualizar_materia",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_materia", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_codigo_materia", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre_materia", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_descripcion", type = String.class)
    }
)

@NamedStoredProcedureQuery(
    name = "eliminar_materia",
    procedureName = "pkg_materias.eliminar_materia",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_materia", type = Integer.class)
    }
)



@Data

@Table(name = "materias")
public class Materias implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Long idMateria;
    @Column(name = "nombre_materia")
    private String nombreMateria;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "codigo_materia")
    private String codigoMateria;

    @OneToMany(mappedBy = "materia")
    private List<Horarios> horarios;
    
    @OneToMany(mappedBy = "materia")
    private List<Notas> notas;
    
    
     
}
