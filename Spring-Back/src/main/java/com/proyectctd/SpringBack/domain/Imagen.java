package com.proyectctd.SpringBack.domain;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@Table(name = "imagen")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long id_imagen;

    private String imagenesUrls;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @JsonBackReference
    private Producto producto;

    // Getters y setters
    public Imagen(){};



    public Imagen(String imagenesUrls) {
        this.imagenesUrls = imagenesUrls;

    }


    public void setUrl(String imagenesUrls) { this.imagenesUrls = imagenesUrls;
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "id_imagen=" + id_imagen +
                ", imagenesUrls='" + imagenesUrls + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
