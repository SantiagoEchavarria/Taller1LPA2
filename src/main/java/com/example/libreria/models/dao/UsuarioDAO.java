package com.example.libreria.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.libreria.models.entity.Usuario;
@Repository
public interface UsuarioDAO extends JpaRepository <Usuario, Long> {
    
}
