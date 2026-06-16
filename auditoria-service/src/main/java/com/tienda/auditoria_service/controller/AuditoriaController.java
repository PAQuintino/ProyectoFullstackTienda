package com.tienda.auditoria_service.controller;

import com.tienda.auditoria_service.entity.Auditoria;
import com.tienda.auditoria_service.service.AuditoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditoria")
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    public AuditoriaController(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @PostMapping
    public ResponseEntity<Auditoria> registrar(
            @RequestParam String servicio,
            @RequestParam String accion,
            @RequestParam(required = false) String descripcion) {
        return ResponseEntity.ok(auditoriaService.registrar(servicio, accion, descripcion));
    }

    @GetMapping
    public ResponseEntity<List<Auditoria>> listarTodos() {
        return ResponseEntity.ok(auditoriaService.listarTodos());
    }

    @GetMapping("/accion/{accion}")
    public ResponseEntity<List<Auditoria>> listarPorAccion(@PathVariable String accion) {
        return ResponseEntity.ok(auditoriaService.listarPorAccion(accion));
    }
}
