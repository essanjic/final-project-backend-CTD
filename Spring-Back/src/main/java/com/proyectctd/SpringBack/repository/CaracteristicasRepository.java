package com.proyectctd.SpringBack.repository;

import com.proyectctd.SpringBack.domain.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaracteristicasRepository extends JpaRepository<Caracteristica, Long> {



}
