package com.proyectctd.SpringBack.service;

import com.proyectctd.SpringBack.domain.Rol;
import com.proyectctd.SpringBack.domain.Usuario;
import com.proyectctd.SpringBack.dto.UsuarioDTO;
import com.proyectctd.SpringBack.exceptions.BadRequestException;
import com.proyectctd.SpringBack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> findByID (Integer id){
        return usuarioRepository.findById(id);
    }



    // Listar todos los usuarios
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::convertirAUsuarioDTO)
                .collect(Collectors.toList());
    }

    // Actualizar
    public UsuarioDTO actualizarUsuario(int id, UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Usuario no encontrado"));

        usuarioExistente.setUsername(usuarioDTO.getUsername());
        usuarioExistente.setNombre(usuarioDTO.getNombre());
        usuarioExistente.setApellido(usuarioDTO.getApellido());
        usuarioExistente.setPassword(usuarioDTO.getPassword());
        usuarioExistente.setEstado(usuarioDTO.getEstado());

        Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);

        return convertirAUsuarioDTO(usuarioActualizado);
    }

    // Buscar por ID
    public UsuarioDTO buscarUsuarioPorId(int id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Usuario no encontrado"));
        return convertirAUsuarioDTO(usuario);
    }

    //BUSCAR POR ROL

    public List<Usuario> buscarTodosUsuariosPorRol(String rol) throws Exception {
        List<Usuario> lista = usuarioRepository.findByRol(rol);
        if(!lista.isEmpty()){
            return lista;
        }else{
            throw new BadRequestException("Error. No existen Usuarios registrados.");
        }
    }

    // Eliminar
    public void eliminarUsuario(int id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
    }


    //buscar por Email
    public Usuario buscarUsuarioPorEmail(String username) throws Exception {

        Optional<Usuario> usuarioBuscado = usuarioRepository.findByUsername(username);
        if(usuarioBuscado.isPresent()){
            return usuarioBuscado.get();
        }
        else{
            throw new BadRequestException("Error. No existe el Usuario con email = " + username + ".");
        }
    }

    // Convertir un Usuario a UsuarioDTO
    private UsuarioDTO convertirAUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setPassword(usuario.getPassword());
        usuarioDTO.setEstado(usuario.getEstado());
        return usuarioDTO;
    }



    //NO SE SI ANDA LA CULPA A GEPETO
    public Usuario cambiarRolUsuario(int idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

         usuario.setRol(Rol.ADMIN); // Cambia el rol a ADMIN

        return usuarioRepository.save(usuario);
    }




    public boolean cambiarRol(int userId, Rol nuevoRol) {
        int updatedRows = usuarioRepository.updateRol(userId, nuevoRol);
        return updatedRows > 0;
    }


}