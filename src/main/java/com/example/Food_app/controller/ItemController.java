package com.example.Food_app.controller;

import com.example.Food_app.domain.Item;
import com.example.Food_app.exceptions.ItemException;
import com.example.Food_app.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping("/addItem")
    public ResponseEntity<Item> addItem(@RequestBody Item item) throws ItemException {
        Item newItem = itemService.addItem(item);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @PutMapping("/updateItem")
    public ResponseEntity<Item> updateItem(@RequestBody Item item) throws ItemException{
        Item updatedItem = itemService.updateItem(item);
        return new ResponseEntity<Item>(updatedItem, HttpStatus.OK);
    }

    @GetMapping("/viewItem/{itemId}")
    public ResponseEntity<Item> getItem(@PathVariable("itemId") Integer itemId) throws ItemException{
        Item item = itemService.viewItem(itemId);
        return new ResponseEntity<Item>(item, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/removeItem/{itemId}")
    public ResponseEntity<Item> removeItem(@PathVariable("itemId") Integer itemId) throws ItemException{
        Item removedItem = itemService.removeItem(itemId);
        return new ResponseEntity<Item>(removedItem, HttpStatus.ACCEPTED);
    }

    @GetMapping("/viewAllItems")
    public ResponseEntity<List<Item>> getAllItems() throws ItemException{
        List<Item> items = itemService.viewAllItems();
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);

    }
}
