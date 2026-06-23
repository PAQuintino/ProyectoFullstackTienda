package com.tienda.pago_service;

import com.tienda.pago_service.entity.Pago;
import com.tienda.pago_service.repository.PagoRepository;
import com.tienda.pago_service.service.PagoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagoServiceApplicationTests {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    private Pago pago;

    @BeforeEach
    void setUp() {
        pago = new Pago();
        pago.setId(1L);
        pago.setPedidoId(10L);
        pago.setMonto(new BigDecimal("19990"));
        pago.setMetodoPago("TARJETA");
        pago.setEstado("APROBADO");
        pago.setTipo("DEBITO");
    }

    @Test
    void crearPago_deberiaRetornarPagoGuardado() {
        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);
        Pago resultado = pagoService.crearPago(pago);
        assertNotNull(resultado);
        assertEquals("APROBADO", resultado.getEstado());
        verify(pagoRepository, times(1)).save(pago);
    }

    @Test
    void listarTodos_deberiaRetornarListaDePagos() {
        when(pagoRepository.findAll()).thenReturn(Arrays.asList(pago));
        List<Pago> resultado = pagoService.listarTodos();
        assertEquals(1, resultado.size());
        verify(pagoRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorId_cuandoExiste_deberiaRetornarPago() {
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));
        Pago resultado = pagoService.obtenerPorId(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void obtenerPorId_cuandoNoExiste_deberiaLanzarExcepcion() {
        when(pagoRepository.findById(99L)).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () -> pagoService.obtenerPorId(99L));
        assertEquals("Pago no encontrado", ex.getMessage());
    }

    @Test
    void actualizar_cuandoExiste_deberiaActualizarYRetornar() {
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));
        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);
        Pago resultado = pagoService.actualizar(1L, pago);
        assertNotNull(resultado);
        verify(pagoRepository, times(1)).save(pago);
    }

    @Test
    void actualizar_cuandoNoExiste_deberiaLanzarExcepcion() {
        when(pagoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> pagoService.actualizar(99L, pago));
    }

    @Test
    void eliminar_deberiaLlamarDeleteById() {
        doNothing().when(pagoRepository).deleteById(1L);
        pagoService.eliminar(1L);
        verify(pagoRepository, times(1)).deleteById(1L);
    }
}
