/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Controller;

import com.centroAcademico.SistemaAcademico.Domain.Archivos;
import com.centroAcademico.SistemaAcademico.Service.ArchivosService;
import com.centroAcademico.SistemaAcademico.Service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/archivos")
public class ArchivosController {

   @Autowired
    private ArchivosService archivosService;

    @Autowired
    private EstudianteService estudianteService;
    
   

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = archivosService.getArchivos();
        model.addAttribute("archivos", lista);
        model.addAttribute("totalArchivos", lista.size());

        var estudiantes = estudianteService.obtenerEstudiantes();
        model.addAttribute("estudiantes", estudiantes);

        
        
        return "/archivos/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Archivos archivos) {

        archivosService.insertarArchivo(archivos);
        return "redirect:/archivos/listado";
    }

    @GetMapping("/eliminar/{idArchivo}")
    public String eliminar(Archivos archivos) {
        archivosService.delete(archivos);
        return "redirect:/archivos/listado";
    }
    
     @PostMapping("/actualizar")
    public String actualizar(Archivos archivo){
    
            archivosService.actualizarArchivo(archivo);
            return "redirect:/archivos/listado";
    }

    @GetMapping("/modificar/{idArchivo}")
    public String modificar(Archivos archivo, Model model) {
        archivo = archivosService.getArchivo(archivo);
        model.addAttribute("archivo", archivo);
        
         var estudiantes = estudianteService.obtenerEstudiantes();
        model.addAttribute("estudiantes", estudiantes);
        
        return "/archivos/modifica";
    }
}
