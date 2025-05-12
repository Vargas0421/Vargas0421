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
    name = "obtener_estudiantes",
    procedureName = "pkg_estudiante.obtener_estudiantes",
    resultClasses = Estudiante.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
)

@NamedStoredProcedureQuery(
    name = "obtener_estudiante_por_id",
    procedureName = "pkg_estudiante.obtener_estudiante_por_id",
    resultClasses = Estudiante.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_estudiante", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
)

@NamedStoredProcedureQuery(
    name = "insertar_estudiante",
    procedureName = "pkg_estudiante.insertar_estudiante",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cedula", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_apellido", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_direccion", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_telefono", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_correo", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_codigo_estudiante", type = String.class)
    }
)

@NamedStoredProcedureQuery(
    name = "actualizar_estudiante",
    procedureName = "pkg_estudiante.actualizar_estudiante",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_estudiante", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cedula_estudiante", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_apellido", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_direccion", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_telefono", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_correo_electronico", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_codigo_estudiante", type = String.class)
    }
)

@NamedStoredProcedureQuery(
    name = "eliminar_estudiante",
    procedureName = "pkg_estudiante.eliminar_estudiante",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_estudiante", type = Integer.class)
    }
)

@Data

@Table(name = "estudiantes")
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")

    private Long idEstudiante;

    @Column(name = "cedula_estudiante")
    private String cedulaEstudiante;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Column(name = "codigo_estudiante")
    private String codigoEstudiante;
    
     @OneToMany(mappedBy = "estudiante")
    private List<Congelamiento> congelamientos;
     
     @OneToMany(mappedBy = "estudiante")
    private List<Archivos> archivos;
     
     @OneToMany(mappedBy = "estudiante")
    private List<Notas> notas;
     
    @OneToMany(mappedBy = "estudiante")
    private List<Inscripciones> inscripciones;

    //public void setIdEstudiante(Long idEstudiante) {
       // this.idEstudiante = idEstudiante;
}
