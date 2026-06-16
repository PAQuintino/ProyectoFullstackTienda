package com.tienda.cliente_service.controller;

import com.tienda.cliente_service.assembler.ClienteModelAssembler;
import com.tienda.cliente_service.entity.Cliente;
import com.tienda.cliente_service.service.ClienteService;
import lombok.RequiredArgsConstructor;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/clientes")
@RequiredArgsConstructor
public class ClienteControllerV2 {

    private final ClienteService clienteService;
    private final ClienteModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Cliente>> listarClientes() {

        List<EntityModel<Cliente>> clientes = clienteService.listarTodos()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                clientes,
                linkTo(methodOn(ClienteControllerV2.class)
                        .listarClientes())
                        .withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<Cliente> obtenerCliente(@PathVariable Long id) {

        Cliente cliente = clienteService.buscarPorId(id);

        return assembler.toModel(cliente);
    }
}