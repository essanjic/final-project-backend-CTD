package com.group6.cenapp.controller;

import com.group6.cenapp.model.FoodAttribute;
import com.group6.cenapp.services.ProductAttributeService;
import com.group6.cenapp.exception.DuplicatedValueException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags="Attributes")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/attributes")
public class FoodAttributeControllet {

    @Autowired
    private ProductAttributeService productAttributeService;

    @GetMapping
    public ResponseEntity<List<FoodAttribute>> getAllAttributes() {
        return ResponseEntity.ok(productAttributeService.getAllProductAttribute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodAttribute> getAttributeById(@PathVariable Integer id) {
        Optional<FoodAttribute> findAttribute = productAttributeService.getProductAttributeById(id);
        if (findAttribute.isPresent()) {
            return ResponseEntity.ok(findAttribute.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<FoodAttribute> createAttribute(@RequestBody FoodAttribute foodAttribute) throws DuplicatedValueException {
        return ResponseEntity.ok(productAttributeService.saveProductAttribute(foodAttribute));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAttribute(@RequestBody FoodAttribute foodAttribute) throws Exception {
        Optional<FoodAttribute> findAttribute = productAttributeService.getProductAttributeById(foodAttribute.getId());
        if (findAttribute.isPresent()) {
            return ResponseEntity.ok(productAttributeService.updateProductAttribute(foodAttribute));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El atributo con ID: " + foodAttribute.getId() + " no se encuentra");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAttribute(@PathVariable Integer id) {
        if (productAttributeService.getProductAttributeById(id).isPresent()) {
            productAttributeService.deleteProductAttributeById(id);
            return ResponseEntity.ok("Se eliminó con éxito el atributo con ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el atributo con ID: " + id);
    }

}
