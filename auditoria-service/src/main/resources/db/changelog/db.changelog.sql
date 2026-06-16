-- liquibase formatted sql

-- changeset pablo:1
CREATE TABLE auditoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    servicio VARCHAR(100) NOT NULL,
    accion VARCHAR(100) NOT NULL,
    descripcion TEXT,
    fecha DATETIME NOT NULL
);

-- changeset pablo:2
INSERT INTO auditoria (servicio, accion, descripcion, fecha) VALUES ('cliente-service', 'CREATE', 'Cliente Pablo Quintino creado', '2026-01-15 10:30:00');
INSERT INTO auditoria (servicio, accion, descripcion, fecha) VALUES ('producto-service', 'CREATE', 'Producto Laptop HP creado', '2026-01-15 11:00:00');
INSERT INTO auditoria (servicio, accion, descripcion, fecha) VALUES ('pedido-service', 'CREATE', 'Pedido 1 creado por cliente 1', '2026-01-15 12:00:00');
INSERT INTO auditoria (servicio, accion, descripcion, fecha) VALUES ('pago-service', 'CREATE', 'Pago de pedido 1 procesado', '2026-01-15 12:05:00');
INSERT INTO auditoria (servicio, accion, descripcion, fecha) VALUES ('pedido-service', 'UPDATE', 'Pedido 1 cambió a COMPLETADO', '2026-01-15 13:00:00');