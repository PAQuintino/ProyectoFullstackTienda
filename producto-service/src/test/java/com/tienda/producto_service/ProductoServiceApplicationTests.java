package com.tienda.producto_service;

import com.tienda.producto_service.entity.Producto;
import com.tienda.producto_service.repository.ProductoRepository;
import com.tienda.producto_service.service.ProductoService;
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
class ProductoServiceApplicationTests {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Notebook HP");
        producto.setPrecio(new BigDecimal("599990"));
        producto.setCategoriaId(1L);
    }

    @Test
    void guardarProducto_deberiaRetornarProductoGuardado() {
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        Producto resultado = productoService.guardarProducto(producto);
        assertNotNull(resultado);
        assertEquals("Notebook HP", resultado.getNombre());
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void listarTodos_deberiaRetornarListaDeProductos() {
        Producto producto2 = new Producto();
        producto2.setId(2L);
        producto2.setNombre("Mouse Logitech");
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto, producto2));
        List<Producto> resultado = productoService.listarTodos();
        assertEquals(2, resultado.size());
    }

    @Test
    void buscarPorId_cuandoProductoExiste_deberiaRetornarProducto() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        Producto resultado = productoService.buscarPorId(1L);
        assertEquals("Notebook HP", resultado.getNombre());
    }

    @Test
    void buscarPorId_cuandoProductoNoExiste_deberiaLanzarExcepcion() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> productoService.buscarPorId(99L));
    }

    @Test
    void existe_cuandoProductoExiste_deberiaRetornarTrue() {
        when(productoRepository.existsById(1L)).thenReturn(true);
        assertTrue(productoService.existe(1L));
    }

    @Test
    void actualizar_cuandoProductoExiste_deberiaActualizarYRetornar() {
        Producto nuevo = new Producto();
        nuevo.setNombre("Notebook Lenovo");
        nuevo.setPrecio(new BigDecimal("799990"));
        nuevo.setCategoriaId(2L);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        Producto resultado = productoService.actualizar(1L, nuevo);
        assertEquals("Notebook Lenovo", resultado.getNombre());
    }

    @Test
    void actualizar_cuandoProductoNoExiste_deberiaLanzarExcepcion() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> productoService.actualizar(99L, new Producto()));
    }

    @Test
    void eliminar_deberiaLlamarDeleteById() {
        doNothing().when(productoRepository).deleteById(1L);
        productoService.eliminar(1L);
        verify(productoRepository, times(1)).deleteById(1L);
    }
}
