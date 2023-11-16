package com.example.Food_app.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer restaurantId;
    private String restaurantName;
    private String managerName;
    private String contactNumber;

    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

    @JoinColumn
    @OneToOne
    private Address address;

    @OneToMany(targetEntity = Item.class,cascade = CascadeType.ALL)
    private List<Item> itemList;
}
