package com.app_remiseria.AppGestorRemiseriaSpring.controller;

import com.app_remiseria.AppGestorRemiseriaSpring.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cerrar-semana")
public class CerrarSemanaController {

    @Autowired
    private ViajeService viajeService;

    @GetMapping
    public String cerrarSemana(Model model){
        model.addAttribute("semanasChofer", viajeService.cerrarSemanaChofer());
        return "cerrar-semana";
    }

}
