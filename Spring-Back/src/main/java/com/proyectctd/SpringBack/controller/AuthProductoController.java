package com.proyectctd.SpringBack.controller;


import com.proyectctd.SpringBack.domain.Caracteristica;
import com.proyectctd.SpringBack.domain.Producto;
import com.proyectctd.SpringBack.dto.ProductoRequestDTO;
import com.proyectctd.SpringBack.dto.RegistroCategoriaDTO;
import com.proyectctd.SpringBack.exceptions.ResourceNotFoundException;
import com.proyectctd.SpringBack.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/auth")

public class AuthProductoController {


    private ProductoService productoService;




        //buscar por id

    @Transactional
    @GetMapping("/auth-Producto/{id}") //trae todo
    public ResponseEntity<ProductoRequestDTO> getProductById(@PathVariable Long id) {
        ProductoRequestDTO product = productoService.getProductoXId(id);
        ProductoRequestDTO productDTO = new ProductoRequestDTO(product.getId(), product.getNombre(), product.getDescripcion(),product.getPrecioVenta(), product.getImagen(),product.getRegistroCategoriaDTO(),product.getCaracteristicaIds());
        return ResponseEntity.ok(productDTO);
    }

    //listar
    @GetMapping("/auth-Producto/listar")
    public ResponseEntity<List<Producto>> listResponseEntity() throws Exception {
        return ResponseEntity.ok(productoService.getAllProducts());
    }





}
