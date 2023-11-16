package com.example.Food_app.services;

import com.example.Food_app.domain.Item;
import com.example.Food_app.exceptions.ItemException;
import com.example.Food_app.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item addItem(Item item) throws ItemException {
        Optional<Item> opt = itemRepository.findById(item.getItemId());
        if(opt.isPresent()) {
            throw new ItemException("Item already exists..");
        }
        else {
            return itemRepository.save(item);
        }
    }

    public Item updateItem(Item item) throws ItemException {
        Optional<Item> opt = itemRepository.findById(item.getItemId());
        if(opt.isPresent()) {
            return itemRepository.save(item);
        }
        else {
            throw new ItemException("No such Item found..");
        }
    }

    public Item viewItem(Integer itemId) throws ItemException {
        Optional<Item> opt = itemRepository.findById(itemId);
        if(opt.isPresent()) {
            return opt.get();
        }
        else {
            throw new ItemException("No Item found with ID: "+itemId);
        }
    }

    public Item removeItem(Integer itemId) throws ItemException {
        Optional<Item> opt = itemRepository.findById(itemId);
        if(opt.isPresent()) {
            Item item = opt.get();
            itemRepository.delete(item);
            return item;
        }
        else {
            throw new ItemException("No Item found with ID: "+itemId);
        }
    }

    public List<Item> viewAllItems() throws ItemException {
        List<Item> items = itemRepository.findAll();
        if(items.isEmpty() == false) {
            return items;
        }
        else {
            throw new ItemException("No Item exists..");
        }
    }
}
