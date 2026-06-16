package com.tienda.notificacion_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;

    @NotBlank
    private String tipo;

    @NotBlank
    private String mensaje;

    private Boolean leido;

    private LocalDateTime fecha;
}