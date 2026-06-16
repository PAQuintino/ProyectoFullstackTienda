package com.tienda.cliente_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, message = "minimo requerido de 3 caracteres")
    private String nombre;

    @NotBlank
    @Size(min = 11, message = "el numero debe tener 8 digitos, más los digitos de zona (569)")
    private String telefono;

    @Email(message = "El formato del email no es válido")
    @Size(min = 10, message = "minimo requerido de 10 caracteres")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}