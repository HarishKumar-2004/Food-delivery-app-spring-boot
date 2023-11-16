package com.example.Food_app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bill {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer billId;
    @CreationTimestamp
    private LocalDateTime billDate;
    @UpdateTimestamp
    private Date updatedOn;

    private Double totalCost;
    private Integer totalItem;

    @OneToOne(mappedBy = "bill")
    @JsonIgnoreProperties({"bill"})
    private OrderDetails order;
}
