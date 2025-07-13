package com.app_remiseria.AppGestorRemiseriaSpring.controller;

import com.app_remiseria.AppGestorRemiseriaSpring.model.Auto;
import com.app_remiseria.AppGestorRemiseriaSpring.model.Chofer;
import com.app_remiseria.AppGestorRemiseriaSpring.service.AutoService;
import com.app_remiseria.AppGestorRemiseriaSpring.service.ChoferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chofer")
public class ChoferController {

    @Autowired
    private ChoferService choferService;

    @Autowired
    private AutoService autoService;

    @GetMapping
    public String listAll(Model model){
        model.addAttribute("lista",choferService.findAll());
        return "chofer-lista";
    }

    @GetMapping("/crear")
    public String create(Model model){
        model.addAttribute("chofer", new Chofer());
        model.addAttribute("autos", autoService.findAll());
        return "chofer-form";
    }

    @GetMapping("/editar/{id}")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("chofer", choferService.findById(id));
        model.addAttribute("autos", autoService.findAll());
        return "chofer-form";
    }

    @PostMapping("/guardar")
    public String save(Chofer chofer){

        choferService.save(chofer);

        return "redirect:/chofer";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id) {

        choferService.delete(id);

        return "redirect:/chofer";
    }

}
