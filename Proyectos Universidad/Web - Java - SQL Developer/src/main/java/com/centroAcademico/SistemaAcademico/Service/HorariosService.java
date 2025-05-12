/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Service;

import java.util.List;
import com.centroAcademico.SistemaAcademico.Domain.Horarios;


public interface HorariosService {
    
     //Se Obtiene los registros de la tabla horarios en un ArrayList
    //de objetos horarios, todos o solo los activos
    public List<Horarios> getHorarios();
        
    
    //Recupera el ristro de la tabla horarios en un objeto horarios
    //si el idhorarios existe... sino devuelve null
    public Horarios getHorario(Horarios horario);
    
    //Actualiza un registro en la tabla horarios si el idhorarios existe
    //Crea un registro en la tabla horarios si  idhorarios no tiene valor
    public void insertarHorario (Horarios horarios);
    
    public void actualizarHorario (Horarios horarios);
    
    //Elimina el registro de la tabla horarios si idhorarios eciste en la tabla
    public void eliminarHorario(Horarios horarios);
    
}
    

