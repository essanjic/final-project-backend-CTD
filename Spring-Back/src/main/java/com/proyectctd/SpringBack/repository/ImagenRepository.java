package com.proyectctd.SpringBack.repository;


import com.proyectctd.SpringBack.domain.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long> {




}
