package com.tienda.cliente_service.assembler;

import com.tienda.cliente_service.controller.ClienteControllerV2;
import com.tienda.cliente_service.entity.Cliente;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {

    @Override
    public EntityModel<Cliente> toModel(Cliente cliente) {

        return EntityModel.of(
                cliente,

                linkTo(
                        methodOn(ClienteControllerV2.class)
                                .obtenerCliente(cliente.getId())
                ).withSelfRel(),

                linkTo(
                        methodOn(ClienteControllerV2.class)
                                .listarClientes()
                ).withRel("clientes"),

                Link.of(
                        "http://localhost:8094/api/pedidos/cliente/" + cliente.getId(),
                        "pedidos"));
        }
}