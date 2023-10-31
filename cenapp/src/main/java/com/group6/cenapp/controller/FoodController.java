package com.group6.cenapp.controller;

import com.group6.cenapp.model.Category;
import com.group6.cenapp.model.City;
import com.group6.cenapp.model.Food;
import com.group6.cenapp.response.ApiResponseHandler;
import com.group6.cenapp.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Api(tags="Products")
@CrossOrigin (origins = "*")
@RestController
@RequestMapping("/products")
public class FoodController {

    @Autowired
    private ProductService prodctService;

    @GetMapping
    public ResponseEntity<List<Food>> listarProductos(){
        return ResponseEntity.ok(prodctService.getAllProducts());
    }

    @ApiOperation(value="Product by ID", notes="Product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarProducto(@PathVariable Integer id)  {
        Optional<Food> productoBuscado = prodctService.getProductById(id);
        if(productoBuscado.isPresent())
            return ApiResponseHandler.generateResponse("Product data retrieved successfully", HttpStatus.OK, productoBuscado.get());

        return ApiResponseHandler.generateResponseError("Product "+ id + " not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Food>> searchProductByCategory(@PathVariable Category id) {
        List<Food> productsSearches = prodctService.getProductsByCategory(id);
         if(!productsSearches.isEmpty()){
            return ResponseEntity.ok(productsSearches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Food> crearProducto(@RequestBody Food food){
        return ResponseEntity.ok(prodctService.saveProduct(food));
    }

    @PutMapping("/update")
    public ResponseEntity<?> editarProducto(@RequestBody Food food) throws Exception{
        Optional<Food> productoBuscado = prodctService.getProductById(food.getId());
        if(productoBuscado.isPresent()){
            return ResponseEntity.ok(prodctService.updateProduct(food));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto con ID: " + food.getId() + " no se encuentra ");
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Integer id){
        if(prodctService.getProductById(id).isPresent()){
            prodctService.deleteProductById(id);
            return ResponseEntity.ok("Se eliminó con éxito el producto con ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el producto con ID: " + id);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<List<Food>> searchProductByCategory(@PathVariable City id) {
        List<Food> productsSearches = prodctService.getProductsByCity(id);
        if(!productsSearches.isEmpty()){
            return ResponseEntity.ok(productsSearches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/dates/{startDate}/{endDate}")
    public ResponseEntity<List<Food>> searchProductsByRangeDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<Food> productsSearches = prodctService.getProductsByRangeDate(startDate, endDate);
        if(!productsSearches.isEmpty()){
            return ResponseEntity.ok(productsSearches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/cityAndDates/{cityId}/{startDate}/{endDate}")
    public ResponseEntity<List<Food>> searchProductsByRangeDate(@PathVariable Integer cityId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<Food> productsSearches = prodctService.getProductsByCityAndRangeDate(cityId, startDate, endDate);
        if(!productsSearches.isEmpty()){
            return ResponseEntity.ok(productsSearches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("findAll/random")
    public ResponseEntity<List<Food>> findAllRandom(){
        return ResponseEntity.ok(prodctService.getRandomProduct());
    }
}



