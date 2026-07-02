package com.tienda.sucursal_service.assemblers;

import com.tienda.sucursal_service.controller.SucursalControllerV2;
import com.tienda.sucursal_service.entity.Sucursal;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SucursalModelAssembler implements RepresentationModelAssembler<Sucursal, EntityModel<Sucursal>> {

    @Override
    public EntityModel<Sucursal> toModel(Sucursal sucursal) {
        return EntityModel.of(sucursal,
            linkTo(methodOn(SucursalControllerV2.class).getSucursalById(sucursal.getId())).withSelfRel(),
            linkTo(methodOn(SucursalControllerV2.class).getAllSucursales()).withRel("sucursales"));
    }
}