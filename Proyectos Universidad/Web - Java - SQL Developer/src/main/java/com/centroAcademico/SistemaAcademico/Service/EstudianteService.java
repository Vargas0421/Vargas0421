/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Service;

import java.util.List;
import com.centroAcademico.SistemaAcademico.Domain.Estudiante;


public interface EstudianteService {
    
     //Se Obtiene los registros de la tabla estudiante en un ArrayList
    //de objetos estudiante, todos o solo los activos
    public List<Estudiante> obtenerEstudiantes();
        
    
    //Recupera el ristro de la tabla estudiante en un objeto estudiante
    //si el idestudiante existe... sino devuelve null
    public Estudiante obtenerEstudiantePorId(Estudiante estudiante);
    
    //Actualiza un registro en la tabla estudiante si el idestudiante existe
    //Crea un registro en la tabla estudiante si  idestudiante no tiene valor
    public void insertarEstudiante (Estudiante estudiante);
    
    public void actualizarEstudiante(Estudiante estudiante);
    
    //Elimina el registro de la tabla estudiante si idestudiante eciste en la tabla
    public void delete(Estudiante estudiante);
    
}
    

