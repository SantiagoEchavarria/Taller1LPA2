package com.example.libreria.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_prestamo", nullable = false)
    @NotNull(message = "La fecha de préstamo es obligatoria")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaPrestamo;

    @Column(name = "fecha_devolucion", nullable = false)
    @NotNull(message = "La fecha de devolución es obligatoria")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @FutureOrPresent(message = "La fecha de devolución debe ser hoy o una fecha futura")
    private LocalDate fechaDevolucion;

    @Column(length = 255)
    @Size(max = 255, message = "La observación no puede superar los 255 caracteres")
    private String observacion;

    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    @JsonIgnore //Estfo es para evitar la recursividad infinita al serializar a JSON
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    
    public Prestamo(Long id, LocalDate fechaPrestamo, LocalDate fechaDevolucion, String observacion, Libro libro,
            Usuario usuario) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.observacion = observacion;
        this.libro = libro;
        this.usuario = usuario;
    }

    public Prestamo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public long getDiasRestantes() {
        return ChronoUnit.DAYS.between(LocalDate.now(), this.fechaDevolucion);
    }

    @Override
    public String toString() {
        return "Prestamo [id=" + id + ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion
                + ", observacion=" + observacion + ", libro=" + libro + ", usuario=" + usuario + "]";
    }

    
}

