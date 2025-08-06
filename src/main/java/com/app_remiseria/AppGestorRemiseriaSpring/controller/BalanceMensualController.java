package com.app_remiseria.AppGestorRemiseriaSpring.controller;

import com.app_remiseria.AppGestorRemiseriaSpring.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Controller
@RequestMapping("/balance-mensual")
public class BalanceMensualController {

    @Autowired
    private ViajeService viajeService;

    //En este metodo envio muchos datos de fechas, para utilizar en la vista.
    //Ademas envio el balance mensual que es un dto
    @GetMapping
    public String getBalance(Model model){

        LocalDate fechaInicioMes = LocalDate.now().withDayOfMonth(1);

        LocalDate fechaFinMes = LocalDate.now().plusMonths(1).withDayOfMonth(1);

        model.addAttribute("balance", viajeService.realizarBalanceMensual());
        model.addAttribute("fechaInicioMes", fechaInicioMes);
        model.addAttribute("fechaFinMes", fechaFinMes);
        model.addAttribute("mes", fechaInicioMes.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")));
        model.addAttribute("año", fechaInicioMes.getYear());
        model.addAttribute("cantidadDiasMes", LocalDate.now().lengthOfMonth());
        model.addAttribute("pestañaActiva", "balance");

        return "balance-mensual";
    }

}
