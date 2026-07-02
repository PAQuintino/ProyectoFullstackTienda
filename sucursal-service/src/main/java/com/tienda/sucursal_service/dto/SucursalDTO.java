package com.tienda.sucursal_service.dto;

import com.tienda.sucursal_service.entity.Sucursal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalDTO {
    
    private Long id;
    private String nombre;
    private String direccion;
    private String comuna;
    private String telefono;
    private String encargado;

    public Sucursal toModel() {
        return new Sucursal();
    }
    
    public static SucursalDTO fromModel(com.tienda.sucursal_service.entity.Sucursal s){
        if (s == null) return null;
        return new SucursalDTO(s.getId(), s.getNombre(), s.getDireccion(), s.getComuna(), s.getTelefono(), s.getEncargado());
    }
}
