package com.example.libreria.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.libreria.models.entity.Libro;
import com.example.libreria.models.service.LibreriaInterface;
import com.example.libreria.utils.paginator.PageRender;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/libreria")
@SessionAttributes("libro")
public class LibroController {
    private final LibreriaInterface libreriaService;

    public LibroController(LibreriaInterface libreriaService) {
        this.libreriaService = libreriaService;
    }

    @GetMapping("/libroslistar")
    public String listarLibros(@RequestParam(value = "pag", defaultValue = "0") int pag,Model model) {

        Pageable pagina = PageRequest.of(pag, 5);
		Page<Libro> libros = libreriaService.listarLibros(pagina);

		PageRender<Libro> pageRender = new PageRender<>("/libreria/libroslistar", libros);

		model.addAttribute("pageRender", pageRender);
        model.addAttribute("titulo", "Listado de Libros");
        model.addAttribute("libros", libros);
        return "libro/libroslistar";
    }

    @GetMapping("/libronuevo")
    public String nuevoLibro(Model model) {
        model.addAttribute("titulo", "Nuevo Libro");
        model.addAttribute("libro", new Libro());
        return "libro/libronuevo";
    }

    @PostMapping("/libroguardar")
    public String guardarLibro(@Valid @ModelAttribute Libro libro, BindingResult errors, Model model,
            SessionStatus status, RedirectAttributes flash) {
        if (errors.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Libro");
            model.addAttribute("libro", new Libro());
            model.addAttribute("error", "Error al guardar el libro");
            return "libro/libronuevo";
        }

        libreriaService.guardarLibro(libro);
        flash.addFlashAttribute("success", "Libro guardado con éxito");
        status.setComplete();
        return "redirect:/libreria/libroslistar";
    }

    @GetMapping("/libroeditar/{id}")
    public String editarLibro(@PathVariable("id") Long id, Model model, RedirectAttributes flash) {
        Libro libro = libreriaService.obtenerLibroPorId(id);
        if (libro == null) {
            flash.addFlashAttribute("error", "El libro no existe en la base de datos");
            return "redirect:/libreria/libroslistar";
        }
        model.addAttribute("titulo", "Editar Libro");
        model.addAttribute("libro", libro);
        return "libro/libronuevo";
    }

    @GetMapping("/libroeliminar/{id}")
    public String eliminarLibro(@PathVariable("id") Long id, RedirectAttributes flash) {
        Libro libro = libreriaService.obtenerLibroPorId(id);
        if (libro == null) {
           flash.addFlashAttribute("error", "El libro no existe en la base de datos");
            return "redirect:/libreria/libroslistar";
        }
        libreriaService.eliminarLibro(id);
        flash.addFlashAttribute("success", "Libro eliminado con éxito");
        return "redirect:/libreria/libroslistar";
    }
}
