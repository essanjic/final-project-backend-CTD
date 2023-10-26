package com.proyectctd.SpringBack.controller;


import com.proyectctd.SpringBack.domain.Categoria;
import com.proyectctd.SpringBack.domain.Producto;
import com.proyectctd.SpringBack.dto.RegistroCategoriaDTO;
import com.proyectctd.SpringBack.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("api/categoria")
@CrossOrigin("http://localhost:5173/")
public class CategoriaController {

    private final CategoriaService categoriaService;


    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }



    @PostMapping("/save")
    public ResponseEntity<Categoria> createCategory(@RequestBody RegistroCategoriaDTO categoriaRequestDTO) {
        try {
            Categoria category = new Categoria();
            category.setNombre(categoriaRequestDTO.getNombre());


            Categoria createdCategory = categoriaService.crearcategoria(category);
            return ResponseEntity.ok(createdCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @GetMapping("/list")
    public List<Categoria> list(){
        return categoriaService.getAllCategorias();
    }

    @GetMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoriaService.deleteCategoria(id); ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCategoryById(@PathVariable Long id) {
        Categoria category = categoriaService.getCategoriaById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("nombre", category.getNombre());


        return ResponseEntity.ok(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody RegistroCategoriaDTO categoriaDTO) throws Exception{
        Categoria categoriaActualizada = categoriaService.editarCategoria(id, categoriaDTO);
        return ResponseEntity.ok(categoriaActualizada);
    }






}
