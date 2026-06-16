package com.tienda.notificacion_service.service;

import com.tienda.notificacion_service.entity.Notificacion;
import com.tienda.notificacion_service.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> listarTodos() {
        return notificacionRepository.findAll();
    }

    public Notificacion obtenerPorId(Long id) {
        return notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificacion no encontrada"));
    }

    public Notificacion crear(Notificacion notificacion) {
        notificacion.setFecha(LocalDateTime.now());
        notificacion.setLeido(false);
        return notificacionRepository.save(notificacion);
    }

    public Notificacion actualizar(Long id, Notificacion nuevo) {
        Notificacion existente = obtenerPorId(id);
        existente.setClienteId(nuevo.getClienteId());
        existente.setTipo(nuevo.getTipo());
        existente.setMensaje(nuevo.getMensaje());
        existente.setLeido(nuevo.getLeido());
        existente.setFecha(nuevo.getFecha());
        return notificacionRepository.save(existente);
    }

    public void eliminar(Long id) {
        notificacionRepository.deleteById(id);
    }
}
