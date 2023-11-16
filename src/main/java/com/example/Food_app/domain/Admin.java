package com.example.Food_app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @Column(unique = true, nullable = false)
    private String email;

    @CreationTimestamp
    private Date createdOn;

    @OneToMany(mappedBy = "admin")
    @JsonIgnoreProperties({"admin"})
    private List<OrderDetails> orderDetailsList;

    @JoinColumn
    @OneToOne
    @JsonIgnoreProperties({"admin"})
    private SecuredUser securedUser;
}
