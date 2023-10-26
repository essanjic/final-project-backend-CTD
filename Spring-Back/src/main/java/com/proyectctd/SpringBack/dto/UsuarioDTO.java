package com.proyectctd.SpringBack.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsuarioDTO {

    private Long id;
    private String username;
    private String nombre;
    private String apellido;
    private String password;
    private String estado;

    public UsuarioDTO(){}

    public UsuarioDTO(Long id, String username, String nombre, String apellido, String password, String estado) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.estado = estado;
    }
}
