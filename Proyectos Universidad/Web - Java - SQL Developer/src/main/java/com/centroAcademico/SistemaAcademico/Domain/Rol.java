/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Domain;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@NamedStoredProcedureQuery(
    name = "insertar_rol",
    procedureName = "pkg_rol.insertar_rol",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_usuario", type = Integer.class)
    }
)
@NamedStoredProcedureQuery(
    name = "obtener_roles",
    procedureName = "pkg_rol.obtener_roles",
    resultClasses = Rol.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
)
@NamedStoredProcedureQuery(
    name = "obtener_rol_por_id",
    procedureName = "pkg_rol.obtener_rol_por_id",
    resultClasses = Rol.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_rol", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
)
@NamedStoredProcedureQuery(
    name = "actualizar_rol",
    procedureName = "pkg_rol.actualizar_rol",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_rol", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_usuario", type = Integer.class)
    }
)
@NamedStoredProcedureQuery(
    name = "eliminar_rol",
    procedureName = "pkg_rol.eliminar_rol",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_rol", type = Integer.class)
    }
)





@Data
@Table(name = "rol")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;
    @Column(name = "nombre_Rol")
    private String nombreRol;
    //    @Column(name="id_usuario")
//    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = true)
    private Usuario usuario;
    
    
}
