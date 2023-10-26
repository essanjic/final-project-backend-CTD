package com.proyectctd.SpringBack.domain;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@Getter
@Setter
@Table(name = "caracteristica")
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caracteristica")
    private Long id_caracteristica;

    private String nombre;

    private String emoji;


    // Getters y setters



    @Override
    public String toString() {
        return "Caracteristica{" +
                "id_caracteristica=" + id_caracteristica +
                ", nombre='" + nombre + '\'' +
                ", emoji='" + emoji + '\'' +
                '}';
    }
}