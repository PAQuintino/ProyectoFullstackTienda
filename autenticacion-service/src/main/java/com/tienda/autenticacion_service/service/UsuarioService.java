package com.tienda.autenticacion_service.service;

import com.tienda.autenticacion_service.entity.Usuario;
import com.tienda.autenticacion_service.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuarioNuevo) {
        Usuario usuario = obtenerPorId(id);
        usuario.setEmail(usuarioNuevo.getEmail());
        usuario.setPassword(usuarioNuevo.getPassword());
        usuario.setRol(usuarioNuevo.getRol());
        return usuarioRepository.save(usuario);
    }

    public void eliminarPorId(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> buscarPorRol(String rol) {
        return usuarioRepository.findByRol(rol);
    }
}
