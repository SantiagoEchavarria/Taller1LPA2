package com.example.libreria.models.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "libros")
public class Libro {

 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    @NotEmpty(message = "El ISBN no puede estar vacío")
    @Size(max = 50, message = "El ISBN no puede superar los 50 caracteres")
    private String isbn;

    @Column(length = 100, nullable = false)
    @NotEmpty(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no puede superar los 100 caracteres")
    private String titulo;

    @Column(length = 100)
    @Size(max = 100, message = "El autor no puede superar los 100 caracteres")
    private String autor;

    @Column(length = 50)
    @Size(max = 50, message = "La editorial no puede superar los 50 caracteres")
    private String editorial;

    @Column(length = 10)
    @Pattern(regexp = "^[0-9]{4}$", message = "El año debe ser un valor numérico de 4 dígitos")
    private String anio;

    @Column(length = 30)
    @Size(max = 30, message = "La edición no puede superar los 30 caracteres")
    private String edicion;


    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore //Esto es para evitar la recursividad infinita al serializar a JSON
    private List<Prestamo> prestamos;

    

    public Libro(Long id, String isbn, String titulo, String autor, String editorial, String anio, String edicion,
            List<Prestamo> prestamos) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
        this.edicion = edicion;
        this.prestamos = prestamos;
    }

    public Libro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    @Override
    public String toString() {
        return "Libro [id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", editorial="
                + editorial + ", anio=" + anio + ", edicion=" + edicion + ", prestamos=" + prestamos + "]";
    }

    // Getters y Setters
}

