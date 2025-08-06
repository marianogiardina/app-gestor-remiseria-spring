package com.app_remiseria.AppGestorRemiseriaSpring.controller;

import com.app_remiseria.AppGestorRemiseriaSpring.model.Auto;
import com.app_remiseria.AppGestorRemiseriaSpring.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autos")
public class AutoController {

    @Autowired
    private AutoService autoService;

    @GetMapping
    public String listAll(Model model){

        model.addAttribute("lista",autoService.findAll());

        model.addAttribute("pestañaActiva", "autos");

        return "autos-lista";
    }

    @GetMapping("/crear")
    public String create(Model model){

        model.addAttribute("auto", new Auto());

        model.addAttribute("pestañaActiva", "autos");

        return "autos-form";
    }

    @GetMapping("/editar/{id}")
    public String edit(Model model, @PathVariable Long id){

        model.addAttribute("auto", autoService.findById(id));

        model.addAttribute("pestañaActiva", "autos");

        return "autos-form";
    }

    @PostMapping("/guardar")
    public String save(Auto auto){

        autoService.save(auto);

        return "redirect:/autos";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id) {

        autoService.delete(id);

        return "redirect:/autos";
    }



}
