package com.tienda.autenticacion_service.controller;

import com.tienda.autenticacion_service.dto.LoginRequest;
import com.tienda.autenticacion_service.dto.LoginResponse;
import com.tienda.autenticacion_service.entity.Usuario;
import com.tienda.autenticacion_service.repository.UsuarioRepository;
import com.tienda.autenticacion_service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (usuario == null || !usuario.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        String token = jwtService.generarToken(usuario.getEmail(), usuario.getRol());
        return ResponseEntity.ok(new LoginResponse(token, usuario.getEmail(), usuario.getRol()));
    }
}