package com.example.Food_app.controller;

import com.example.Food_app.domain.Item;
import com.example.Food_app.domain.OrderDetails;
import com.example.Food_app.exceptions.CustomerException;
import com.example.Food_app.exceptions.OrderException;
import com.example.Food_app.services.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderService;

    @PostMapping("/saveOrder")
    public ResponseEntity<OrderDetails> saveOrder(@RequestBody OrderDetails order) throws OrderException {

        OrderDetails myOrder = orderService.addOrder(order);
        return new ResponseEntity<>(myOrder, HttpStatus.CREATED);
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<OrderDetails> updateOrder(@RequestBody OrderDetails order) throws OrderException{

        OrderDetails myOrder = orderService.updateOrder(order);
        return new ResponseEntity<>(myOrder,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/removeOrder/{orderId}")
    public ResponseEntity<OrderDetails> deleteOrder(@PathVariable("orderId") Integer orderId) throws OrderException{

        OrderDetails myOrder = orderService.removeOrder(orderId);
        return new ResponseEntity<>(myOrder,HttpStatus.OK);
    }

    @GetMapping("/viewOrder/{orderId}")
    public ResponseEntity<OrderDetails> viewOrder(@PathVariable("orderId") Integer orderId) throws OrderException{

        OrderDetails myOrder = orderService.viewOrder(orderId);
        return new ResponseEntity<>(myOrder,HttpStatus.FOUND);
    }

    @GetMapping("/viewCustomerOrder/{customerId}")
    public List<Item> viewAllOrders(@PathVariable("customerId") Integer customerId) throws OrderException, CustomerException {

        List<Item> custOrder = orderService.viewAllOrdersByCustomer(customerId);
        return custOrder;
    }

}
