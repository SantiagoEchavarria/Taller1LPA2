package com.example.libreria.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.libreria.models.entity.Usuario;
import com.example.libreria.models.service.LibreriaInterface;
import com.example.libreria.utils.paginator.PageRender;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/libreria")
@SessionAttributes("usuario")
public class UsuarioController {

    private final LibreriaInterface libreriaService;

    public UsuarioController(LibreriaInterface libreriaService) {
        this.libreriaService = libreriaService;
    }

    @GetMapping("/usuarioslistar")
    public String listarUsuarios(@RequestParam(value = "pag", defaultValue = "0") int pag, Model model) {
        Pageable pagina = PageRequest.of(pag, 5);
        Page<Usuario> usuarios = libreriaService.listarUsuarios(pagina);

		PageRender<Usuario> pageRender = new PageRender<>("/libreria/usuarioslistar", usuarios);

		model.addAttribute("pageRender", pageRender);
        model.addAttribute("titulo", "Listado de Usuarios");
        model.addAttribute("usuarios", usuarios);
        return "usuario/usuarioslistar";
    }

    @GetMapping("/usuarionuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("titulo", "Nuevo Usuario");
        model.addAttribute("usuario", new Usuario());
        return "usuario/usuarionuevo";
    }

    @PostMapping("/usuarioguardar")
    public String guardarUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult errors, Model model,
                                 SessionStatus status, RedirectAttributes flash) {
        if (errors.hasErrors()) {
            model.addAttribute("titulo", "Nuevo Usuario");
            model.addAttribute("usuario", new Usuario());
            model.addAttribute("error", "Error al guardar el usuario");
            return "usuario/usuarionuevo";
        }

        libreriaService.guardarUsuario(usuario);
        flash.addFlashAttribute("success", "Usuario guardado con éxito");
        status.setComplete();
        return "redirect:/libreria/usuarioslistar";
    }

    @GetMapping("/usuarioeditar/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model, RedirectAttributes flash) {
        Usuario usuario = libreriaService.obtenerUsuarioPorId(id);
        if (usuario == null) {
            flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
            return "redirect:/libreria/usuarioslistar";
        }
        model.addAttribute("titulo", "Editar Usuario");
        model.addAttribute("usuario", usuario);
        return "usuario/usuarionuevo";
    }

    @GetMapping("/usuarioeliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id, RedirectAttributes flash) {
        Usuario usuario = libreriaService.obtenerUsuarioPorId(id);
        if (usuario == null) {
            flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
            return "redirect:/libreria/usuarioslistar";
        }
        libreriaService.eliminarUsuario(id);
        flash.addFlashAttribute("success", "Usuario eliminado con éxito");
        return "redirect:/libreria/usuarioslistar";
    }
}

