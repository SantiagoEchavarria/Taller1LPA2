package com.example.libreria.models.service;

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
}
