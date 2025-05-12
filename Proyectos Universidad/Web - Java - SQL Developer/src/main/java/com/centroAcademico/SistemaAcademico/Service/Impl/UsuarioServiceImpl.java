/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Service.Impl;


import com.centroAcademico.SistemaAcademico.Dao.RolDao;
import com.centroAcademico.SistemaAcademico.Domain.Usuario;

import com.centroAcademico.SistemaAcademico.Service.UsuarioService;
import com.centroAcademico.SistemaAcademico.Dao.UsuarioDao;
import com.centroAcademico.SistemaAcademico.Domain.Rol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
 
    @Autowired
    private UsuarioDao usuarioDao; //se conecta con el dao para poder utilizar los metodos que se encuentran en el dao 
    @Autowired
    private RolDao rolDao; 
    
   
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioDao.getUsuarios();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        List<Usuario> usuarios = usuarioDao.getUsuario(usuario.getIdUsuario());
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsername(String username) {
        return usuarioDao.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameYPassword(String username, String password) {
        return usuarioDao.findByUsernameAndPassword(username, password);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameOCorreo(String username, String correo) {
        return usuarioDao.findByUsernameOrCorreo(username, correo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo) {
        return usuarioDao.existsByUsernameOrCorreo(username, correo);
    }

    @Override
    @Transactional
    public void insertarUsuario(Usuario usuario) { // se inserta un nuevo usuario si el id del usuario esta vacío
        String passwordHashed = passwordEncoder.encode(usuario.getPassword());// esto lo ingno para mi caso de archivo porque no hay password
        usuario.setPassword(passwordHashed);
        usuarioDao.insertarUsuario(         
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getNombre(),
                usuario.getApellidos(),
                usuario.getCorreo(),
                usuario.getActivo());
         
           
        
    }
    
    @Override
    @Transactional
    public void actualizarUsuario(Usuario usuario) { // se actualiza un usuario si el id del usuario NO esta vacío      
        usuarioDao.actualizarUsuario(
                usuario.getIdUsuario(),
                usuario.getUsername(),
                usuario.getNombre(),
                usuario.getApellidos(),
                usuario.getCorreo(),
                usuario.getActivo());
         
           
        
    }

    @Override
    @Transactional
    public void eliminarUsuario(Usuario usuario) { /// se elimina el usuario que tiene el id pasado por parámetro
        usuarioDao.eliminarUsuario(usuario.getIdUsuario());
    }
}
    

