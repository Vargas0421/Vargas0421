package com.centroAcademico.SistemaAcademico.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.centroAcademico.SistemaAcademico.Domain.Aulas;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AulasDao extends JpaRepository<Aulas, Long>{
    
    @Procedure(name = "insertar_aula")
    void insertarAula(
            @Param("p_numero_Aula") String numeroAula,
            @Param("p_capacidad") Integer capacidad
    );

    @Procedure(name = "actualizar_aula")
    void actualizarAula(
            @Param("p_id_aula") Long idAula,
            @Param("p_numero_Aula") String numeroAula,
            @Param("p_capacidad") Integer capacidad
    );

    @Procedure(name = "eliminar_aula")
    void eliminar(@Param("p_id_aula") Long idAula);

    @Procedure(name = "obtener_aulas")
    List<Aulas> obtenerAulas();
    
    @Procedure(name = "obtener_aulas_por_id")
    List<Aulas> obtenerAulaPorId(@Param("p_id_aula") Long idAula);

}
