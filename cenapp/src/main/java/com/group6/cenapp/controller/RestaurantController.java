package com.group6.cenapp.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group6.cenapp.model.Category;
import com.group6.cenapp.model.City;
import com.group6.cenapp.model.Restaurant;
import com.group6.cenapp.response.ApiResponseHandler;
import com.group6.cenapp.services.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Api(tags="Restaurant")
@CrossOrigin (origins = "*")
@RestController
@RequestMapping("/v1/api/restaurants")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> listRestaurant(){
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @ApiOperation(value="Product by ID", notes="Restaurant by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Object> findRestaurant(@PathVariable Integer id)  {
        Optional<Restaurant> productoBuscado = restaurantService.getRestaurantById(id);
        if(productoBuscado.isPresent())
            return ApiResponseHandler.generateResponse("Restaurant data retrieved successfully", HttpStatus.OK, productoBuscado.get());

        return ApiResponseHandler.generateResponseError("Restaurant "+ id + " not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Restaurant>> searchRestaurantByCategory(@PathVariable Category id) {
        List<Restaurant> restaurantSearches = restaurantService.getRestaurantByCategory(id);
         if(!restaurantSearches.isEmpty()){
            return ResponseEntity.ok(restaurantSearches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant){
        return ResponseEntity.ok(restaurantService.saveRestaurant(restaurant));
    }

    @PutMapping("/update")
    public ResponseEntity<?> editRestaurant(@RequestBody Restaurant restaurant) throws Exception{
        Optional<Restaurant> productoBuscado = restaurantService.getRestaurantById(restaurant.getId());
        if(productoBuscado.isPresent()){
            return ResponseEntity.ok(restaurantService.updateRestaurant(restaurant));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El restaurante con ID: " + restaurant.getId() + " no se encuentra ");
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Integer id){
        if(restaurantService.getRestaurantById(id).isPresent()){
            restaurantService.deleteRestaurantById(id);
            return ResponseEntity.ok("Se eliminó con éxito el restaurante con ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el restaurante con ID: " + id);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<List<Restaurant>> searchRestaurantByCategory(@PathVariable City id) {
        List<Restaurant> restaurantSearches = restaurantService.getRestaurantByCity(id);
        if(!restaurantSearches.isEmpty()){
            return ResponseEntity.ok(restaurantSearches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/dates/{startDate}/{endDate}")
    public ResponseEntity<List<Restaurant>> searchrestaurantByRangeDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<Restaurant> restaurantSearches = restaurantService.getRestaurantByRangeDate(startDate, endDate);
        if(!restaurantSearches.isEmpty()){
            return ResponseEntity.ok(restaurantSearches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/cityAndDates/{cityId}/{startDate}/{endDate}")
    public ResponseEntity<List<Restaurant>> searchrestaurantByRangeDate(@PathVariable Integer cityId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<Restaurant> restaurantSearches = restaurantService.getRestaurantByCityAndRangeDate(cityId, startDate, endDate);
        if(!restaurantSearches.isEmpty()){
            return ResponseEntity.ok(restaurantSearches);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("findAll/random")
    public ResponseEntity<List<Restaurant>> findAllRandom(){
        return ResponseEntity.ok(restaurantService.getRandomRestaurant());
    }
}



