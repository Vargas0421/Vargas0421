/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Service.Impl;


import com.centroAcademico.SistemaAcademico.Domain.Materias;

import com.centroAcademico.SistemaAcademico.Service.MateriasService;
import com.centroAcademico.SistemaAcademico.Dao.MateriasDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MateriasServiceImpl implements MateriasService {

     @Autowired
    private MateriasDao materiasDao;
     
    @Override
    @Transactional(readOnly=true)
    public List<Materias> getMaterias() {
        var lista = materiasDao.getMaterias();
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Materias getMateria(Materias materia) {
          List<Materias> materias = materiasDao.getMateria(materia.getIdMateria());
        return materias.isEmpty() ? null : materias.get(0);
    }

    @Override
    @Transactional
    public void insertarMateria(Materias materia) {
        materiasDao.insertarMateria(
                materia.getCodigoMateria(),
                materia.getNombreMateria(),
                materia.getDescripcion()
        );
    }

    
    @Override
    @Transactional
    public void actualizarMateria(Materias materia){
        materiasDao.actualizarMateria(materia.getIdMateria(),
                materia.getCodigoMateria(),
                materia.getNombreMateria(),
                materia.getDescripcion()
        );
    }
            
    @Override
    @Transactional
    public void eliminar(Materias materias) {
        materiasDao.eliminarMateria(materias.getIdMateria());
    }

  

   
    
    
}
