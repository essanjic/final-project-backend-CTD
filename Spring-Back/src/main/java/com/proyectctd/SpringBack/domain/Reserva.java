/*package com.proyectctd.SpringBack.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@Table(name = "reserva")
public class Reserva {
    @Id
    @Column(name = "id_reserva")
    private Long idReserva;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_producto",nullable = false)
    private Producto producto;

    @Column
    private LocalDate fechaReserva;

    @Column
    private LocalDate FechaInicio;

    @Column
    private LocalDate FechaFinalizacion;

    @Column
    @Enumerated(EnumType.STRING)
    private EnumEstadoReserva estadoReserva;

    public Reserva(){};


}




 */