/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Controller;

import com.centroAcademico.SistemaAcademico.Domain.Estudiante;
import com.centroAcademico.SistemaAcademico.Service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {
    
    @Autowired
    private EstudianteService estudianteService;
    
    @GetMapping("/listado")
    public String listado(Model model){
        var lista=estudianteService.obtenerEstudiantes();
        model.addAttribute("estudiantes", lista);
        model.addAttribute("totalEstudiantes", lista.size());
        return "/estudiante/listado";
    }
    
    @PostMapping("/guardar")
    public String guardar(Estudiante estudiante){
    
            estudianteService.insertarEstudiante(estudiante);
            return "redirect:/estudiante/listado";
    }
    
     @PostMapping("/actualizar")
    public String actualizar(Estudiante estudiante){
    
            estudianteService.actualizarEstudiante(estudiante);
            return "redirect:/estudiante/listado";
    }
    
    @GetMapping("/eliminar/{idEstudiante}")
    public String eliminar(Estudiante estudiante){
        estudianteService.delete(estudiante);
        return "redirect:/estudiante/listado";
    }
    
     @GetMapping("/modificar/{idEstudiante}")
    public String modificar(Estudiante estudiante, Model model){
        estudiante=estudianteService.obtenerEstudiantePorId(estudiante);
        model.addAttribute("estudiante",estudiante);
        return "/estudiante/modifica";
    }   
}
