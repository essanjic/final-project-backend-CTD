package com.proyectctd.SpringBack.dto;



import lombok.Data;


@Data
public class CaracteristicasDTO {

    private Long id_caracteristica;
    private String nombre;
    private String emoji;


    public CaracteristicasDTO() {

    }

    public CaracteristicasDTO(Long id_caracteristica, String nombre, String emoji) {
        this.id_caracteristica = id_caracteristica;
        this.nombre = nombre;
        this.emoji = emoji;
    }

    public Long getId_caracteristica() {
        return id_caracteristica;
    }

    public void setId_caracteristica(Long id_caracteristica) {
        this.id_caracteristica = id_caracteristica;
    }
}
