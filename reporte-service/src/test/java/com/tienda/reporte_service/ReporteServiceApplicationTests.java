package com.tienda.reporte_service;

import com.tienda.reporte_service.entity.Reporte;
import com.tienda.reporte_service.repository.ReporteRepository;
import com.tienda.reporte_service.service.ReporteService;
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
class ReporteServiceApplicationTests {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private ReporteService reporteService;

    private Reporte reporte;

    @BeforeEach
    void setUp() {
        reporte = new Reporte();
        reporte.setId(1L);
        reporte.setTipo("VENTAS");
        reporte.setDescripcion("Reporte mensual");
        reporte.setFechaGenerado(LocalDateTime.now());
    }

    @Test
    void listarTodos_deberiaRetornarLista() {
        when(reporteRepository.findAll()).thenReturn(Arrays.asList(reporte));
        List<Reporte> resultado = reporteService.listarTodos();
        assertEquals(1, resultado.size());
    }

    @Test
    void obtenerPorId_cuandoExiste_deberiaRetornarReporte() {
        when(reporteRepository.findById(1L)).thenReturn(Optional.of(reporte));
        Reporte resultado = reporteService.obtenerPorId(1L);
        assertEquals("VENTAS", resultado.getTipo());
    }

    @Test
    void obtenerPorId_cuandoNoExiste_deberiaLanzarExcepcion() {
        when(reporteRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> reporteService.obtenerPorId(99L));
    }

    @Test
    void crear_deberiaGuardarYRetornarReporte() {
        when(reporteRepository.save(any(Reporte.class))).thenReturn(reporte);
        Reporte resultado = reporteService.crear(reporte);
        assertNotNull(resultado);
        verify(reporteRepository, times(1)).save(reporte);
    }

    @Test
    void actualizar_cuandoExiste_deberiaActualizar() {
        Reporte nuevo = new Reporte();
        nuevo.setTipo("STOCK");
        nuevo.setDescripcion("Reporte stock");
        when(reporteRepository.findById(1L)).thenReturn(Optional.of(reporte));
        when(reporteRepository.save(any(Reporte.class))).thenReturn(reporte);
        Reporte resultado = reporteService.actualizar(1L, nuevo);
        assertEquals("STOCK", resultado.getTipo());
        assertEquals("Reporte stock", resultado.getDescripcion());
    }

    @Test
    void eliminar_deberiaLlamarDeleteById() {
        doNothing().when(reporteRepository).deleteById(1L);
        reporteService.eliminar(1L);
        verify(reporteRepository, times(1)).deleteById(1L);
    }
}
