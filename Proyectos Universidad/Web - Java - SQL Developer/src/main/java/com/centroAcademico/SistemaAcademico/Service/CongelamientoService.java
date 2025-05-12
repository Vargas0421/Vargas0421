package com.centroAcademico.SistemaAcademico.Service;

import java.util.List;
import com.centroAcademico.SistemaAcademico.Domain.Congelamiento;

public interface CongelamientoService {

    // Se Obtiene los registros de la tabla materias en un ArrayList
    // de objetos materias, todos o solo los activos
    public List<Congelamiento> obtenerCongelamientos();

    // Recupera el ristro de la tabla materias en un objeto materias
    // si el idmaterias existe... sino devuelve null
    public Congelamiento obtenerCongelamientoPorId(Congelamiento congelamiento);

    // Actualiza un registro en la tabla materias si el idmaterias existe
    // Crea un registro en la tabla materias si idmaterias no tiene valor
    public void insertarCongelamiento(Congelamiento congelamientos);
    
    public void actualizarCongelamiento(Congelamiento congelamientos);

    // Elimina el registro de la tabla materias si idmaterias eciste en la tabla
    public void eliminarCongelamiento(Congelamiento congelamientos);

}
