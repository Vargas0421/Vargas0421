/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Service;

import java.util.List;
import com.centroAcademico.SistemaAcademico.Domain.Profesores;


public interface ProfesoresService {
    
     //Se Obtiene los registros de la tabla profesores en un ArrayList
    //de objetos profesores, todos o solo los activos
    public List<Profesores> getProfesores();
        
    
    //Recupera el ristro de la tabla profesores en un objeto profesores
    //si el idprofesores existe... sino devuelve null
    public Profesores getProfesor(Profesores profesor);
    
    //Actualiza un registro en la tabla profesores si el idprofesores existe
    //Crea un registro en la tabla profesores si  idprofesores no tiene valor
    public void insertarProfesor (Profesores profesor);
    
    public void actualizarProfesor (Profesores profesor);
    
    //Elimina el registro de la tabla profesores si idprofesores eciste en la tabla
    public void eliminar(Profesores profesor);


   
    
}
    

