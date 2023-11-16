package com.example.Food_app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer addressId;
    private String area;
    private String city;
    private String state;
    private String country;
    private String pincode;

    @OneToOne(mappedBy = "address")
    @JsonIgnoreProperties({"address"})
    private Customer customer;

    @OneToOne(mappedBy = "address")
    @JsonIgnoreProperties({"address"})
    private Restaurant restaurant;

}
