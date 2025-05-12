/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.centroAcademico.SistemaAcademico.Domain.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface UsuarioDao  extends JpaRepository<Usuario,Long> {
    
     public Usuario findByUsername(String username);
    
    public Usuario findByUsernameAndPassword(String username, String Password);
    
    public Usuario findByUsernameOrCorreo(String username, String correo);

    public boolean existsByUsernameOrCorreo(String username, String correo);
    
     @Procedure(name = "insertar_usuario") // se define el procedimiento almacenado que se va a utilizar (el nombre que se le da ebn el codigo no en la db)
    void insertarUsuario(
        @Param("p_username") String username, // y se le pasan los paremteros que recibe el procedimiento almacenado tal como la tabla de la db
        @Param("p_password") String password,
        @Param("p_nombre") String nombre,
        @Param("p_apellidos") String apellidos,
        @Param("p_correo") String correo,
        @Param("p_activo") String activo
    );

    @Procedure(name = "obtener_usuarios") 
    List<Usuario> getUsuarios();

    @Procedure(name = "obtener_usuario_por_id")
    List<Usuario> getUsuario(@Param("p_id_usuario") Long idUsuario);

    @Procedure(name = "actualizar_usuario")
    void actualizarUsuario(
        @Param("p_id_usuario") Long idUsuario,
        @Param("p_username") String username,
        @Param("p_nombre") String nombre,
        @Param("p_apellidos") String apellidos,
        @Param("p_correo") String correo,
        @Param("p_activo") String activo
    );

    @Procedure(name = "eliminar_usuario")
    void eliminarUsuario(@Param("p_id_usuario") Long idUsuario);
}
