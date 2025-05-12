package com.centroAcademico.SistemaAcademico.Service;

import java.util.List;
import com.centroAcademico.SistemaAcademico.Domain.Notas;

public interface NotasService {

    // Se Obtiene los registros de la tabla materias en un ArrayList
    // de objetos materias, todos o solo los activos
    public List<Notas> obtenerNotas();

    // Recupera el ristro de la tabla materias en un objeto materias
    // si el idmaterias existe... sino devuelve null
    public Notas getNotaPorId(Notas nota);

    // Actualiza un registro en la tabla materias si el idmaterias existe
    // Crea un registro en la tabla materias si idmaterias no tiene valor
    public void insertarNota(Notas nota);
    
    public void actualizarNota(Notas nota);

    // Elimina el registro de la tabla materias si idmaterias eciste en la tabla
    public void delete(Notas nota);

}
