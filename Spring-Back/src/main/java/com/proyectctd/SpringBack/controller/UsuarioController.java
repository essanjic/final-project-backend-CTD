package com.proyectctd.SpringBack.controller;


import com.amazonaws.services.alexaforbusiness.model.NotFoundException;
import com.proyectctd.SpringBack.domain.Rol;
import com.proyectctd.SpringBack.domain.Usuario;
import com.proyectctd.SpringBack.dto.UsuarioDTO;
import com.proyectctd.SpringBack.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/usuarios")
@CrossOrigin("http://localhost:5173/")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }



    //LISTAR

    @GetMapping("/auth-usuarios/list")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }



    //buscar por id no DTO
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Integer id) {
        Optional<Usuario> usuarioBuscado = usuarioService.findByID(id);
        if (usuarioBuscado.isPresent()) {
            return ResponseEntity.ok(usuarioBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //buscar por email

    @GetMapping("/buscar/{username}")
    public ResponseEntity<?> buscarUsuarioPorNombre(@PathVariable String username) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorEmail(username);
            return ResponseEntity.ok(usuario);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }



    //ELIMINAR METODO DE AMBOS
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    //cambiar estado de usuario comun
    @PostMapping("/{id}/cambiar-rol")
    public ResponseEntity<Usuario> cambiarRolUsuario(@PathVariable int id) {
        Usuario usuarioActualizado = usuarioService.cambiarRolUsuario(id);
        return ResponseEntity.ok(usuarioActualizado);
    }


    //modificar USER x ADMIN

    @PostMapping("/cambiar-rol")
    public ResponseEntity<String> cambiarRol(@RequestParam("userId") int userId, @RequestParam("nuevoRol") Rol nuevoRol) {
        boolean actualizado = usuarioService.cambiarRol(userId, nuevoRol);
        if (actualizado) {
            return ResponseEntity.ok("Rol de usuario actualizado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el rol de usuario.");
        }
    }
}
