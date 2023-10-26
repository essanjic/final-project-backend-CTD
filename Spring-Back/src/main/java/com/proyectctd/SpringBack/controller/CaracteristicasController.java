package com.proyectctd.SpringBack.controller;

import com.proyectctd.SpringBack.domain.Caracteristica;
import com.proyectctd.SpringBack.domain.Categoria;
import com.proyectctd.SpringBack.dto.CaracteristicasDTO;
import com.proyectctd.SpringBack.dto.RegistroCategoriaDTO;
import com.proyectctd.SpringBack.service.CaracteristicasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/caracteristicas")
@CrossOrigin("http://localhost:5173/")
public class CaracteristicasController {
    private CaracteristicasService service;

    public CaracteristicasController(CaracteristicasService service) {
        this.service = service;
    }

    @PostMapping("/crear")
    public ResponseEntity<Caracteristica> createCaracteristica(@RequestBody Caracteristica caracteristica) {
        Caracteristica createdCaracteristica = service.crearCaracteristica(caracteristica);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCaracteristica);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Caracteristica>> list() throws Exception{
        return ResponseEntity.ok(service.findAll());
    }


    @PutMapping("/modificar/{id}")
    public ResponseEntity<Caracteristica> updateCaracteristica(
            @PathVariable Long id, @RequestBody Caracteristica updatedCaracteristica) {
        Caracteristica updated = service.updateCaracteristica(id, updatedCaracteristica);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Caracteristica> getCaracteristicaById(@PathVariable Long id) {
        Caracteristica caracteristica = service.getCaracteristicaById(id);
        if (caracteristica != null) {
            return ResponseEntity.ok(caracteristica);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteCaracteristica(@PathVariable Long id) {
        service.deleteCaracteristicas(id);
        return ResponseEntity.noContent().build();
    }
}
