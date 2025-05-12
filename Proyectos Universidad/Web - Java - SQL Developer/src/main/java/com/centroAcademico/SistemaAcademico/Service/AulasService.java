package com.centroAcademico.SistemaAcademico.Service;

import java.util.List;
import com.centroAcademico.SistemaAcademico.Domain.Aulas;
import org.springframework.stereotype.Service;

public interface AulasService {
    
    // Se Obtiene los registros de la tabla 
    public List<Aulas> getAulas();

    // Recupera el ristro de la tabla
    public Aulas getAula(Aulas aulas);

    // Actualiza un registro en la tabla
    // Crea un registro en la tabla
    //public void save(Aulas aulas);
    
    public void insertarAula(Aulas aulas);
    
    public void actualizarAula(Aulas aulas);

    // Elimina el registro de la tabla
    //public void delete(Aulas aulas);
    
    public void eliminar(Aulas aulas);

}
