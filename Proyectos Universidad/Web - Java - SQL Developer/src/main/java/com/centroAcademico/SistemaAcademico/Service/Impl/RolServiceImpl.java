/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Service.Impl;


import com.centroAcademico.SistemaAcademico.Domain.Rol;

import com.centroAcademico.SistemaAcademico.Service.RolService;
import com.centroAcademico.SistemaAcademico.Dao.RolDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolServiceImpl implements RolService {

     @Autowired
    private RolDao rolDao;
     
    @Override
    @Transactional(readOnly=true)
    public List<Rol> getRoles() {
        var lista = rolDao.getRoles();
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Rol getRol(Rol rol) {
        List<Rol> roles = rolDao.getRol(rol.getIdRol());
        return roles.isEmpty() ? null : roles.get(0);
    }

    @Override
    @Transactional
    public void insertarRol(Rol rol) {
        rolDao.insertarRol(
                rol.getNombreRol(),
                rol.getUsuario().getIdUsuario());
    }
    
    @Override
    @Transactional
    public void actualizarRol(Rol rol) {
        rolDao.actualizarRol(
                rol.getIdRol(),
                rol.getNombreRol(),
                rol.getUsuario().getIdUsuario());
    }

    @Override
    @Transactional
    public void eliminarRol(Rol rol) {
        rolDao.eliminarRol(rol.getIdRol());
    }
    
    
}
