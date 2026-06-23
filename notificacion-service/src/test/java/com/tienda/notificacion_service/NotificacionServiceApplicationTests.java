package com.tienda.notificacion_service;

import com.tienda.notificacion_service.entity.Notificacion;
import com.tienda.notificacion_service.repository.NotificacionRepository;
import com.tienda.notificacion_service.service.NotificacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificacionServiceApplicationTests {

    @Mock
    private NotificacionRepository notificacionRepository;

    @InjectMocks
    private NotificacionService notificacionService;

    private Notificacion notificacion;

    @BeforeEach
    void setUp() {
        notificacion = new Notificacion();
        notificacion.setId(1L);
        notificacion.setClienteId(1L);
        notificacion.setTipo("EMAIL");
        notificacion.setMensaje("Pedido confirmado");
        notificacion.setLeido(false);
        notificacion.setFecha(LocalDateTime.now());
    }

    @Test
    void listarTodos_deberiaRetornarLista() {
        when(notificacionRepository.findAll()).thenReturn(Arrays.asList(notificacion));
        List<Notificacion> resultado = notificacionService.listarTodos();
        assertEquals(1, resultado.size());
    }

    @Test
    void obtenerPorId_cuandoExiste_deberiaRetornarNotificacion() {
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notificacion));
        Notificacion resultado = notificacionService.obtenerPorId(1L);
        assertEquals("EMAIL", resultado.getTipo());
    }

    @Test
    void obtenerPorId_cuandoNoExiste_deberiaLanzarExcepcion() {
        when(notificacionRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> notificacionService.obtenerPorId(99L));
    }

    @Test
    void crear_deberiaGuardarYRetornarNotificacion() {
        when(notificacionRepository.save(any(Notificacion.class))).thenReturn(notificacion);
        Notificacion resultado = notificacionService.crear(notificacion);
        assertNotNull(resultado);
        assertFalse(resultado.getLeido());
        verify(notificacionRepository, times(1)).save(notificacion);
    }

    @Test
    void actualizar_cuandoExiste_deberiaActualizar() {
        Notificacion nueva = new Notificacion();
        nueva.setClienteId(2L);
        nueva.setTipo("SMS");
        nueva.setMensaje("Actualizado");
        nueva.setLeido(true);
        nueva.setFecha(LocalDateTime.now());
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notificacion));
        when(notificacionRepository.save(any(Notificacion.class))).thenReturn(notificacion);
        Notificacion resultado = notificacionService.actualizar(1L, nueva);
        assertEquals("SMS", resultado.getTipo());
        assertEquals("Actualizado", resultado.getMensaje());
    }

    @Test
    void eliminar_deberiaLlamarDeleteById() {
        doNothing().when(notificacionRepository).deleteById(1L);
        notificacionService.eliminar(1L);
        verify(notificacionRepository, times(1)).deleteById(1L);
    }
}
