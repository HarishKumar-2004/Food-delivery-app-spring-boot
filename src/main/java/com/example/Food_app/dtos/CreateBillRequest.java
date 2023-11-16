package com.example.Food_app.dtos;

import com.example.Food_app.domain.Bill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBillRequest {

    @NotNull
    private double totalCost;
    @NotNull
    private Integer totalItem;

    public Bill toBill()
    {
        return Bill.builder()
                   .totalCost(this.totalCost)
                   .totalItem(this.totalItem)
                   .build();
    }
}
