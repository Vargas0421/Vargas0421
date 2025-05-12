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
@NamedStoredProcedureQuery( // se definen los procedimientos almacenados que se van a utilizar 
    name = "insertar_usuario",// es que se llama no necesariamente igual al nombre del procedimiento almacenado enm la db 
    procedureName = "pkg_usuarios.insertar_usuario",// este es el que esta en la db 
    parameters = { // estos son los parametros que recibe el procedimiento almacenado
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_username", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_apellidos", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_correo", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_activo", type = String.class)
    }
)
@NamedStoredProcedureQuery(
    name = "obtener_usuarios",
    procedureName = "pkg_usuarios.obtener_usuarios",
    resultClasses = Usuario.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
)
@NamedStoredProcedureQuery(
    name = "obtener_usuario_por_id",
    procedureName = "pkg_usuarios.obtener_usuario_por_id",
    resultClasses = Usuario.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_usuario", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
)
@NamedStoredProcedureQuery(
    name = "actualizar_usuario",
    procedureName = "pkg_usuarios.actualizar_usuario",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_usuario", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_username", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_apellidos", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_correo", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_activo", type = String.class)
    }
)
@NamedStoredProcedureQuery(
    name = "eliminar_usuario",
    procedureName = "pkg_usuarios.eliminar_usuario",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_usuario", type = Integer.class)
    }
)



@Data
@Table(name = "usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")

    private Long idUsuario;// se crean los atributos que se van a utilizar en la tabla de la base de datos
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "correo")
    private String correo;  
    @Column(name = "activo")
    private String activo;

    @OneToMany(mappedBy = "usuario") 
    private List<Rol> roles;
     
    
}
