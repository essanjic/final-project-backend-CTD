package com.group6.cenapp.controller;

import com.group6.cenapp.model.Image;
import com.group6.cenapp.services.ImageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Api(tags="Images")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        return ResponseEntity.ok(imageService.getAllImages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Integer id) {
        Optional<Image> findImage = imageService.getImageById(id);
        if (findImage.isPresent()) {
            return ResponseEntity.ok(findImage.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create/{restaurantId}")
    public ResponseEntity<String> createImage(@RequestBody Image image, @PathVariable Integer restaurantId) {
        byte[] imageData = Base64.getDecoder().decode(image.getUrl());
        image.setUrl(Base64.getEncoder().encodeToString(imageData));
        Image savedImage = imageService.saveImage(image, restaurantId);

        if (savedImage != null) {
            return ResponseEntity.ok("Se guardó con éxito la imagen.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo guardar la imagen.");
        }
    }


    @PutMapping("/update/{restaurantId}")
    public ResponseEntity<?> updateImage(@RequestBody Image image, @PathVariable Integer restaurantId) throws Exception {
        Optional<Image> findImage = imageService.getImageById(image.getId());
        if (findImage.isPresent()) {
            return ResponseEntity.ok(imageService.updateImage(image,restaurantId));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La imagen con ID: " + image.getId() + " no se encuentra");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Integer id) {
        if (imageService.getImageById(id).isPresent()) {
            imageService.deleteImageById(id);
            return ResponseEntity.ok("Se eliminó con éxito la imagen con ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la imagen con ID: " + id);
    }


}
