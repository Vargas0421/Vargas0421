/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Service;

import java.util.List;
import com.centroAcademico.SistemaAcademico.Domain.Rol;


public interface RolService {
    
     //Se Obtiene los registros de la tabla rol en un ArrayList
    //de objetos rol, todos o solo los activos
    public List<Rol> getRoles();
        
    
    //Recupera el ristro de la tabla rol en un objeto rol
    //si el idrol existe... sino devuelve null
    public Rol getRol(Rol rol);
    
    //Actualiza un registro en la tabla rol si el idrol existe
    //Crea un registro en la tabla rol si  idrol no tiene valor
    public void insertarRol (Rol rol);
    
    public void actualizarRol (Rol rol);
    
    //Elimina el registro de la tabla rol si idrol eciste en la tabla
    public void eliminarRol(Rol rol);
    
}
    

