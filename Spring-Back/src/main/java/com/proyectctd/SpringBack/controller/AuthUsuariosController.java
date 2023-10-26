package com.proyectctd.SpringBack.controller;


import com.amazonaws.services.alexaforbusiness.model.NotFoundException;
import com.proyectctd.SpringBack.domain.Usuario;
import com.proyectctd.SpringBack.dto.UsuarioDTO;
import com.proyectctd.SpringBack.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/auth")
public class AuthUsuariosController {

    @Autowired
    private final UsuarioService usuarioService;

    public AuthUsuariosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    //eliminar
    @DeleteMapping("/auth-usuarios/delete/{id}")
    public ResponseEntity<String> borrarUsuario(@PathVariable Integer id){
        Optional<Usuario> usuarioEliminar=usuarioService.findByID(id);
        if(usuarioEliminar.isPresent()){
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario borrado");
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //buscar x username

    @GetMapping("/auth-usuarios/{username}")
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



    //editar, deberia ser un metodo para que el usuario actualice SU PERFIL

    //editar
    @PutMapping("/auth-usuarios/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(
            @PathVariable int id,
            @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioDTO);
        return ResponseEntity.ok(usuarioActualizado);
    }

    //Buscar por id DTO
    @GetMapping("/auth-usuarios/buscar/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable int id) {
        UsuarioDTO usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }


    //ELIMINAR METODO DE AMBOS

    @DeleteMapping("/auth-usuarios/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
