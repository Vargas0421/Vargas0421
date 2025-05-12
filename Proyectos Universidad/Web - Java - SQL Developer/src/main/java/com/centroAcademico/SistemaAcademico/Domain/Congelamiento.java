package com.centroAcademico.SistemaAcademico.Domain;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@NamedStoredProcedureQuery(
    name = "obtener_congelamientos",
    procedureName = "pkg_congelamiento.obtener_congelamientos",
    resultClasses = Congelamiento.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
)

@NamedStoredProcedureQuery(
    name = "obtener_congelamiento_por_id",
    procedureName = "pkg_congelamiento.obtener_congelamieno_por_id",
    resultClasses = Congelamiento.class,
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_congelamiento", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_result", type = void.class)
    }
)

@NamedStoredProcedureQuery(
    name = "insertar_congelamiento",
    procedureName = "pkg_congelamiento.insertar_congelamiento",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_estudiante", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fecha_complemento", type = Date.class)
    }
)

@NamedStoredProcedureQuery(
    name = "actualizar_congelamiento",
    procedureName = "pkg_congelamiento.actualizar_congelamiento",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_congelamiento", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_estudiante", type = Long.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fecha_complemento", type = Date.class)
    }
)

@NamedStoredProcedureQuery(
    name = "eliminar_congelamiento",
    procedureName = "pkg_congelamiento.eliminar_congelamiento",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_id_congelamiento", type = Long.class)
    }
)




@Data
@Table(name = "congelamiento")
public class Congelamiento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_congelamiento")
    private Long idCongelamiento;
   
    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "fecha_complemento", nullable = false)
    private Date fechaComplemento;
    
    //Pruebas por error de compilación - Anthony
    
}
