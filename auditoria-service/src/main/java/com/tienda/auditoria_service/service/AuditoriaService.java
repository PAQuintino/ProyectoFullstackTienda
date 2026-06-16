package com.tienda.auditoria_service.service;

import com.tienda.auditoria_service.entity.Auditoria;
import com.tienda.auditoria_service.repository.AuditoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;

    public List<Auditoria> listarTodos() {
        return auditoriaRepository.findAll();
    }

    public Auditoria obtenerPorId(Long id) {
        return auditoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    }

    public Auditoria registrar(String servicio, String accion, String descripcion) {
        Auditoria auditoria = new Auditoria();
        auditoria.setServicio(servicio);
        auditoria.setAccion(accion);
        auditoria.setDescripcion(descripcion);
        auditoria.setFecha(LocalDateTime.now());
        return auditoriaRepository.save(auditoria);
    }

    public void eliminar(Long id) {
        auditoriaRepository.deleteById(id);
    }

    public List<Auditoria> buscarPorServicio(String servicio) {
        return auditoriaRepository.findByServicio(servicio);
    }

    public List<Auditoria> buscarPorAccion(String accion) {
        return auditoriaRepository.findByAccion(accion);
    }

    public List<Auditoria> listarPorAccion(String accion) {
        return auditoriaRepository.findByAccion(accion);
    }
}