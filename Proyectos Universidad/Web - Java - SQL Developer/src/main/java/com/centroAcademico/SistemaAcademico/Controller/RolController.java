/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Controller;

import com.centroAcademico.SistemaAcademico.Domain.Rol;
import com.centroAcademico.SistemaAcademico.Service.RolService;
import com.centroAcademico.SistemaAcademico.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = rolService.getRoles();
        model.addAttribute("rols", lista);
        model.addAttribute("totalRols", lista.size());

        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);

        return "/rol/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Rol rol) {

        rolService.insertarRol(rol);
        return "redirect:/rol/listado";
    }
    
    @PostMapping("/actualizar")
    public String actualizar(Rol rol) {

        rolService.actualizarRol(rol);
        return "redirect:/rol/listado";
    }

    @GetMapping("/eliminar/{idRol}")
    public String eliminar(Rol rol) {
        rolService.eliminarRol(rol);
        return "redirect:/rol/listado";
    }

    @GetMapping("/modificar/{idRol}")
    public String modificar(Rol rol, Model model) {
        rol = rolService.getRol(rol);
        model.addAttribute("rol", rol);

        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);

        return "/rol/modifica";
    }
}
