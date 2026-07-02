package com.tienda.sucursal_service.controller;

import com.tienda.sucursal_service.assemblers.SucursalModelAssembler;
import com.tienda.sucursal_service.entity.Sucursal;
import com.tienda.sucursal_service.service.SucursalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/sucursales")
@RequiredArgsConstructor
@Tag(name = "Sucursales V2 (HATEOAS)", description = "Version con hipermedia")
public class SucursalControllerV2 {

    private final SucursalService sucursalService;
    private final SucursalModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Listar todas las sucursales con links HATEOAS")
    public CollectionModel<EntityModel<Sucursal>> getAllSucursales() {
        List<EntityModel<Sucursal>> sucursales = sucursalService.listarTodas().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(sucursales,
                linkTo(methodOn(SucursalControllerV2.class).getAllSucursales()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Buscar sucursal por ID con links HATEOAS")
    public EntityModel<Sucursal> getSucursalById(@PathVariable Long id) {
        return assembler.toModel(sucursalService.findById(id));
    }

}
