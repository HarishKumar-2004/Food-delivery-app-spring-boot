package com.example.Food_app.controller;

import com.example.Food_app.domain.Category;
import com.example.Food_app.exceptions.CategoryException;
import com.example.Food_app.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) throws CategoryException {
        Category newCategory = categoryService.addCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws CategoryException{
        Category updatedCategory = categoryService.updateCategory(category);
        return new ResponseEntity<Category>(updatedCategory, HttpStatus.ACCEPTED);
    }

    @GetMapping("/viewCategory/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Integer categoryId) throws CategoryException{
        Category category = categoryService.viewCategory(categoryId);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @DeleteMapping("/removeCategory/{categoryId}")
    public ResponseEntity<Category> removeCategory(@PathVariable("categoryId") Integer categoryId) throws CategoryException{
        Category removedCategory = categoryService.removeCategory(categoryId);
        return new ResponseEntity<Category>(removedCategory, HttpStatus.OK);
    }

    @GetMapping("/viewAllCategory")
    public ResponseEntity<List<Category>> getAllCategories() throws CategoryException{
        List<Category> categories = categoryService.viewAllCategory();
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

}
