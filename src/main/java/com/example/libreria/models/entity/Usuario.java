package com.example.libreria.models.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    @NotEmpty(message = "La identificación es obligatoria")
    @Size(max = 10, message = "La identificación no puede superar los 10 caracteres")
    private String identificacion;

    @Column(length = 15)
    @Size(max = 15, message = "El teléfono móvil no puede superar los 15 caracteres")
    @Pattern(regexp = "^[0-9+\\-\\s]*$", message = "El teléfono solo puede contener números, espacios, '+' o '-'")
    private String telefonoMovil;

    @Column(length = 80, nullable = false)
    @NotEmpty(message = "El nombre completo es obligatorio")
    @Size(max = 80, message = "El nombre completo no puede superar los 80 caracteres")
    private String nombreCompleto;

    @Column(length = 80, unique = true, nullable = false)
    @NotEmpty(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe tener un formato válido")
    @Size(max = 80, message = "El correo electrónico no puede superar los 80 caracteres")
    private String correoElectronico;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prestamo> prestamos;

    public Usuario(Long id, String identificacion, String telefonoMovil, String nombreCompleto,
            String correoElectronico, List<Prestamo> prestamos) {
        this.id = id;
        this.identificacion = identificacion;
        this.telefonoMovil = telefonoMovil;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.prestamos = prestamos;
    }
    public Usuario() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    public String getTelefonoMovil() {
        return telefonoMovil;
    }
    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    public List<Prestamo> getPrestamos() {
        return prestamos;
    }
    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", identificacion=" + identificacion + ", telefonoMovil=" + telefonoMovil
                + ", nombreCompleto=" + nombreCompleto + ", correoElectronico=" + correoElectronico + ", prestamos="
                + prestamos + "]";
    }
    
}
