package com.proyectctd.SpringBack.controller;


import com.proyectctd.SpringBack.domain.Categoria;
import com.proyectctd.SpringBack.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/auth")
public class AuthCategoriaController {

    private CategoriaService categoriaService;


    @GetMapping("/auth-categoria/list")
    public List<Categoria> list(){
        return categoriaService.getAllCategorias();
    }

    @GetMapping("/auth-categoria/buscar/{id}")
    public ResponseEntity<Map<String, Object>> getCategoryById(@PathVariable Long id) {
        Categoria category = categoriaService.getCategoriaById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("nombre", category.getNombre());


        return ResponseEntity.ok(response);

    }

}
