package com.tienda.inventario_service;

import com.tienda.inventario_service.entity.Stock;
import com.tienda.inventario_service.repository.StockRepository;
import com.tienda.inventario_service.service.InventarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventarioServiceApplicationTests {

    @Mock
    private StockRepository stockRepository;

    @Mock
    private WebClient.Builder webClientBuilder;

    @InjectMocks
    private InventarioService inventarioService;

    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock();
        stock.setId(1L);
        stock.setProductoId(1L);
        stock.setCantidad(100);
        stock.setAlmacenId(1L);
    }

    @Test
    void actualizar_cuandoExiste_deberiaActualizar() {
        Stock nuevo = new Stock();
        nuevo.setProductoId(2L);
        nuevo.setCantidad(200);
        nuevo.setAlmacenId(2L);
        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);
        Stock resultado = inventarioService.actualizar(1L, nuevo);
        assertEquals(200, resultado.getCantidad());
    }

    @Test
    void actualizar_cuandoNoExiste_deberiaLanzarExcepcion() {
        Stock nuevo = new Stock();
        when(stockRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> inventarioService.actualizar(99L, nuevo));
    }

    @Test
    void eliminar_deberiaLlamarDeleteById() {
        doNothing().when(stockRepository).deleteById(1L);
        inventarioService.eliminar(1L);
        verify(stockRepository, times(1)).deleteById(1L);
    }

    @Test
    void buscarPorAlmacenId_deberiaRetornarLista() {
        when(stockRepository.findByAlmacenId(1L)).thenReturn(Arrays.asList(stock));
        List<Stock> resultado = inventarioService.buscarPorAlmacenId(1L);
        assertEquals(1, resultado.size());
    }

    @Test
    void buscarPorCantidadMinima_deberiaRetornarLista() {
        when(stockRepository.findByCantidadGreaterThan(50)).thenReturn(Arrays.asList(stock));
        List<Stock> resultado = inventarioService.buscarPorCantidadMinima(50);
        assertEquals(1, resultado.size());
    }
}
