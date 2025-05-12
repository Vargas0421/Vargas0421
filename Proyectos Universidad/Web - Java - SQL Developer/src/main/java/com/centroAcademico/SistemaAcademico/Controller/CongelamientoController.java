/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.centroAcademico.SistemaAcademico.Controller;

import com.centroAcademico.SistemaAcademico.Domain.Congelamiento;
import com.centroAcademico.SistemaAcademico.Service.EstudianteService;
import com.centroAcademico.SistemaAcademico.Service.MateriasService;
import com.centroAcademico.SistemaAcademico.Service.ProfesoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.centroAcademico.SistemaAcademico.Service.CongelamientoService;

@Controller
@RequestMapping("/congelamiento")
public class CongelamientoController {

    @Autowired
    private CongelamientoService congelamientoService;

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = congelamientoService.obtenerCongelamientos();
        model.addAttribute("congelamientos", lista);
        model.addAttribute("totalCongelamientos", lista.size());

        var estudiantes = estudianteService.obtenerEstudiantes();
        model.addAttribute("estudiantes", estudiantes);

        return "/congelamiento/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Congelamiento congelamientos) {

        congelamientoService.insertarCongelamiento(congelamientos);
        return "redirect:/congelamiento/listado";
    }

    @GetMapping("/eliminar/{idCongelamiento}")
    public String eliminar(Congelamiento congelamientos) {
        congelamientoService.eliminarCongelamiento(congelamientos);
        return "redirect:/congelamiento/listado";
    }

    @PostMapping("/actualizar")
    public String actualizar(Congelamiento congelamiento) {
        congelamientoService.actualizarCongelamiento(congelamiento);
        return "redirect:/congelamiento/listado";
    }

    @GetMapping("/modificar/{idCongelamiento}")
    public String modificar(Congelamiento congelamiento, Model model) {
        congelamiento = congelamientoService.obtenerCongelamientoPorId(congelamiento);
        model.addAttribute("congelamiento", congelamiento);

        var estudiantes = estudianteService.obtenerEstudiantes();
        model.addAttribute("estudiantes", estudiantes);

        return "/congelamiento/modifica";
    }
}
