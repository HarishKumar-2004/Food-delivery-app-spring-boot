package com.example.Food_app.services;

import com.example.Food_app.domain.Customer;
import com.example.Food_app.domain.SecuredUser;
import com.example.Food_app.exceptions.CustomerException;
import com.example.Food_app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SecuredUserService securedUserService;


    public Customer addCustomer(Customer customer) throws CustomerException {

        Optional<Customer> opt = customerRepository.findById(customer.getCustomerId());
        if(opt.isPresent()) {
            throw new CustomerException("Customer already exists..");
        }
        else {
            SecuredUser securedUser = customer.getSecuredUser();
            securedUser = securedUserService.save(securedUser, "STUDENT_USER");
            customer.setSecuredUser(securedUser);

            return customerRepository.save(customer);
        }
    }

    public Customer updateCustomer(Customer customer) throws CustomerException {
        Optional<Customer> opt = customerRepository.findById(customer.getCustomerId());
        if(opt.isPresent()) {
            return customerRepository.save(customer);
        }
        else {
            throw new CustomerException("No such customer exists..");
        }
    }

    public Customer removeCustomerById(Integer customerId) throws CustomerException {
        Optional<Customer> opt = customerRepository.findById(customerId);
        if(opt.isPresent()) {
            Customer customer = opt.get();
            customerRepository.delete(customer);
            return customer;
        }
        else {
            throw new CustomerException("No Customer found with ID: "+customerId);
        }
    }

    public Customer viewCustomer(Integer customerId) throws CustomerException {
        Optional<Customer> opt = customerRepository.findById(customerId);
        if(opt.isPresent()) {
            Customer customer = opt.get();
            return customer;
        }
        else {
            throw new CustomerException("No Customer found with ID: "+customerId);
        }
    }

}
