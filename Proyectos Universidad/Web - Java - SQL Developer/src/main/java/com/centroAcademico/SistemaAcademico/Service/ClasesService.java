package com.centroAcademico.SistemaAcademico.Service;

import com.centroAcademico.SistemaAcademico.Domain.Clases;
import java.util.List;


public interface ClasesService {
    
    // Se Obtiene los registros de la tabla 
    public List<Clases> obtenerClases();

    // Recupera el ristro de la tabla
    public Clases obtenerClasePorId(Clases clases);

    // Actualiza un registro en la tabla
    // Crea un registro en la tabla
    public void insertarClase(Clases clases);
    
    public void actualizarClase(Clases clases);

    // Elimina el registro de la tabla
    public void delete(Clases clases);
}
