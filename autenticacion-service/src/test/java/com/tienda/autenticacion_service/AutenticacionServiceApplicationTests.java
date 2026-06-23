package com.tienda.autenticacion_service;

import com.tienda.autenticacion_service.entity.Usuario;
import com.tienda.autenticacion_service.repository.UsuarioRepository;
import com.tienda.autenticacion_service.service.UsuarioService;
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
class AutenticacionServiceApplicationTests {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("admin@tienda.com");
        usuario.setPassword("1234");
        usuario.setRol("ADMIN");
    }

    @Test
    void listarTodos_deberiaRetornarListaDeUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario));
        List<Usuario> resultado = usuarioService.listarTodos();
        assertEquals(1, resultado.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorId_cuandoExiste_deberiaRetornarUsuario() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Usuario resultado = usuarioService.obtenerPorId(1L);
        assertEquals("admin@tienda.com", resultado.getEmail());
    }

    @Test
    void obtenerPorId_cuandoNoExiste_deberiaLanzarExcepcion() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> usuarioService.obtenerPorId(99L));
    }

    @Test
    void obtenerPorEmail_cuandoExiste_deberiaRetornarUsuario() {
        when(usuarioRepository.findByEmail("admin@tienda.com")).thenReturn(Optional.of(usuario));
        Usuario resultado = usuarioService.obtenerPorEmail("admin@tienda.com");
        assertEquals("ADMIN", resultado.getRol());
    }

    @Test
    void obtenerPorEmail_cuandoNoExiste_deberiaLanzarExcepcion() {
        when(usuarioRepository.findByEmail("nope@test.com")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> usuarioService.obtenerPorEmail("nope@test.com"));
    }

    @Test
    void crearUsuario_deberiaRetornarUsuarioGuardado() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario resultado = usuarioService.crearUsuario(usuario);
        assertEquals("admin@tienda.com", resultado.getEmail());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void actualizarUsuario_cuandoExiste_deberiaActualizar() {
        Usuario nuevo = new Usuario();
        nuevo.setEmail("nuevo@tienda.com");
        nuevo.setPassword("5678");
        nuevo.setRol("USER");
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario resultado = usuarioService.actualizarUsuario(1L, nuevo);
        assertEquals("nuevo@tienda.com", resultado.getEmail());
        assertEquals("USER", resultado.getRol());
    }

    @Test
    void eliminarPorId_deberiaLlamarDeleteById() {
        doNothing().when(usuarioRepository).deleteById(1L);
        usuarioService.eliminarPorId(1L);
        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void buscarPorRol_deberiaRetornarListaFiltrada() {
        when(usuarioRepository.findByRol("ADMIN")).thenReturn(Arrays.asList(usuario));
        List<Usuario> resultado = usuarioService.buscarPorRol("ADMIN");
        assertEquals(1, resultado.size());
        verify(usuarioRepository, times(1)).findByRol("ADMIN");
    }
}
