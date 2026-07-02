package com.tienda.sucursal_service.controller;

import com.tienda.sucursal_service.dto.SucursalDTO;
import com.tienda.sucursal_service.entity.Sucursal;
import com.tienda.sucursal_service.service.SucursalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
@RequiredArgsConstructor
@Tag(name = "Sucursales", description = "Gestion de sucursales")
public class SucursalController {

    private final SucursalService sucursalService;

    @PostMapping
    @Operation(summary = "Guardar una nueva sucursal")
    public ResponseEntity<Sucursal> guardar(@Valid @RequestBody Sucursal sucursal) {
        return ResponseEntity.status(201).body(sucursalService.guardar(sucursal));
    }

    @GetMapping
    @Operation(summary = "Listar todas las sucursales")
    public ResponseEntity<List<Sucursal>> listarTodas() {
        return ResponseEntity.ok(sucursalService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar sucursal por ID (retorna DTO)")
    public ResponseEntity<SucursalDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(SucursalDTO.fromModel(sucursalService.findById(id)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar sucursal por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        sucursalService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}