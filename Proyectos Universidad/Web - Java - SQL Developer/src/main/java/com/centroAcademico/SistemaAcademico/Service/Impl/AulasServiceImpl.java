package com.centroAcademico.SistemaAcademico.Service.Impl;

import com.centroAcademico.SistemaAcademico.Domain.Aulas;
import com.centroAcademico.SistemaAcademico.Service.AulasService;
import com.centroAcademico.SistemaAcademico.Dao.AulasDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AulasServiceImpl implements AulasService{
    
    @Autowired
    private AulasDao aulasDao;
     
    @Override
    @Transactional(readOnly=true)
    public List<Aulas> getAulas() {
        //var lista = aulasDao.findAll();
        var lista = aulasDao.obtenerAulas();
        return lista;
    }
    
    @Override
    @Transactional(readOnly=true)
    public Aulas getAula(Aulas aula) {
       List<Aulas> aulas = aulasDao.obtenerAulaPorId(aula.getIdAula());
        return aulas.isEmpty() ? null : aulas.get(0);
    }

    @Override
    @Transactional
    public void insertarAula(Aulas aulas) {
        aulasDao.insertarAula(
                aulas.getNumeroAula(),
                aulas.getCapacidad()
                );
    }
    
    @Override
    @Transactional
    public void actualizarAula(Aulas aulas) {
        aulasDao.actualizarAula(
                aulas.getIdAula(),
                aulas.getNumeroAula(),
                aulas.getCapacidad()
                );
    }
    
    @Override
    @Transactional
    public void eliminar(Aulas aulas) {
        aulasDao.eliminar(aulas.getIdAula());
    }

    /*@Override
    @Transactional
    public void save(Aulas aulas) {
        aulasDao.save(aulas);
    }

    @Override
    @Transactional
    public void delete(Aulas aulas) {
        aulasDao.delete(aulas);
    }*/
}
