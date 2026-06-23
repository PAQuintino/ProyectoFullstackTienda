package com.tienda.pedido_service;

import com.tienda.pedido_service.entity.Pedido;
import com.tienda.pedido_service.repository.PedidoRepository;
import com.tienda.pedido_service.service.PedidoService;
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
class PedidoServiceApplicationTests {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private WebClient.Builder webClientBuilder;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        pedido.setId(1L);
        pedido.setClienteId(1L);
        pedido.setProductoId(1L);
        pedido.setCantidad(2);
        pedido.setEstado("PENDIENTE");
    }

    @Test
    void crearPedido_deberiaRetornarPedidoGuardado() {
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);
        Pedido resultado = pedidoService.crearPedido(pedido);
        assertNotNull(resultado);
        assertEquals("PENDIENTE", resultado.getEstado());
    }

    @Test
    void listarTodos_deberiaRetornarLista() {
        when(pedidoRepository.findAll()).thenReturn(Arrays.asList(pedido));
        List<Pedido> resultado = pedidoService.listarTodos();
        assertEquals(1, resultado.size());
    }

    @Test
    void obtenerPorId_cuandoExiste_deberiaRetornarPedido() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        Pedido resultado = pedidoService.obtenerPorId(1L);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void obtenerPorId_cuandoNoExiste_deberiaLanzarExcepcion() {
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> pedidoService.obtenerPorId(99L));
    }

    @Test
    void actualizar_cuandoExiste_deberiaActualizar() {
        Pedido nuevo = new Pedido();
        nuevo.setClienteId(2L);
        nuevo.setProductoId(2L);
        nuevo.setCantidad(5);
        nuevo.setEstado("ENVIADO");
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);
        Pedido resultado = pedidoService.actualizar(1L, nuevo);
        assertEquals("ENVIADO", resultado.getEstado());
        assertEquals(5, resultado.getCantidad());
    }

    @Test
    void actualizar_cuandoNoExiste_deberiaLanzarExcepcion() {
        Pedido nuevo = new Pedido();
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> pedidoService.actualizar(99L, nuevo));
    }

    @Test
    void eliminar_deberiaLlamarDeleteById() {
        doNothing().when(pedidoRepository).deleteById(1L);
        pedidoService.eliminar(1L);
        verify(pedidoRepository, times(1)).deleteById(1L);
    }

    @Test
    void buscarPorClienteId_deberiaRetornarLista() {
        when(pedidoRepository.findByClienteId(1L)).thenReturn(Arrays.asList(pedido));
        List<Pedido> resultado = pedidoService.buscarPorClienteId(1L);
        assertEquals(1, resultado.size());
    }

    @Test
    void buscarPorEstado_deberiaRetornarLista() {
        when(pedidoRepository.findByEstado("PENDIENTE")).thenReturn(Arrays.asList(pedido));
        List<Pedido> resultado = pedidoService.buscarPorEstado("PENDIENTE");
        assertEquals(1, resultado.size());
    }
}
