-- liquibase formatted sql

-- changeset pablo:1
CREATE TABLE reportes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(100) NOT NULL,
    descripcion TEXT,
    fecha_generado DATETIME NOT NULL
);

-- changeset pablo:2
INSERT INTO reportes (tipo, descripcion, fecha_generado) VALUES ('VENTAS_MENSUAL', 'Reporte de ventas enero 2026', '2026-01-31 23:59:00');
INSERT INTO reportes (tipo, descripcion, fecha_generado) VALUES ('PRODUCTOS_TRENDING', 'Top productos más vendidos enero', '2026-01-31 23:59:00');
INSERT INTO reportes (tipo, descripcion, fecha_generado) VALUES ('VENTAS_MENSUAL', 'Reporte de ventas febrero 2026', '2026-02-28 23:59:00');
INSERT INTO reportes (tipo, descripcion, fecha_generado) VALUES ('CLIENTES_ACTIVOS', 'Clientes con compras en febrero', '2026-02-28 23:59:00');
INSERT INTO reportes (tipo, descripcion, fecha_generado) VALUES ('METODOS_PAGO', 'Métodos de pago más usados febrero', '2026-02-28 23:59:00');
INSERT INTO reportes (tipo, descripcion, fecha_generado) VALUES ('VENTAS_MENSUAL', 'Reporte de ventas marzo 2026', '2026-03-31 23:59:00');
INSERT INTO reportes (tipo, descripcion, fecha_generado) VALUES ('PRODUCTOS_TRENDING', 'Top productos más vendidos marzo', '2026-03-31 23:59:00');
INSERT INTO reportes (tipo, descripcion, fecha_generado) VALUES ('CLIENTES_ACTIVOS', 'Clientes con compras en marzo', '2026-03-31 23:59:00');
INSERT INTO reportes (tipo, descripcion, fecha_generado) VALUES ('VENTAS_MENSUAL', 'Reporte de ventas abril 2026', '2026-04-30 23:59:00');
INSERT INTO reportes (tipo, descripcion, fecha_generado) VALUES ('METODOS_PAGO', 'Métodos de pago más usados abril', '2026-04-30 23:59:00');