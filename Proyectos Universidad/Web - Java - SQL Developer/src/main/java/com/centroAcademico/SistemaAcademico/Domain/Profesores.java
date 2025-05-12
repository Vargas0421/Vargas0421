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
    name = "obtener_profesores",
    procedureName = "pkg_profesores.obtener_profesores",
    resultClasses = Profesores.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
)

@NamedStoredProcedureQuery(
    name = "obtener_profesor_por_id",
    procedureName = "pkg_profesores.obtener_profesor_por_id",
    resultClasses = Profesores.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_profesor", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
)

@NamedStoredProcedureQuery(
    name = "insertar_profesor",
    procedureName = "pkg_profesores.insertar_profesor",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cedula", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_apellido", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_codigo_profesor", type = String.class)
    }
)

@NamedStoredProcedureQuery(
    name = "actualizar_profesor",
    procedureName = "pkg_profesores.actualizar_profesor",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_profesor", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cedula_profesor", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_apellido", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_codigo_profesor", type = String.class)
    }
)

@NamedStoredProcedureQuery(
    name = "eliminar_profesor",
    procedureName = "pkg_profesores.eliminar_profesor",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_profesor", type = Integer.class)
    }
)

@Data

@Table(name="profesores")
public class Profesores implements Serializable {
    
    private static final long serialVersionUID = 1l;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_profesor")
    private Long idProfesor;
    @Column(name = "cedula_profesor")
    private String cedulaProfesor;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "codigo_profesor")
    private String codigoProfesor;
    
    
    @OneToMany(mappedBy = "profesor")
    private List<Horarios> horarios;
    
    @OneToMany(mappedBy = "profesor")
    private List<Notas> notas;

  
     
   
    
}
