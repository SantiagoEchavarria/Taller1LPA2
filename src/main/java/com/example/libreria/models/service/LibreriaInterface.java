package com.example.libreria.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.libreria.models.entity.Libro;
import com.example.libreria.models.entity.Prestamo;
import com.example.libreria.models.entity.Usuario;

public interface LibreriaInterface{
    //Metodos de Libro
    public Page<Libro> listarLibros(Pageable pageable);
    public List<Libro> listarLibros();
    public void guardarLibro(Libro libro);
    public Libro obtenerLibroPorId(Long id);
    public void eliminarLibro(Long id);

    //Metodos de Usuario
    public Page<Usuario> listarUsuarios(Pageable pageable);
    public List<Usuario> listarUsuarios();
    public void guardarUsuario(Usuario usuario);
    public Usuario obtenerUsuarioPorId(Long id);
    public void eliminarUsuario(Long id);

    //Metodos de Prestamo
    public Page<Prestamo> listarPrestamos(Pageable pageable);
    public List<Prestamo> listarPrestamos();    
    public void guardarPrestamo(Prestamo prestamo);
    public Prestamo obtenerPrestamoPorId(Long id);
    public void eliminarPrestamo(Long id);
    
}
