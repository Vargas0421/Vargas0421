/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Service.Impl;


import com.centroAcademico.SistemaAcademico.Dao.UsuarioDao;
import com.centroAcademico.SistemaAcademico.Domain.Rol;
import com.centroAcademico.SistemaAcademico.Domain.Usuario;
import com.centroAcademico.SistemaAcademico.Service.UsuarioDetailsService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gonza
 */


@Service("userDetailsService")
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService {
    
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private HttpSession session;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //se busca el registro en la tabla usuario que tenga el username pasado por prm
        Usuario usuario = usuarioDao.findByUsername(username);

        //Se valida si el usuario se encontro
        if (usuario == null) {
            //No se encontro el usuario
            throw new UsernameNotFoundException(username);

        }
        

        

        //se deben crear los roles que vienen desde la tabla rol...
        var roles = new ArrayList<GrantedAuthority>();

        //se recorren los roles del usuario y se pasan al arreglo... ya como rol de seguridad
        for (Rol r : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(r.getNombreRol()));
        }
        
        
        //Se retorna un usuario del sistema con username, password y roles...
        return new User(usuario.getUsername(),usuario.getPassword(),roles);
    }

}
