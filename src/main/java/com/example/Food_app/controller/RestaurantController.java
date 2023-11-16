package com.example.Food_app.controller;

import com.example.Food_app.domain.Restaurant;
import com.example.Food_app.dtos.CreateRestaurantRequest;
import com.example.Food_app.exceptions.RestaurantException;
import com.example.Food_app.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restService;

    @PostMapping("/addRestaurant")
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody @Valid CreateRestaurantRequest restaurantRequest) throws RestaurantException {

        Restaurant newRestaurant = restService.addRestaurant(restaurantRequest.toRestaurant());

        return new ResponseEntity<Restaurant>(newRestaurant , HttpStatus.CREATED);
    }

    @PutMapping("/updateRestaurant")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody @Valid CreateRestaurantRequest restaurantRequest) throws RestaurantException{

        Restaurant updatedRestaurant=restService.updateRestaurant(restaurantRequest.toRestaurant());
        return new ResponseEntity<Restaurant>(updatedRestaurant,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/removeRestaurant/{restaurantId}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable("restaurantId") Integer restaurantId) throws RestaurantException{

        Restaurant removedRestaurant = restService.removeRestaurant(restaurantId);
        return new ResponseEntity<Restaurant>(removedRestaurant, HttpStatus.OK);
    }

    @GetMapping("/viewRestaurant/{restaurantId}")
    public ResponseEntity<Restaurant> getByRestaurantId(@PathVariable Integer restaurantId) throws RestaurantException{

        Restaurant myRestaurant = restService.viewRestaurant(restaurantId);
        return new ResponseEntity<>(myRestaurant,HttpStatus.FOUND);
    }

}
