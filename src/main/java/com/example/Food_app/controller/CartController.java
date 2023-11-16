package com.example.Food_app.controller;

import com.example.Food_app.domain.Cart;
import com.example.Food_app.exceptions.CartException;
import com.example.Food_app.exceptions.ItemException;
import com.example.Food_app.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/saveCart")
    public ResponseEntity<Cart> saveCartDetails(@RequestBody Cart fc) throws CartException
    {
        Cart myCart = cartService.saveCart(fc);
        return new ResponseEntity<>(myCart,HttpStatus.CREATED);
    }


    @PutMapping("/addCart/{cartId}/{itemId}")
    public ResponseEntity<Cart> addItemToCart(@PathVariable("cartId") Integer cartId, @PathVariable("itemId") Integer itemId) throws CartException, ItemException {
        Cart updatedCart = cartService.addItem(cartId, itemId);
        return new ResponseEntity<Cart>(updatedCart, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/removeCart/{cartId}")
    public ResponseEntity<Cart> removeCart(@PathVariable("cartId") Integer cartId) throws CartException
    {
        Cart removedCart = cartService.clearCart(cartId);
        return new ResponseEntity<Cart>(removedCart, HttpStatus.OK);
    }


    @GetMapping("/viewCart/{cartId}")
    public ResponseEntity<?> getCartByCartId(@PathVariable ("cartId") Integer cartId) throws CartException
    {
        Cart myCart = cartService.viewCart(cartId);
        return new ResponseEntity<>(myCart,HttpStatus.ACCEPTED);
    }
}
