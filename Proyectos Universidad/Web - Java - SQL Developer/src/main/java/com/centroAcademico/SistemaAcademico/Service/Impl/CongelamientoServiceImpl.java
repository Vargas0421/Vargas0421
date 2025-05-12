package com.centroAcademico.SistemaAcademico.Service.Impl;

import com.centroAcademico.SistemaAcademico.Domain.Congelamiento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.centroAcademico.SistemaAcademico.Dao.CongelamientoDao;
import com.centroAcademico.SistemaAcademico.Service.CongelamientoService;

@Service
public class CongelamientoServiceImpl implements CongelamientoService {

    @Autowired
    private CongelamientoDao congelamientosDao;

    @Override
    @Transactional(readOnly = true)
    public List<Congelamiento> obtenerCongelamientos() {
        return congelamientosDao.obtenerCongelamientos();
    }

    @Override
    @Transactional(readOnly = true)
    public Congelamiento obtenerCongelamientoPorId(Congelamiento congelamiento) {
        List<Congelamiento> congelamientos = congelamientosDao.obtenerCongelamientoPorId(congelamiento.getIdCongelamiento());
        return congelamientos.isEmpty() ? null : congelamientos.get(0);
    }

    @Override
    @Transactional
    public void insertarCongelamiento(Congelamiento congelamientos) {
        congelamientosDao.insertarCongelamiento(
                congelamientos.getEstudiante().getIdEstudiante(),
                congelamientos.getFechaComplemento());
    }

    @Override
    @Transactional
    public void actualizarCongelamiento(Congelamiento congelamientos) {
        congelamientosDao.actualizarCongelamiento(
                congelamientos.getIdCongelamiento(),
                congelamientos.getEstudiante().getIdEstudiante(),
                congelamientos.getFechaComplemento());
    }

    // Nuevo método para crear un congelamiento usando el procedimiento almacenado
    

    @Override
    @Transactional
    public void eliminarCongelamiento(Congelamiento congelamientos) {
        congelamientosDao.eliminarCongelamiento(congelamientos.getIdCongelamiento());
    }

    

    
   
}
