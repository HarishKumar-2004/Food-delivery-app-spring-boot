package com.example.Food_app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer orderId;
    @CreationTimestamp
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @JoinColumn
    @OneToOne
    private Bill bill;

    @JoinColumn
    @OneToOne
    private Cart cart;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"transactionList"})
    private Admin admin;

}
