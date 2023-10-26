package com.proyectctd.SpringBack.controller;


import com.proyectctd.SpringBack.domain.Caracteristica;
import com.proyectctd.SpringBack.service.CaracteristicasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/auth")
public class AuthCaracteristicasController {

    private CaracteristicasService service;

    @GetMapping("/auth-caracteristicas/list")
    public ResponseEntity<List<Caracteristica>> list() throws Exception{
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/auth-caracteristicas/buscar/{id}")
    public ResponseEntity<Caracteristica> getCaracteristicaById(@PathVariable Long id) {
        Caracteristica caracteristica = service.getCaracteristicaById(id);
        if (caracteristica != null) {
            return ResponseEntity.ok(caracteristica);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
