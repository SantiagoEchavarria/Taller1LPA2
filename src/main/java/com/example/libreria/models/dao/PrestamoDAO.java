package com.example.libreria.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.libreria.models.entity.Libro;
import com.example.libreria.models.entity.Prestamo;
@Repository
public interface PrestamoDAO extends JpaRepository<Prestamo, Long> {
    @Query("SELECT COUNT(p) FROM Prestamo p WHERE p.usuario.id = ?1")
    public Long numeroPrestamosPorUsuario(Long usuarioId);

    @Query("SELECT p.libro FROM Prestamo p WHERE p.usuario.id = ?1")
    List<Libro> buscarLibrosPrestadosPorUsuario(Long usuarioId);

    @Query("SELECT p.libro FROM Prestamo p WHERE p.usuario.id = ?1 AND p.libro.titulo LIKE %?2%")
    List<Libro> buscarLibrosPrestadosPorUsuarioYTitulo(Long usuarioId, String titulo);
}
