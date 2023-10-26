package com.proyectctd.SpringBack.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegistroUsuarioDTO {
    private Long id_usuario;
    private String nombre;
    private String apellido;
    private String userName;
    private  String email;
    private  String password;
    private String rol;

}