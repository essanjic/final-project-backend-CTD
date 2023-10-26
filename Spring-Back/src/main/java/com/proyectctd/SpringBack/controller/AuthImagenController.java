package com.proyectctd.SpringBack.controller;


import com.proyectctd.SpringBack.domain.Imagen;
import com.proyectctd.SpringBack.service.ImagenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/auth")
public class AuthImagenController {

    private ImagenService imagenService;

    @GetMapping("/auth-imagen/list")
    public List<Imagen> getAllImages() {
        return imagenService.getAllImages();
    }



    @GetMapping("/auth-imagen/{id}")
    public ResponseEntity<Imagen> getImageById(@PathVariable Long id) {
        Imagen imagen = imagenService.getImageById(id);
        return ResponseEntity.ok(imagen);
    }



}
