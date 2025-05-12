/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Service.Impl;

import com.centroAcademico.SistemaAcademico.Domain.Estudiante;

import com.centroAcademico.SistemaAcademico.Service.EstudianteService;
import com.centroAcademico.SistemaAcademico.Dao.EstudianteDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteDao estudianteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Estudiante> obtenerEstudiantes() {
        var lista = estudianteDao.getEstudiantes();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Estudiante obtenerEstudiantePorId(Estudiante estudiante) {
        List<Estudiante> estudiantes = estudianteDao.obtenerEstudiante(estudiante.getIdEstudiante());
        return estudiantes.isEmpty() ? null : estudiantes.get(0);
    }

    @Override
    @Transactional
    public void insertarEstudiante(Estudiante estudiante) {
        estudianteDao.insertarEstudiante(
                estudiante.getCedulaEstudiante(),
                estudiante.getNombre(),
                estudiante.getApellido(),
                estudiante.getDireccion(),
                estudiante.getTelefono(),
                estudiante.getCorreoElectronico(),
                estudiante.getCodigoEstudiante()
        );
    }

    @Override
    @Transactional
    public void actualizarEstudiante(Estudiante estudiante) {
        estudianteDao.actualizarEstudiante(
                estudiante.getIdEstudiante(),
                estudiante.getCedulaEstudiante(),
                estudiante.getNombre(),
                estudiante.getApellido(),
                estudiante.getDireccion(),
                estudiante.getTelefono(),
                estudiante.getCorreoElectronico(),
                estudiante.getCodigoEstudiante()
        );
    }

    @Override
    @Transactional
    public void delete(Estudiante estudiante) {
        estudianteDao.eliminarEstudiante(estudiante.getIdEstudiante());
    }

}
