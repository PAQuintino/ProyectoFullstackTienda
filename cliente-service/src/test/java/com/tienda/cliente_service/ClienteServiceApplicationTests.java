package com.tienda.cliente_service;

import com.tienda.cliente_service.entity.Cliente;
import com.tienda.cliente_service.repository.ClienteRepository;
import com.tienda.cliente_service.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceApplicationTests {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Pablo Quintino");
        cliente.setEmail("pablo@test.com");
        cliente.setTelefono("987654321");
    }

    @Test
    void guardarCliente_deberiaRetornarClienteGuardado() {
        // Given
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        // When
        Cliente resultado = clienteService.guardarCliente(cliente);

        // Then
        assertNotNull(resultado);
        assertEquals("Pablo Quintino", resultado.getNombre());
        assertEquals("pablo@test.com", resultado.getEmail());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void listarTodos_deberiaRetornarListaDeClientes() {
        // Given
        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNombre("Andrés");
        List<Cliente> clientes = Arrays.asList(cliente, cliente2);
        when(clienteRepository.findAll()).thenReturn(clientes);

        // When
        List<Cliente> resultado = clienteService.listarTodos();

        // Then
        assertEquals(2, resultado.size());
        assertEquals("Pablo Quintino", resultado.get(0).getNombre());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void existe_cuandoClienteExiste_deberiaRetornarTrue() {
        // Given
        when(clienteRepository.existsById(1L)).thenReturn(true);

        // When
        Boolean resultado = clienteService.existe(1L);

        // Then
        assertTrue(resultado);
        verify(clienteRepository, times(1)).existsById(1L);
    }

    @Test
    void buscarPorId_cuandoClienteExiste_deberiaRetornarCliente() {
        // Given
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        // When
        Cliente resultado = clienteService.buscarPorId(1L);

        // Then
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Pablo Quintino", resultado.getNombre());
    }

    @Test
    void buscarPorId_cuandoClienteNoExiste_deberiaLanzarExcepcion() {
        // Given
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteService.buscarPorId(99L);
        });

        assertEquals("El cliente con ese ID no existe en el sistema", exception.getMessage());
    }

    @Test
    void actualizar_cuandoClienteExiste_deberiaActualizarYRetornar() {
        // Given
        Cliente clienteNuevo = new Cliente();
        clienteNuevo.setNombre("Pablo Actualizado");
        clienteNuevo.setEmail("nuevo@test.com");
        clienteNuevo.setTelefono("111222333");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        // When
        Cliente resultado = clienteService.actualizar(1L, clienteNuevo);

        // Then
        assertEquals("Pablo Actualizado", resultado.getNombre());
        assertEquals("nuevo@test.com", resultado.getEmail());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void actualizar_cuandoClienteNoExiste_deberiaLanzarExcepcion() {
        // Given
        Cliente clienteNuevo = new Cliente();
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            clienteService.actualizar(99L, clienteNuevo);
        });
    }

    @Test
    void eliminar_deberiaLlamarDeleteById() {
        // Given
        doNothing().when(clienteRepository).deleteById(1L);

        // When
        clienteService.eliminar(1L);

        // Then
        verify(clienteRepository, times(1)).deleteById(1L);
    }
}