/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Service.Impl;


import com.centroAcademico.SistemaAcademico.Domain.Profesores;

import com.centroAcademico.SistemaAcademico.Service.ProfesoresService;
import com.centroAcademico.SistemaAcademico.Dao.ProfesoresDao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfesoresServiceImpl implements ProfesoresService {

     @Autowired
    private ProfesoresDao profesoresDao;
     
    @Override
    @Transactional(readOnly=true)
    public List<Profesores> getProfesores() {
        var lista = profesoresDao.getProfesores();
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Profesores getProfesor(Profesores profesor) {
       List<Profesores> profesores = profesoresDao.getProfesor(profesor.getIdProfesor());
        return profesores.isEmpty() ? null : profesores.get(0);
    }

    @Override
    @Transactional
    public void insertarProfesor(Profesores profesor) {
        profesoresDao.insertarProfesor(
                profesor.getCedulaProfesor(),
                profesor.getNombre(),
                profesor.getApellido(),
                profesor.getCodigoProfesor()
                );
    }
    
    @Override
    @Transactional
    public void actualizarProfesor(Profesores profesor) {
        profesoresDao.actualizarProfesor(
                profesor.getIdProfesor(),
                profesor.getCedulaProfesor(),
                profesor.getNombre(),
                profesor.getApellido(),
                profesor.getCodigoProfesor()
                );
    }

    @Override
    @Transactional
    public void eliminar(Profesores profesores) {
        profesoresDao.eliminar(profesores.getIdProfesor());
    }
    
    
}
