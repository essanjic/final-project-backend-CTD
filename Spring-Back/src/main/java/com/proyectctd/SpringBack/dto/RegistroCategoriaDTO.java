package com.proyectctd.SpringBack.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RegistroCategoriaDTO {
    private Long id_categoria;

    private String nombre;

    public RegistroCategoriaDTO(Long id_categoria, String nombre) {
        this.id_categoria = id_categoria;
        this.nombre = nombre;
    }

    public RegistroCategoriaDTO(){}

    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }



}
