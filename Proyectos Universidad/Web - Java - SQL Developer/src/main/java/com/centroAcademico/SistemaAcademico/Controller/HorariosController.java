/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Controller;

import com.centroAcademico.SistemaAcademico.Domain.Horarios;
import com.centroAcademico.SistemaAcademico.Service.HorariosService;
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
@RequestMapping("/horarios")
public class HorariosController {

   @Autowired
    private HorariosService horariosService;

    @Autowired
    private ProfesoresService profesoresService;
    
    @Autowired
    private MateriasService materiasService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = horariosService.getHorarios();
        model.addAttribute("horarios", lista);
        model.addAttribute("totalHorarios", lista.size());

        var profesores = profesoresService.getProfesores();
        model.addAttribute("profesores", profesores);

        var materias = materiasService.getMaterias();
        model.addAttribute("materias", materias);
        
        return "/horarios/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Horarios horarios) {

        horariosService.insertarHorario(horarios);
        return "redirect:/horarios/listado";
    }
    
     @PostMapping("/actualizar")
    public String actualizar(Horarios horarios) {

        horariosService.actualizarHorario(horarios);
        return "redirect:/horarios/listado";
    }

    @GetMapping("/eliminar/{idHorario}")
    public String eliminar(Horarios horarios) {
        horariosService.eliminarHorario(horarios);
        return "redirect:/horarios/listado";
    }

    @GetMapping("/modificar/{idHorario}")
    public String modificar(Horarios horario, Model model) {
        horario = horariosService.getHorario(horario);
        model.addAttribute("horario", horario);
        
         var profesores = profesoresService.getProfesores();
        model.addAttribute("profesores", profesores);

        var materias = materiasService.getMaterias();
        model.addAttribute("materias", materias);
        
        return "/horarios/modifica";
    }
}
