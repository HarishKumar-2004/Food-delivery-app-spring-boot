package com.example.Food_app.controller;

import com.example.Food_app.domain.Customer;
import com.example.Food_app.domain.SecuredUser;
import com.example.Food_app.dtos.CreateCustomerRequest;
import com.example.Food_app.exceptions.CustomerException;
import com.example.Food_app.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid CreateCustomerRequest customerRequest) throws CustomerException {
        Customer newCustomer = customerService.addCustomer(customerRequest.toCustomer());
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<Customer> updateCustomer(@RequestBody @Valid CreateCustomerRequest customerRequest) throws CustomerException{
        Customer updatedCustomer = customerService.updateCustomer(customerRequest.toCustomer());
        return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/removeCustomer/{customerId}")
    public ResponseEntity<Customer> removeCustomer(@PathVariable("customerId") Integer customerId) throws CustomerException{
        Customer removedCustomer = customerService.removeCustomerById(customerId);
        return new ResponseEntity<Customer>(removedCustomer, HttpStatus.OK);
    }

    // api for admin to check details of any customer with customer id
    @GetMapping("/viewCustomer-by-id/{customerId}")
    public Customer viewCustomer(@PathVariable("customerId") Integer customerId) throws CustomerException{


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();
        boolean isCalledByAdmin =  securedUser.getAuthorities().stream().anyMatch(x-> "CUSTOMER_INFO_BY_ADMIN" == x.getAuthority());
        if(isCalledByAdmin) {
            return customerService.viewCustomer(customerId);
        }
        throw new RuntimeException("User is not authorised");

    }

    // api for customer to check only their details
    @GetMapping("/viewCustomer")
    public Customer findCustomer() throws CustomerException{

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();
        int customerId = securedUser.getCustomer().getCustomerId();
        return customerService.viewCustomer(customerId);

    }

}
