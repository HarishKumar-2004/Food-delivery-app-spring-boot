package com.example.Food_app.services;

import com.example.Food_app.domain.Restaurant;
import com.example.Food_app.exceptions.RestaurantException;
import com.example.Food_app.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException {
        Optional<Restaurant> opt = restaurantRepository.findById(restaurant.getRestaurantId());
        if(opt.isPresent()) {
            throw new RestaurantException("Restaurant already exists..");
        }
        else {
            return restaurantRepository.save(restaurant);
        }
    }

    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantException {
        Optional<Restaurant> opt = restaurantRepository.findById(restaurant.getRestaurantId());
        if(opt.isPresent()) {
            return restaurantRepository.save(restaurant);
        }
        else {
            throw new RestaurantException("No such Restaurant exists..");
        }
    }

    public Restaurant removeRestaurant(Integer restaurantId) throws RestaurantException {
        Optional<Restaurant> opt = restaurantRepository.findById(restaurantId);
        if(opt.isPresent()) {
            Restaurant restaurant = opt.get();
            restaurantRepository.delete(restaurant);
            return restaurant;
        }
        else {
            throw new RestaurantException("No Restaurant found with ID: "+restaurantId);
        }
    }

    public Restaurant viewRestaurant(Integer restaurantId) throws RestaurantException {
        Optional<Restaurant> opt = restaurantRepository.findById(restaurantId);
        if(opt.isPresent()) {
            Restaurant restaurant = opt.get();
            return restaurant;
        }else {
            throw new RestaurantException("No Restaurant found with ID: "+restaurantId);
        }
    }
}
