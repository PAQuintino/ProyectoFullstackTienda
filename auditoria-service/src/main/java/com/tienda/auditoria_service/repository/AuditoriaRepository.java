package com.tienda.auditoria_service.repository;

import com.tienda.auditoria_service.entity.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {

    List<Auditoria> findByAccion(String accion);
    List<Auditoria> findByServicio(String servicio);
}
