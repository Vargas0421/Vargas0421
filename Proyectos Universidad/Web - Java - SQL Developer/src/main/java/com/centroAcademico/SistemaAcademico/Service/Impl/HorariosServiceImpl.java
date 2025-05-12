/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Service.Impl;


import com.centroAcademico.SistemaAcademico.Domain.Horarios;

import com.centroAcademico.SistemaAcademico.Service.HorariosService;
import com.centroAcademico.SistemaAcademico.Dao.HorariosDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HorariosServiceImpl implements HorariosService {

     @Autowired
    private HorariosDao horariosDao;
     
    @Override
    @Transactional(readOnly=true)
    public List<Horarios> getHorarios() {
        var lista = horariosDao.getHorarios();
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Horarios getHorario(Horarios horario) {
        List<Horarios> horarios = horariosDao.getHorario(horario.getIdHorario());
        return horarios.isEmpty() ? null : horarios.get(0);
    }

    @Override
    @Transactional
    public void insertarHorario(Horarios horarios) {
        horariosDao.insertarHorario(
                 horarios.getProfesor().getIdProfesor()
                ,horarios.getMateria().getIdMateria()
                ,horarios.getDiaSemana()
                ,horarios.getHorarioInc()
                ,horarios.getHorarioFin());
    }
    
    @Override
    @Transactional
    public void actualizarHorario(Horarios horarios) {
        horariosDao.actualizarHorario(
                 horarios.getIdHorario()
                ,horarios.getProfesor().getIdProfesor()
                ,horarios.getMateria().getIdMateria()
                ,horarios.getDiaSemana()
                ,horarios.getHorarioInc()
                ,horarios.getHorarioFin()
        );
    }

    @Override
    @Transactional
    public void eliminarHorario(Horarios horarios) {
        horariosDao.eliminarHorario(horarios.getIdHorario());
    }
    
    
}
