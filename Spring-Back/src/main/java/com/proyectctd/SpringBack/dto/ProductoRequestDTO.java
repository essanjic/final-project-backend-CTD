package com.proyectctd.SpringBack.dto;
import com.proyectctd.SpringBack.domain.Caracteristica;
import com.proyectctd.SpringBack.domain.Imagen;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ProductoRequestDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private double precioVenta;

    private List<Imagen> imagen;

    private RegistroCategoriaDTO registroCategoriaDTO;
    private List<Caracteristica> caracteristicaIds;

    public ProductoRequestDTO(){}


    public ProductoRequestDTO(Long id, String nombre, String descripcion, double precioVenta, List<Imagen> imagen, RegistroCategoriaDTO registroCategoriaDTO, List<Caracteristica> caracteristicaIds) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.imagen = imagen;
        this.registroCategoriaDTO = registroCategoriaDTO;
        this.caracteristicaIds = caracteristicaIds;
    }
}
