package com.tienda.reporte_service.controller;

import com.tienda.reporte_service.entity.Reporte;
import com.tienda.reporte_service.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<Reporte>> listarTodos() {
        return ResponseEntity.ok(reporteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reporteService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Reporte> crear(@RequestBody Reporte reporte) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteService.crear(reporte));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reporte> actualizar(@PathVariable Long id, @RequestBody Reporte reporte) {
        return ResponseEntity.ok(reporteService.actualizar(id, reporte));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reporteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}