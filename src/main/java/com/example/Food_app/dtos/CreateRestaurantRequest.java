package com.example.Food_app.dtos;

import com.example.Food_app.domain.Address;
import com.example.Food_app.domain.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRestaurantRequest {

    @NotNull
    private String restaurantName;
    @NotBlank
    private String managerName;
    @NotBlank
    private String contactNumber;

    @NotBlank
    private String area;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    private String country;
    private String pincode;

    public Restaurant toRestaurant()
    {
        return Restaurant.builder()
                .restaurantName(this.restaurantName)
                .managerName(this.managerName)
                .contactNumber(this.contactNumber)
                .address(
                        Address.builder()
                                .area(this.area)
                                .city(this.city)
                                .state(this.state)
                                .country(this.country)
                                .pincode(this.pincode)
                                .build()
                )
                .build();
    }
}
