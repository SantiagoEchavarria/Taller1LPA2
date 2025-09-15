package com.example.libreria.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.libreria.models.entity.Libro;
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
        model.addAttribute("libros", libreriaService.listarLibrosDisponibles());
        model.addAttribute("usuarios", libreriaService.listarUsuarios());
        return "prestamo/prestamonuevo";
    }

    @PostMapping("/prestamoguardar")
    public String guardarPrestamo(@Valid @ModelAttribute Prestamo prestamo, BindingResult errors, Model model,
                                  SessionStatus status, RedirectAttributes flash) {
        if (errors.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Préstamo");
            model.addAttribute("prestamo", new Prestamo());
            model.addAttribute("libros", libreriaService.listarLibros());
            model.addAttribute("usuarios", libreriaService.listarUsuarios());
            model.addAttribute("error", "Error al guardar el préstamo");
            return "prestamo/prestamonuevo";
        }
        if (libreriaService.numeroPrestamosPorUsuario(prestamo.getUsuario().getId()) >= 5) {
            model.addAttribute("titulo", "Nuevo Préstamo");
            model.addAttribute("prestamo", new Prestamo());
            model.addAttribute("libros", libreriaService.listarLibros());
            model.addAttribute("usuarios", libreriaService.listarUsuarios());
            model.addAttribute("error", "El usuario ya tiene 5 préstamos activos. No puede realizar más préstamos.");
            return "prestamo/prestamonuevo";
        }
        Libro libro = libreriaService.obtenerLibroPorId(prestamo.getLibro().getId());
        libro.setDisponible(false);
        prestamo.setLibro(libro);
        libreriaService.guardarLibro(libro);

        libreriaService.guardarPrestamo(prestamo);
        flash.addFlashAttribute("success", "Préstamo guardado con éxito");
        status.setComplete();
        return "redirect:/libreria/prestamoslistar";
    }
    @GetMapping("/prestamoeditar/{id}")
    public String editarPrestamo(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Prestamo prestamo = libreriaService.obtenerPrestamoPorId(id);
        if (prestamo == null) {
            flash.addFlashAttribute("error", "El préstamo no existe en la base de datos");
            return "redirect:/libreria/prestamoslistar";
        }
        model.addAttribute("titulo", "Editar Préstamo");
        model.addAttribute("prestamo", prestamo);
        model.addAttribute("libros", libreriaService.listarLibros());
        model.addAttribute("usuarios", libreriaService.listarUsuarios());
        return "prestamo/prestamonuevo";
    }
    @GetMapping("/prestamoeliminar/{id}")
    public String eliminarPrestamo(@PathVariable Long id, RedirectAttributes flash) {
        Prestamo prestamo = libreriaService.obtenerPrestamoPorId(id);
        if (prestamo != null) {
            libreriaService.eliminarPrestamo(id);
            flash.addFlashAttribute("success", "Préstamo eliminado con éxito");
        } else {
            flash.addFlashAttribute("error", "El préstamo no existe en la base de datos");
        }
        return "redirect:/libreria/prestamoslistar";
    }

    @GetMapping("/cargarproductos/{id}/{term}")
    @ResponseBody
    public List<Libro> cargarLibros(@PathVariable Long id, @PathVariable String term) {
        return libreriaService.buscarLibroPorTituloYUsuario(term, id);
    }
}