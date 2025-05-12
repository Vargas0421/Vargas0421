package com.centroAcademico.SistemaAcademico.Controller;

import com.centroAcademico.SistemaAcademico.Domain.Clases;
import com.centroAcademico.SistemaAcademico.Service.AulasService;
import com.centroAcademico.SistemaAcademico.Service.ClasesService;
import com.centroAcademico.SistemaAcademico.Service.HorariosService;
import com.centroAcademico.SistemaAcademico.Service.MateriasService;
import com.centroAcademico.SistemaAcademico.Service.ProfesoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clases")
public class ClasesController {
    
    @Autowired
    private ClasesService clasesService;

    @Autowired
    private HorariosService horariosService;
    
    @Autowired
    private ProfesoresService profesorService;
    
    @Autowired
    private MateriasService materiasService;
    
    @Autowired
    private AulasService aulasService;
    
    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = clasesService.obtenerClases();
        model.addAttribute("clases", lista);
        model.addAttribute("totalClases", lista.size());

        var horarios = horariosService.getHorarios();
        model.addAttribute("horarios", horarios);
        
        var profesores = profesorService.getProfesores();
        model.addAttribute("profesores", profesores);
        
        var materias = materiasService.getMaterias();
        model.addAttribute("materias", materias);
        
        var aulas = aulasService.getAulas();
        model.addAttribute("aulas", aulas);

        return "/clases/listado";
    }

     @PostMapping("/actualizar")
    public String actualizar(Clases clase){
    
            clasesService.actualizarClase(clase);
            return "redirect:/clases/listado";
    }
    
    @PostMapping("/guardar")
    public String guardar(Clases clases) {

        clasesService.insertarClase(clases);
        return "redirect:/clases/listado";
    }

    @GetMapping("/eliminar/{idClase}")
    public String eliminar(Clases clases) {
        clasesService.delete(clases);
        return "redirect:/clases/listado";
    }

    @GetMapping("/modificar/{idClase}")
    public String modificar(Clases clases, Model model) {
        clases = clasesService.obtenerClasePorId(clases);
        model.addAttribute("clases", clases);
        
        var horarios = horariosService.getHorarios();
        model.addAttribute("horarios", horarios);
        
        var profesores = profesorService.getProfesores();
        model.addAttribute("profesores", profesores);
        
        var materias = materiasService.getMaterias();
        model.addAttribute("materias", materias);
        
        var aulas = aulasService.getAulas();
        model.addAttribute("aulas", aulas);
        
        return "/clases/modifica";
    }
}
