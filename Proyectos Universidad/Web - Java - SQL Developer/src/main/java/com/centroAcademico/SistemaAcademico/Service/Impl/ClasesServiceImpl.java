package com.centroAcademico.SistemaAcademico.Service.Impl;

import com.centroAcademico.SistemaAcademico.Dao.ClasesDao;
import com.centroAcademico.SistemaAcademico.Domain.Clases;
import com.centroAcademico.SistemaAcademico.Service.ClasesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClasesServiceImpl implements ClasesService {

    @Autowired
    private ClasesDao clasesDao;

    @Override
    @Transactional(readOnly = true)
    public List<Clases> obtenerClases() {
        var lista = clasesDao.obtenerClases();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Clases obtenerClasePorId(Clases clase) {
        List<Clases> clases = clasesDao.obtenerClasePorId(clase.getIdClase());
        return clases.isEmpty() ? null : clases.get(0);
    }

    @Override
    @Transactional
    public void insertarClase(Clases clase) {
        clasesDao.insertarClase(
                clase.getHorario().getIdHorario(),
                clase.getProfesor().getIdProfesor(),
                clase.getMateria().getIdMateria(),
                clase.getAula().getIdAula()
        );
                
                
        
    }

    @Override
    @Transactional
    public void actualizarClase(Clases clase) {
        clasesDao.actualizarClase(
                clase.getIdClase(),
                clase.getHorario().getIdHorario(),
                clase.getProfesor().getIdProfesor(),
                clase.getMateria().getIdMateria(),
                clase.getAula().getIdAula()
        );
    }

    @Override
    @Transactional
    public void delete(Clases clase) {
        clasesDao.eliminarClase(clase.getIdClase());
    }
}
