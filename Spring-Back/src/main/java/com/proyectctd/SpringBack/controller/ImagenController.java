package com.proyectctd.SpringBack.controller;

import com.amazonaws.services.alexaforbusiness.model.NotFoundException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyectctd.SpringBack.domain.Imagen;
import com.proyectctd.SpringBack.exceptions.BadRequestException;
import com.proyectctd.SpringBack.service.ImagenService;
import com.proyectctd.SpringBack.service.api.AWS3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/imagenes")
@CrossOrigin("http://localhost:5173/")
public class ImagenController {


    private ImagenService imagenService;



    public ImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;

    }

    @GetMapping("/list")
    public List<Imagen> getAllImages() {
        return imagenService.getAllImages();
    }



    @GetMapping("/{id}")
    public ResponseEntity<Imagen> getImageById(@PathVariable Long id) {
        Imagen imagen = imagenService.getImageById(id);
        return ResponseEntity.ok(imagen);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id) {
        imagenService.deleteImage(id);
        return ResponseEntity.ok().build();
    }



}
