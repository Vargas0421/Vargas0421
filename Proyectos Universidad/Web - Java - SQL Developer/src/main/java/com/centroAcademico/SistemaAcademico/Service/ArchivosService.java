package com.centroAcademico.SistemaAcademico.Service;

import java.util.List;
import com.centroAcademico.SistemaAcademico.Domain.Archivos;

public interface ArchivosService {

    // Se Obtiene los registros de la tabla materias en un ArrayList
    // de objetos materias, todos o solo los activos
    public List<Archivos> getArchivos();

    // Recupera el ristro de la tabla materias en un objeto materias
    // si el idmaterias existe... sino devuelve null
    public Archivos getArchivo(Archivos archivo);

    // Actualiza un registro en la tabla materias si el idmaterias existe
    // Crea un registro en la tabla materias si idmaterias no tiene valor
    public void insertarArchivo(Archivos archivos);
    
    public void actualizarArchivo(Archivos archivo);

    // Elimina el registro de la tabla materias si idmaterias eciste en la tabla
    public void delete(Archivos archivos);


}
