package com.tienda.sucursal_service.service;

import com.tienda.sucursal_service.entity.Sucursal;
import com.tienda.sucursal_service.repository.SucursalRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SucursalService {

    private static final Logger log = LoggerFactory.getLogger(SucursalService.class);
    private final SucursalRepository sucursalRepository;

    public Sucursal guardar(Sucursal sucursal) {
        log.info("Guardando sucursal: {}", sucursal.getNombre());
        return sucursalRepository.save(sucursal);
    }

    public List<Sucursal> listarTodas() {
        log.info("Listando todas las sucursales");
        return sucursalRepository.findAll();
    }

    public Sucursal findById(Long id) {
        log.info("Buscando sucursalID: {}", id);
        return sucursalRepository.findById(id)
            .orElseThrow(() -> {
                log.error("SucursalID {} no encontrado", id);
                return new RuntimeException("La sucursa ID no existe en el sistema");
            });
    }

    public void eliminar(Long id) {
        log.info("Eliminando sucursal ID: {}", id);
        sucursalRepository.deleteById(id);
    }
}
