package com.gestion.gestion_usuarios.controladores;

import com.gestion.gestion_usuarios.dtos.ActualizarContrasenaDto;
import com.gestion.gestion_usuarios.servicios.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recuperar")
public class RecuperacionContrasenaControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    /**
     * Endpoint para actualizar la contraseña usando un token.
     * 
     * @param dto Contiene el token y la nueva contraseña.
     * @return Mensaje de éxito o error.
     */
    @PostMapping("/cambiar")
    public ResponseEntity<String> cambiarContrasena(@RequestBody ActualizarContrasenaDto dto) {
        try {
            usuarioServicio.actualizarContrasenaConToken(dto.getToken(), dto.getNuevaContrasena());
            return ResponseEntity.ok("Contraseña actualizada correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor.");
        }
    }
}
