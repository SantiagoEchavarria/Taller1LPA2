package com.example.libreria.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.libreria.models.entity.Libro;
@Repository
public interface LibroDAO extends JpaRepository<Libro, Long>{

    List<Libro> findByDisponibleTrue();
    
}
