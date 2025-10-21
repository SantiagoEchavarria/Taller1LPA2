package com.example.libreria.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.libreria.models.entity.UsuarioR;

public interface UsuarioRDAO extends JpaRepository<UsuarioR, Long>{
    public UsuarioR findByNombre(String nombre);
}
