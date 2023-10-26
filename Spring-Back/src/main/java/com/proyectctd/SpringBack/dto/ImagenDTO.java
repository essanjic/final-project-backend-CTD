package com.proyectctd.SpringBack.dto;

import lombok.Data;

@Data
public class ImagenDTO {
    private Long id_imagen;
    private String imagenesUrls;
    private String descripcion;


    // Constructores, getters y setters
    public ImagenDTO() {}

    public ImagenDTO(Long id_imagen, String imagen_ruta, String descripcion) {
        this.id_imagen =id_imagen;
        this.imagenesUrls = imagen_ruta;
        this.descripcion = descripcion;

    }

    public String getImagenesUrls() {
        return imagenesUrls;
    }
}
