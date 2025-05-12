/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.centroAcademico.SistemaAcademico.Domain.Rol;
import java.util.List;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface RolDao  extends JpaRepository<Rol,Long> {
    
    
     @Procedure(name = "insertar_rol")
    void insertarRol(
        @Param("p_nombre") String nombreRol,
        @Param("p_id_usuario") Long idUsuario
    );
    
     @Procedure(name = "obtener_roles")
    List<Rol> getRoles();
    
     @Procedure(name = "obtener_rol_por_id")
    List<Rol> getRol(@Param("p_id_rol") Long idRol);
    
    @Procedure(name = "actualizar_rol")
    void actualizarRol(
        @Param("p_id_rol") Long idRol,
        @Param("p_nombre") String nombreRol,
        @Param("p_id_usuario") Long idUsuario
    );
    
    
    @Procedure(name = "eliminar_rol")
    void eliminarRol(@Param("p_id_rol") Long idRol);
    
}
