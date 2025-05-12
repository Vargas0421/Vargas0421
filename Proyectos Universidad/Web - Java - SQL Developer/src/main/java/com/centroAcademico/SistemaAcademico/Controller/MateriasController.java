/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Controller;

import com.centroAcademico.SistemaAcademico.Domain.Materias;
import com.centroAcademico.SistemaAcademico.Service.MateriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/materias")
public class MateriasController {
    
    @Autowired
    private MateriasService materiasService;
    
    @GetMapping("/listado")
    public String listado(Model model){
        var lista=materiasService.getMaterias();
        model.addAttribute("materias", lista);
        model.addAttribute("totalMaterias", lista.size());
        return "/materias/listado";
    }
    
    @PostMapping("/guardar")
    public String guardar(Materias materias){
    
            materiasService.insertarMateria(materias);
            return "redirect:/materias/listado";
    }
    
    @PostMapping("/actualizar")
    public String actualizar(Materias materia){
    
            materiasService.actualizarMateria(materia);
            return "redirect:/materias/listado";
    }
    
    @GetMapping("/eliminar/{idMateria}")
    public String eliminar(Materias materias){
        materiasService.eliminar(materias);
        return "redirect:/materias/listado";
    }
    
     @GetMapping("/modificar/{idMateria}")
    public String modificar(Materias materia, Model model){
        materia=materiasService.getMateria(materia);
        model.addAttribute("materia",materia);
        return "/materias/modifica";
    }   
}
