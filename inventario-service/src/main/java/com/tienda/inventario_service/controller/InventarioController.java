package com.tienda.inventario_service.controller;

import com.tienda.inventario_service.dto.StockResponseDTO;
import com.tienda.inventario_service.entity.Stock;
import com.tienda.inventario_service.service.InventarioService;
import com.tienda.inventario_service.repository.StockRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventarios")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;
    private final StockRepository stockRepository;

    @PostMapping("/stock")
    public Mono<ResponseEntity<StockResponseDTO>> registrarStock(@Valid @RequestBody Stock stock) {
        return inventarioService.registrarStock(stock)
                .map(guardado -> {
                    StockResponseDTO response = new StockResponseDTO();
                    response.setId(guardado.getId());
                    response.setCantidad(guardado.getCantidad());
                    return ResponseEntity.ok(response);
                });
    }

    @GetMapping
    public List<Stock> listarTodos() {
        return stockRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Stock> obtenerPorId(@PathVariable Long id) {
        return stockRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> actualizar(@PathVariable Long id, @Valid @RequestBody Stock stock) {
        return ResponseEntity.ok(inventarioService.actualizar(id, stock));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/almacen/{almacenId}")
    public ResponseEntity<List<Stock>> buscarPorAlmacen(@PathVariable Long almacenId) {
        return ResponseEntity.ok(inventarioService.buscarPorAlmacenId(almacenId));
    }

    @GetMapping("/cantidad-mayor/{cantidad}")
    public ResponseEntity<List<Stock>> buscarPorCantidadMinima(@PathVariable Integer cantidad) {
        return ResponseEntity.ok(inventarioService.buscarPorCantidadMinima(cantidad));
    }
    
}
