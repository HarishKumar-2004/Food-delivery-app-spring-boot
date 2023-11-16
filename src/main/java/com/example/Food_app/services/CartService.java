package com.example.Food_app.services;

import com.example.Food_app.domain.Cart;
import com.example.Food_app.domain.Item;
import com.example.Food_app.exceptions.CartException;
import com.example.Food_app.exceptions.ItemException;
import com.example.Food_app.repository.CartRepository;
import com.example.Food_app.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemDAO;

    public Cart saveCart(Cart cart) throws CartException {
        Optional<Cart> opt = cartRepository.findById(cart.getCartId());
        if(opt.isPresent()) {
            throw new CartException("Cart already exists..");
        }
        else {
            return cartRepository.save(cart);
        }
    }

    public Cart clearCart(Integer cartId) throws CartException {
        Optional<Cart> opt = cartRepository.findById(cartId);
        if(opt.isPresent()) {
            Cart cart = opt.get();
            cartRepository.delete(cart);
            return cart;
        }
        else {
            throw new CartException("No Cart found with ID: "+cartId);
        }
    }

    public Cart viewCart(Integer cartId) throws CartException {
        Optional<Cart> opt = cartRepository.findById(cartId);
        if(opt.isPresent()) {
            Cart cart = opt.get();
            return cart;
        }
        else {
            throw new CartException("No Cart found with ID: "+cartId);
        }
    }

    public Cart addItem(Integer cartId, Integer itemId) throws CartException, ItemException {
        Optional<Cart> cOpt = cartRepository.findById(cartId);
        if(cOpt.isPresent()) {

            Optional<Item> iOpt = itemDAO.findById(itemId);
            if(iOpt.isPresent()) {

                Cart cart = cOpt.get();
                Item item = iOpt.get();
                List<Item> list = new ArrayList<>();
                list.addAll(cart.getItemList());
                list.add(item);
                cart.setItemList(list);

                return cart;

            }
            else {
                throw new ItemException("No Item found with ID: "+itemId);
            }
        }
        else {
            throw new CartException("No Cart found with ID: "+cartId);
        }
    }

}
