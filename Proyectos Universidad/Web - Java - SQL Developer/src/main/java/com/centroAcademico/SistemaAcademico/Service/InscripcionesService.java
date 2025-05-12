package com.centroAcademico.SistemaAcademico.Service;

import com.centroAcademico.SistemaAcademico.Domain.Inscripciones;
import java.util.List;

public interface InscripcionesService {
    
    // Se Obtiene los registros de la tabla 
    public List<Inscripciones> getInscripciones();

    // Recupera el ristro de la tabla
    public Inscripciones getInscripcion(Inscripciones inscripciones);

    // Actualiza un registro en la tabla
    // Crea un registro en la tabla
    //public void save(Inscripciones inscripciones);

    public void insertarInscripcion(Inscripciones inscripciones);
    
    public void actualizarInscripcion(Inscripciones inscripciones);
    
    // Elimina el registro de la tabla
    public void eliminar(Inscripciones inscripciones);
    
}
