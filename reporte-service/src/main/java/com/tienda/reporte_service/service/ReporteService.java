package com.tienda.reporte_service.service;

import com.tienda.reporte_service.entity.Reporte;
import com.tienda.reporte_service.repository.ReporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private final ReporteRepository reporteRepository;

    public List<Reporte> listarTodos() {
        return reporteRepository.findAll();
    }

    public Reporte obtenerPorId(Long id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
    }

    public Reporte crear(Reporte reporte) {
        reporte.setFechaGenerado(LocalDateTime.now());
        return reporteRepository.save(reporte);
    }

    public Reporte actualizar(Long id, Reporte nuevo) {
        Reporte r = obtenerPorId(id);
        r.setTipo(nuevo.getTipo());
        r.setDescripcion(nuevo.getDescripcion());
        return reporteRepository.save(r);
    }

    public void eliminar(Long id) {
        reporteRepository.deleteById(id);
    }
}