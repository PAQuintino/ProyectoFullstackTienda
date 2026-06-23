package com.tienda.auditoria_service;

import com.tienda.auditoria_service.entity.Auditoria;
import com.tienda.auditoria_service.repository.AuditoriaRepository;
import com.tienda.auditoria_service.service.AuditoriaService;
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
class AuditoriaServiceApplicationTests {

    @Mock
    private AuditoriaRepository auditoriaRepository;

    @InjectMocks
    private AuditoriaService auditoriaService;

    private Auditoria auditoria;

    @BeforeEach
    void setUp() {
        auditoria = new Auditoria();
        auditoria.setId(1L);
        auditoria.setServicio("cliente-service");
        auditoria.setAccion("CREAR");
        auditoria.setDescripcion("Cliente creado");
        auditoria.setFecha(LocalDateTime.now());
    }

    @Test
    void listarTodos_deberiaRetornarLista() {
        when(auditoriaRepository.findAll()).thenReturn(Arrays.asList(auditoria));
        List<Auditoria> resultado = auditoriaService.listarTodos();
        assertEquals(1, resultado.size());
    }

    @Test
    void obtenerPorId_cuandoExiste_deberiaRetornarAuditoria() {
        when(auditoriaRepository.findById(1L)).thenReturn(Optional.of(auditoria));
        Auditoria resultado = auditoriaService.obtenerPorId(1L);
        assertEquals("CREAR", resultado.getAccion());
    }

    @Test
    void obtenerPorId_cuandoNoExiste_deberiaLanzarExcepcion() {
        when(auditoriaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> auditoriaService.obtenerPorId(99L));
    }

    @Test
    void registrar_deberiaCrearYGuardarAuditoria() {
        when(auditoriaRepository.save(any(Auditoria.class))).thenReturn(auditoria);
        Auditoria resultado = auditoriaService.registrar("cliente-service", "CREAR", "Cliente creado");
        assertNotNull(resultado);
        verify(auditoriaRepository, times(1)).save(any(Auditoria.class));
    }

    @Test
    void eliminar_deberiaLlamarDeleteById() {
        doNothing().when(auditoriaRepository).deleteById(1L);
        auditoriaService.eliminar(1L);
        verify(auditoriaRepository, times(1)).deleteById(1L);
    }

    @Test
    void buscarPorServicio_deberiaRetornarLista() {
        when(auditoriaRepository.findByServicio("cliente-service")).thenReturn(Arrays.asList(auditoria));
        List<Auditoria> resultado = auditoriaService.buscarPorServicio("cliente-service");
        assertEquals(1, resultado.size());
    }

    @Test
    void buscarPorAccion_deberiaRetornarLista() {
        when(auditoriaRepository.findByAccion("CREAR")).thenReturn(Arrays.asList(auditoria));
        List<Auditoria> resultado = auditoriaService.buscarPorAccion("CREAR");
        assertEquals(1, resultado.size());
    }

    @Test
    void listarPorAccion_deberiaRetornarLista() {
        when(auditoriaRepository.findByAccion("CREAR")).thenReturn(Arrays.asList(auditoria));
        List<Auditoria> resultado = auditoriaService.listarPorAccion("CREAR");
        assertEquals(1, resultado.size());
    }
}
