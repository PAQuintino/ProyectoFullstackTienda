--liquibase formatted sql

--changeset pablo:1
CREATE TABLE sucursales (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200),
    comuna VARCHAR(100),
    telefono VARCHAR(20),
    encargado VARCHAR(100)
);

--changeset pablo:2
INSERT INTO sucursales (nombre, direccion, comuna, telefono, encargado) VALUES
('Sucursal Centro', 'Av. Providencia 1234', 'Providencia', '+56223334444', 'Andres Silva'),
('Sucursal Norte', 'Av. Kennedy 5678', 'Las Condes', '+56225556666', 'Carla Diaz'),
('Sucursal Sur', 'Av. Vicuna Mackenna 999', 'La Florida', '+56227778888', 'Luis Rojas');
