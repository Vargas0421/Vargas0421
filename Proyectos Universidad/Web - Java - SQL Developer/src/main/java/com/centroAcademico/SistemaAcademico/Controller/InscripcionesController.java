package com.centroAcademico.SistemaAcademico.Controller;

import com.centroAcademico.SistemaAcademico.Domain.Inscripciones;
import com.centroAcademico.SistemaAcademico.Service.EstudianteService;
import com.centroAcademico.SistemaAcademico.Service.InscripcionesService;
import com.centroAcademico.SistemaAcademico.Service.MateriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/inscripciones")
public class InscripcionesController {
    
    @Autowired
    private InscripcionesService inscripcionesService;
    
    @Autowired
    private MateriasService materiasService;
    
    @Autowired
    private EstudianteService estudiantesService;
    
    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = inscripcionesService.getInscripciones();
        model.addAttribute("inscripciones", lista);
        model.addAttribute("totalInscripciones", lista.size());

        var materias = materiasService.getMaterias();
        model.addAttribute("materias", materias);
        
        var estudiantes = estudiantesService.obtenerEstudiantes();
        model.addAttribute("estudiantes", estudiantes);

        return "/inscripciones/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Inscripciones inscripciones) {

        inscripcionesService.insertarInscripcion(inscripciones);
        return "redirect:/inscripciones/listado";
    }

    @PostMapping("/actualizar")
    public String actualizar(Inscripciones inscripciones){
    
            inscripcionesService.actualizarInscripcion(inscripciones);
            return "redirect:/inscripciones/listado";
    }
    
    @GetMapping("/eliminar/{idInscripcion}")
    public String eliminar(Inscripciones inscripciones) {
        inscripcionesService.eliminar(inscripciones);
        return "redirect:/inscripciones/listado";
    }

    @GetMapping("/modificar/{idInscripcion}")
    public String modificar(Inscripciones inscripciones, Model model) {
        inscripciones = inscripcionesService.getInscripcion(inscripciones);
        model.addAttribute("inscripciones", inscripciones);
        
        var materias = materiasService.getMaterias();
        model.addAttribute("materias", materias);
        
        var estudiantes = estudiantesService.obtenerEstudiantes();
        model.addAttribute("estudiantes", estudiantes);
        
        return "/inscripciones/modifica";
    }
    
}
