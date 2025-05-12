/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Controller;

import com.centroAcademico.SistemaAcademico.Domain.Profesores;
import com.centroAcademico.SistemaAcademico.Service.ProfesoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profesores")
public class ProfesoresController {
    
    @Autowired
    private ProfesoresService profesoresService;
    
    @GetMapping("/listado")
    public String listado(Model model){
        var lista=profesoresService.getProfesores();
        model.addAttribute("profesores", lista);
        model.addAttribute("totalProfesores", lista.size());
        return "/profesores/listado";
    }
    
    @PostMapping("/guardar")
    public String guardar(Profesores profesores){
    
            profesoresService.insertarProfesor(profesores);
            return "redirect:/profesores/listado";
    }
    
    @PostMapping("/actualizar")
    public String actualizar(Profesores profesores){
    
            profesoresService.actualizarProfesor(profesores);
            return "redirect:/profesores/listado";
    }
    
    @GetMapping("/eliminar/{idProfesor}")
    public String eliminar(Profesores profesores){
        profesoresService.eliminar(profesores);
        return "redirect:/profesores/listado";
    }
    
     @GetMapping("/modificar/{idProfesor}")
    public String modificar(Profesores profesor, Model model){
        profesor=profesoresService.getProfesor(profesor);
        model.addAttribute("profesor",profesor);
        return "/profesores/modifica";
    }   
}
