package com.tienda.sucursal_service.repository;

import com.tienda.sucursal_service.entity.Sucursal;


import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}
