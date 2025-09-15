package com.example.libreria.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.example.libreria.models.dao.LibroDAO;
import com.example.libreria.models.dao.PrestamoDAO;
import com.example.libreria.models.dao.UsuarioDAO;
import com.example.libreria.models.entity.Libro;
import com.example.libreria.models.entity.Prestamo;
import com.example.libreria.models.entity.Usuario;


@Service
public class LibreriaService implements LibreriaInterface {

    private final LibroDAO libroDAO;
    private final UsuarioDAO usuarioDAO;
    private final PrestamoDAO prestamoDAO;

    public LibreriaService(LibroDAO libroDAO, UsuarioDAO usuarioDAO, PrestamoDAO prestamoDAO) {
        this.libroDAO = libroDAO;
        this.usuarioDAO = usuarioDAO;
        this.prestamoDAO = prestamoDAO;
    }

    // LIBROS
    @Override
    public Page<Libro> listarLibros(Pageable pageable) {
        return libroDAO.findAll(pageable);
    }

    @Override
    public void guardarLibro(Libro libro) {
        libroDAO.save(libro);
    }

    @Override
    public Libro obtenerLibroPorId(Long id) {
        return libroDAO.findById(id).orElse(null);
    }

    @Override
    public void eliminarLibro(Long id) {
        libroDAO.deleteById(id);
    }

    @Override
    public List<Libro> listarLibros() {
        return libroDAO.findAll();
    }

    // USUARIOS
    @Override
    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuarioDAO.findAll(pageable);
    }

    @Override
    public void guardarUsuario(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioDAO.findById(id).orElse(null);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioDAO.deleteById(id);
    }
    
    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioDAO.findAll();
    }

    // PRESTAMOS
    @Override
    public Page<Prestamo> listarPrestamos(Pageable pageable) {
        return prestamoDAO.findAll(pageable);
    }

    @Override
    public void guardarPrestamo(Prestamo prestamo) {
        prestamoDAO.save(prestamo);
    }

    @Override
    public Prestamo obtenerPrestamoPorId(Long id) {
        return prestamoDAO.findById(id).orElse(null);
    }

    @Override
    public void eliminarPrestamo(Long id) {
        prestamoDAO.deleteById(id);
    }

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoDAO.findAll();
    }

    @Override
    public Long numeroPrestamosPorUsuario(Long usuarioId) {
        return prestamoDAO.numeroPrestamosPorUsuario(usuarioId);
    }

    @Override
    public List<Libro> buscarLibrosPrestadosPorUsuario(Long usuarioId) {
        return prestamoDAO.buscarLibrosPrestadosPorUsuario(usuarioId);
    }

    @Override
    public List<Libro> buscarLibroPorTituloYUsuario(String titulo, Long usuarioId) {
        return prestamoDAO.buscarLibrosPrestadosPorUsuarioYTitulo(usuarioId, titulo);
    }
    
}
