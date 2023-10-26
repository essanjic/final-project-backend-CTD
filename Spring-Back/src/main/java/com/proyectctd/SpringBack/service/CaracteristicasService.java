package com.proyectctd.SpringBack.service;

import com.proyectctd.SpringBack.domain.Caracteristica;

import com.proyectctd.SpringBack.repository.CaracteristicasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Service
public class CaracteristicasService {

    @PersistenceContext
    private EntityManager entityManager;
    private final  CaracteristicasRepository caracteristicasRepository;



    @Autowired
    public CaracteristicasService(CaracteristicasRepository caracteristicasRepository) {
        this.caracteristicasRepository = caracteristicasRepository;


    }



    public Caracteristica crearCaracteristica(Caracteristica caracteristica){
        return  caracteristicasRepository.save(caracteristica);
    }


    public Caracteristica getCaracteristicaById(Long id) {
        Optional<Caracteristica> caracteristicaOptional = caracteristicasRepository.findById(id);
        return caracteristicaOptional.orElse(null);
    }

    public Caracteristica updateCaracteristica(Long id, Caracteristica updatedCaracteristica) {
        Caracteristica existingCaracteristica = caracteristicasRepository.findById(id).orElse(null);
        if (existingCaracteristica != null) {
            existingCaracteristica.setNombre(updatedCaracteristica.getNombre());
            existingCaracteristica.setEmoji(updatedCaracteristica.getEmoji());
            return caracteristicasRepository.save(existingCaracteristica);
        }
        return null;
    }
    public List<Caracteristica> findAll() {return caracteristicasRepository.findAll();}

    public void deleteCaracteristicas(Long id){caracteristicasRepository.deleteById(id);}


    public List<Caracteristica> getCaracteristicasByProductoId(Long id_producto) {
        // Crear una consulta personalizada para obtener las características por el ID del producto
        String jpql = "SELECT c FROM caracteristica c JOIN c.producto p WHERE p.id_producto = :idProducto";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id_producto", id_producto);

        // Ejecutar la consulta y devolver la lista de características
        return query.getResultList();
    }
}
