package com.app_remiseria.AppGestorRemiseriaSpring.controller;


import com.app_remiseria.AppGestorRemiseriaSpring.model.EstadoViaje;
import com.app_remiseria.AppGestorRemiseriaSpring.model.Viaje;
import com.app_remiseria.AppGestorRemiseriaSpring.service.ChoferService;
import com.app_remiseria.AppGestorRemiseriaSpring.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/viajes")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;

    @Autowired
    private ChoferService choferService;

    @GetMapping
    public String listAll(Model model){

        model.addAttribute("lista",viajeService.findAll());

        model.addAttribute("pestañaActiva", "viajes");

        return "viajes-lista";
    }

    @GetMapping("/crear")
    public String create(Model model){

        model.addAttribute("viaje", new Viaje());

        model.addAttribute("chofers", choferService.findAll());

        model.addAttribute("estadosViaje", EstadoViaje.values());

        model.addAttribute("pestañaActiva", "viajes");

        return "viaje-form";
    }

    @GetMapping("/editar/{id}")
    public String edit(Model model, @PathVariable Long id){

        model.addAttribute("viaje", viajeService.findById(id));

        model.addAttribute("chofers", choferService.findAll());

        model.addAttribute("estadosViaje", EstadoViaje.values());

        model.addAttribute("pestañaActiva", "viajes");

        return "viaje-form";
    }

    @PostMapping("/guardar")
    public String save(Viaje viaje){

        viajeService.save(viaje);

        return "redirect:/viajes";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id) {

        viajeService.delete(id);

        return "redirect:/viajes";
    }

}
