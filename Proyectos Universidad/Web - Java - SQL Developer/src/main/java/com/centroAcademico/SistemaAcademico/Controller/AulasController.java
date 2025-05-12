package com.centroAcademico.SistemaAcademico.Controller;

import com.centroAcademico.SistemaAcademico.Domain.Aulas;
import com.centroAcademico.SistemaAcademico.Service.AulasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/aulas")
public class AulasController {
    
    @Autowired
    private AulasService aulasService;
    
   

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = aulasService.getAulas();
        model.addAttribute("aulas", lista);
        model.addAttribute("totalAulas", lista.size());
        
        return "/aulas/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Aulas aulas) {

        aulasService.insertarAula(aulas);
        return "redirect:/aulas/listado";
    }
    
    @PostMapping("/actualizar")
    public String actualizar(Aulas aulas){
    
            aulasService.actualizarAula(aulas);
            return "redirect:/aulas/listado";
    }

    @GetMapping("/eliminar/{idAula}")
    public String eliminar(Aulas aulas) {
        aulasService.eliminar(aulas);
        return "redirect:/aulas/listado";
    }
    
    

    @GetMapping("/modificar/{idAula}")
    public String modificar(Aulas aulas, Model model) {
        aulas = aulasService.getAula(aulas);
        model.addAttribute("aulas", aulas);
        
        return "/aulas/modifica";
    }
}
