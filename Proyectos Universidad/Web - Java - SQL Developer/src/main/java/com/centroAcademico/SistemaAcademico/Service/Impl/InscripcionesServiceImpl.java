package com.centroAcademico.SistemaAcademico.Service.Impl;

import com.centroAcademico.SistemaAcademico.Dao.InscripcionesDao;
import com.centroAcademico.SistemaAcademico.Domain.Inscripciones;
import com.centroAcademico.SistemaAcademico.Service.InscripcionesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InscripcionesServiceImpl implements InscripcionesService{
    @Autowired
    private InscripcionesDao inscripcionesDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Inscripciones> getInscripciones() {
        //var lista = inscripcionesDao.findAll();
        var lista = inscripcionesDao.obtenerInscripciones();
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Inscripciones getInscripcion(Inscripciones inscripcion) {
        //return inscripcionesDao.findById(inscripciones.getIdInscripcion()).orElse(null);
        List<Inscripciones> inscripciones = inscripcionesDao.obtenerInscripcionPorId(inscripcion.getIdInscripcion());
        return inscripciones.isEmpty() ? null : inscripciones.get(0);
    }
    


    @Override
    @Transactional
    public void insertarInscripcion(Inscripciones inscripcion) {
        inscripcionesDao.insertarInscripcion(
                inscripcion.getMateria().getIdMateria(),
                inscripcion.getEstudiante().getIdEstudiante()
        );
    }
    
    @Override
    @Transactional
    public void actualizarInscripcion(Inscripciones inscripcion) {
        inscripcionesDao.actualizarInscripcion(
                inscripcion.getIdInscripcion(),
                inscripcion.getMateria().getIdMateria(),
                inscripcion.getEstudiante().getIdEstudiante()
                );
    }
    
    @Override
    @Transactional
    public void eliminar(Inscripciones inscripcion) {
        inscripcionesDao.eliminar(inscripcion.getIdInscripcion());
    }

/*
    @Override
    @Transactional
    public void save(Inscripciones clases) {
        inscripcionesDao.save(clases);
    }

    @Override
    @Transactional
    public void delete(Inscripciones clases) {
        inscripcionesDao.delete(clases);
    }
*/


}
