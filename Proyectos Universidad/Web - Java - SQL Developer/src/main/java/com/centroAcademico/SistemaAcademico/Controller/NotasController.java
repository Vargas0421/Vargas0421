/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Controller;

import com.centroAcademico.SistemaAcademico.Domain.Notas;
import com.centroAcademico.SistemaAcademico.Service.EstudianteService;
import com.centroAcademico.SistemaAcademico.Service.NotasService;
import com.centroAcademico.SistemaAcademico.Service.MateriasService;
import com.centroAcademico.SistemaAcademico.Service.ProfesoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/notas")
public class NotasController {

    @Autowired
    private NotasService notasService;

    @Autowired
    private ProfesoresService profesoresService;

    @Autowired
    private MateriasService materiasService;

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = notasService.obtenerNotas();
        model.addAttribute("notas", lista);
        model.addAttribute("totalNotas", lista.size());

        var profesores = profesoresService.getProfesores();
        model.addAttribute("profesores", profesores);

        var materias = materiasService.getMaterias();
        model.addAttribute("materias", materias);
        
         var estudiantes = estudianteService.obtenerEstudiantes();
        model.addAttribute("estudiantes", estudiantes);

        return "/notas/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Notas notas) {

        notasService.insertarNota(notas);
        return "redirect:/notas/listado";
    }
    
    @PostMapping("/actualizar")
    public String actualizar(Notas notas){
    
            notasService.actualizarNota(notas);
            return "redirect:/notas/listado";
    }

    @GetMapping("/eliminar/{idNota}")
    public String eliminar(Notas notas) {
        notasService.delete(notas);
        return "redirect:/notas/listado";
    }

    @GetMapping("/modificar/{idNota}")
    public String modificar(Notas nota, Model model) {
        nota = notasService.getNotaPorId(nota);
        model.addAttribute("nota", nota);

        var profesores = profesoresService.getProfesores();
        model.addAttribute("profesores", profesores);

        var materias = materiasService.getMaterias();
        model.addAttribute("materias", materias);
        
         var estudiantes = estudianteService.obtenerEstudiantes();
        model.addAttribute("estudiantes", estudiantes);

        return "/notas/modifica";
    }
}
