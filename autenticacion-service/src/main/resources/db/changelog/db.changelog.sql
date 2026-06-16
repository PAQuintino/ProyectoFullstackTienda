-- liquibase formatted sql

-- changeset pablo:1
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL
);

-- changeset pablo:2
INSERT INTO usuarios (email, password, rol) VALUES ('admin@tienda.com', '1234', 'ADMIN');
INSERT INTO usuarios (email, password, rol) VALUES ('pablo@tienda.com', '1234', 'CLIENTE');
INSERT INTO usuarios (email, password, rol) VALUES ('juan@tienda.com', '1234', 'CLIENTE');
INSERT INTO usuarios (email, password, rol) VALUES ('maria@tienda.com', '1234', 'CLIENTE');
INSERT INTO usuarios (email, password, rol) VALUES ('carlos@tienda.com', '1234', 'CLIENTE');