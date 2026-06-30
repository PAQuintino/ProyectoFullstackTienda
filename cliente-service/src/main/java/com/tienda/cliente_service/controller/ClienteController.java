package com.tienda.cliente_service.controller;

import com.tienda.cliente_service.entity.Cliente;
import com.tienda.cliente_service.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping//
    public ResponseEntity<Cliente> guardar(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.status(201).body(clienteService.guardarCliente(cliente));
    }

    @GetMapping("/{id}")//
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @GetMapping//
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{id}/exists")//
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.existe(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.actualizar(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}