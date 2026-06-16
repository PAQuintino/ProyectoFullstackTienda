package com.tienda.notificacion_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tienda.notificacion_service.entity.Notificacion;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long>{
    List<Notificacion> findByClienteId(Long clienteId);
    List<Notificacion> findByLeido(Boolean leido);
}
