package com.example.Food_app.dtos;

import com.example.Food_app.domain.Address;
import com.example.Food_app.domain.Customer;
import com.example.Food_app.domain.SecuredUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {

    private String username;
    private String password;

    @NotNull
    private String name;
    @NotNull
    @Min(16)
    private Integer age;
    @NotBlank
    private String gender;
    @NotNull
    private String mobileNumber;
    private String email;

    @NotNull
    private String area;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String country;
    private String pincode;

    public Customer toCustomer()
    {
        return Customer.builder()
                .name(this.name)
                .age(this.age)
                .gender(this.gender)
                .mobileNumber(this.mobileNumber)
                .email(this.email)
                .securedUser(SecuredUser.builder()
                        .username(this.username)
                        .password(this.password)
                        .build())
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
