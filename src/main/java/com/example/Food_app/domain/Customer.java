package com.example.Food_app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String name;
    private Integer age;
    private String gender;
    private String mobileNumber;
    private String email;

    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

    @JoinColumn
    @OneToOne
    private Address address;

    @JoinColumn
    @OneToOne
    private Cart cart;

    @JoinColumn
    @OneToOne
    @JsonIgnoreProperties({"customer"})
    private SecuredUser securedUser;

}
