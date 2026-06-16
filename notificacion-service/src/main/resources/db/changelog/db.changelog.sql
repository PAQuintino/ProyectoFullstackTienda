-- liquibase formatted sql

-- changeset pablo:1
CREATE TABLE notificaciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    mensaje TEXT NOT NULL,
    leido BOOLEAN DEFAULT FALSE,
    fecha DATETIME NOT NULL
);

-- changeset pablo:2
INSERT INTO notificaciones (cliente_id, tipo, mensaje, leido, fecha) VALUES (1, 'PEDIDO_CREADO', 'Tu pedido 1 fue creado exitosamente', false, '2026-01-15 12:00:00');
INSERT INTO notificaciones (cliente_id, tipo, mensaje, leido, fecha) VALUES (1, 'PAGO_APROBADO', 'Tu pago de $899.99 fue aprobado', true, '2026-01-15 12:05:00');
INSERT INTO notificaciones (cliente_id, tipo, mensaje, leido, fecha) VALUES (1, 'PEDIDO_COMPLETADO', 'Tu pedido 1 fue entregado', true, '2026-01-15 13:00:00');
INSERT INTO notificaciones (cliente_id, tipo, mensaje, leido, fecha) VALUES (2, 'PEDIDO_CREADO', 'Tu pedido 2 fue creado exitosamente', false, '2026-02-20 14:00:00');
INSERT INTO notificaciones (cliente_id, tipo, mensaje, leido, fecha) VALUES (3, 'PEDIDO_CREADO', 'Tu pedido 3 fue creado exitosamente', false, '2026-03-10 09:00:00');
INSERT INTO notificaciones (cliente_id, tipo, mensaje, leido, fecha) VALUES (2, 'PAGO_RECHAZADO', 'Tu pago fue rechazado, intenta de nuevo', false, '2026-04-15 13:00:00');
INSERT INTO notificaciones (cliente_id, tipo, mensaje, leido, fecha) VALUES (4, 'PEDIDO_CREADO', 'Tu pedido 5 fue creado exitosamente', false, '2026-04-01 11:00:00');
INSERT INTO notificaciones (cliente_id, tipo, mensaje, leido, fecha) VALUES (5, 'PAGO_APROBADO', 'Tu pago de $70.00 fue aprobado', true, '2026-04-10 08:30:00');
INSERT INTO notificaciones (cliente_id, tipo, mensaje, leido, fecha) VALUES (6, 'PEDIDO_ENVIADO', 'Tu pedido 8 está en camino', false, '2026-05-01 10:00:00');
INSERT INTO notificaciones (cliente_id, tipo, mensaje, leido, fecha) VALUES (7, 'PEDIDO_COMPLETADO', 'Tu pedido 10 fue entregado', true, '2026-05-20 12:00:00');