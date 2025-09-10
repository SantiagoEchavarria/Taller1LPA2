package com.example.libreria.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.libreria.models.entity.Prestamo;
import com.example.libreria.models.service.LibreriaInterface;
import com.example.libreria.utils.paginator.PageRender;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/libreria")
@SessionAttributes("prestamo")
public class PrestamoController {

    private final LibreriaInterface libreriaService;

    public PrestamoController(LibreriaInterface libreriaService) {
        this.libreriaService = libreriaService;
    }

    @GetMapping("/prestamoslistar")
    public String listarPrestamos(@RequestParam(value = "pag", defaultValue = "0") int pag,Model model) {
         Pageable pagina = PageRequest.of(pag, 5);
		Page<Prestamo> prestamos = libreriaService.listarPrestamos(pagina);

		PageRender<Prestamo> pageRender = new PageRender<>("/libreria/prestamoslistar", prestamos);

		model.addAttribute("pageRender", pageRender);
        model.addAttribute("titulo", "Listado de Préstamos");
        model.addAttribute("prestamos", prestamos);
        return "prestamo/prestamoslistar";
    }

    @GetMapping("/prestamonuevo")
    public String nuevoPrestamo(Model model) {
        model.addAttribute("titulo", "Nuevo Préstamo");
        model.addAttribute("prestamo", new Prestamo());
        return "prestamo/prestamonuevo";
    }

    @PostMapping("/prestamoguardar")
    public String guardarPrestamo(@Valid @ModelAttribute Prestamo prestamo, BindingResult errors, Model model,
                                  SessionStatus status, RedirectAttributes flash) {
        if (errors.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Préstamo");
            model.addAttribute("prestamo", new Prestamo());
            model.addAttribute("error", "Error al guardar el préstamo");
            return "prestamo/prestamonuevo";
        }

        libreriaService.guardarPrestamo(prestamo);
        flash.addFlashAttribute("success", "Préstamo guardado con éxito");
        status.setComplete();
        return "redirect:/libreria/prestamoslistar";
    }
}
