package com.example.Food_app.services;

import com.example.Food_app.domain.Customer;
import com.example.Food_app.domain.Item;
import com.example.Food_app.domain.OrderDetails;
import com.example.Food_app.exceptions.CustomerException;
import com.example.Food_app.exceptions.OrderException;
import com.example.Food_app.repository.CustomerRepository;
import com.example.Food_app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public OrderDetails addOrder(OrderDetails order) throws OrderException {
        Optional<OrderDetails> opt = orderRepository.findById(order.getOrderId());
        if(opt.isPresent()) {
            throw new OrderException("Order already exists..");
        }
        else {
            return orderRepository.save(order);
        }
    }

    public OrderDetails updateOrder(OrderDetails order) throws OrderException {
        Optional<OrderDetails> opt = orderRepository.findById(order.getOrderId());
        if(opt.isPresent()) {
            return orderRepository.save(order);
        }
        else {
            throw new OrderException("Order such Order exists..");
        }
    }

    public OrderDetails removeOrder(Integer orderId) throws OrderException {
        Optional<OrderDetails> opt = orderRepository.findById(orderId);
        if(opt.isPresent()) {
            OrderDetails order = opt.get();
            orderRepository.delete(order);
            return order;
        }
        else {
            throw new OrderException("No Order found with ID: "+orderId);
        }
    }

    public OrderDetails viewOrder(Integer orderId) throws OrderException {
        Optional<OrderDetails> opt = orderRepository.findById(orderId);
        if(opt.isPresent()) {
            OrderDetails order = opt.get();
            return order;
        }
        else {
            throw new OrderException("No Order found with ID: "+orderId);
        }
    }

    public List<Item> viewAllOrdersByCustomer(Integer customerId) throws OrderException, CustomerException {
        Optional<Customer> cOpt =customerRepository.findById(customerId);
        if(cOpt.isPresent()) {
            Customer customer = cOpt.get();
            List<Item> itemList = customer.getCart().getItemList();
            if(itemList.isEmpty()==false) {
                return itemList;
            }
            else {
                throw new OrderException("No Orders found..");
            }
        }
        else {
            throw new CustomerException("No Customer found with ID: "+customerId);
        }
    }
}
