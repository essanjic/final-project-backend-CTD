package com.group6.cenapp.repository;


import com.group6.cenapp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    //consultar reserva por ID de producto
    List<Reservation> findByProduct_Id(Integer productId);
    List<Reservation> findByUser_id(Integer userId);


}
