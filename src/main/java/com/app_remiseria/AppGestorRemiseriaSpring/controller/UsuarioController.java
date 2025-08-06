package com.app_remiseria.AppGestorRemiseriaSpring.controller;

import com.app_remiseria.AppGestorRemiseriaSpring.model.Usuario;
import com.app_remiseria.AppGestorRemiseriaSpring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listAll(Model model) {

        model.addAttribute("lista", usuarioService.findAll());

        model.addAttribute("pestañaActiva", "usuarios");

        return "usuarios-lista";
    }

    @GetMapping("/crear")
    public String create(Model model) {

        model.addAttribute("usuario", new Usuario());

        model.addAttribute("pestañaActiva", "usuarios");

        return "usuario-form";
    }

    @PostMapping("/guardar")
    public String save(Usuario usuario) {

        usuarioService.save(usuario);

        return "redirect:/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id) {

        usuarioService.deleteById(id);

        return "redirect:/usuarios";
    }


}
