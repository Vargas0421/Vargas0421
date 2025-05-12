/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Service.Impl;

import com.centroAcademico.SistemaAcademico.Domain.Archivos;

import com.centroAcademico.SistemaAcademico.Service.ArchivosService;
import com.centroAcademico.SistemaAcademico.Dao.ArchivosDao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArchivosServiceImpl implements ArchivosService {

    @Autowired
    private ArchivosDao archivosDao;

    @Override
    @Transactional(readOnly = true)
    public List<Archivos> getArchivos() {
        var lista = archivosDao.getArchivos();
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Archivos getArchivo(Archivos archivo) {
        List<Archivos> archivos = archivosDao.obtenerArchivoPorId(archivo.getIdArchivo());
        return archivos.isEmpty() ? null : archivos.get(0);
    }

    @Override
    @Transactional
    public void insertarArchivo(Archivos archivos) {
        archivosDao.insertarArchivo(
                archivos.getEstudiante().getIdEstudiante(),
                archivos.getTipoDocumento());
    }

    @Override
    public void actualizarArchivo(Archivos archivo) {
        archivosDao.actualizarArchivo(
                archivo.getIdArchivo(),
                archivo.getEstudiante().getIdEstudiante(),
                archivo.getTipoDocumento());
    }

    @Override
    @Transactional
    public void delete(Archivos archivos) {
        archivosDao.eliminarArchivo(archivos.getIdArchivo());
    }

}
