/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Service;

import java.util.List;
import com.centroAcademico.SistemaAcademico.Domain.Materias;


public interface MateriasService {
    
     //Se Obtiene los registros de la tabla materias en un ArrayList
    //de objetos materias, todos o solo los activos
    public List<Materias> getMaterias();
        
    
    //Recupera el ristro de la tabla materias en un objeto materias
    //si el idmaterias existe... sino devuelve null
    public Materias getMateria(Materias materia);
    
    //Actualiza un registro en la tabla materias si el idmaterias existe
    //Crea un registro en la tabla materias si  idmaterias no tiene valor
    public void insertarMateria (Materias materia);
    
    public void actualizarMateria (Materias materia);
    
    //Elimina el registro de la tabla materias si idmaterias eciste en la tabla
    public void eliminar(Materias materias);


    
}
    

