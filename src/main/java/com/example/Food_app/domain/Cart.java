package com.example.Food_app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @OneToOne(mappedBy = "cart")
    @JsonIgnoreProperties({"cart"})
    private OrderDetails order;

    @OneToOne(mappedBy = "cart")
    @JsonIgnoreProperties({"cart"})
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    @JsonIgnoreProperties("cart")
    private List<Item> itemList ;

}
