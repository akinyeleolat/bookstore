package com.application.bookstore.controller;

import java.util.List;

import com.application.bookstore.exception.DuplicateResourceException;
import com.application.bookstore.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.bookstore.model.Category;
import com.application.bookstore.service.CategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "*")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    Category createCategory(@Valid @RequestBody Category category) throws DuplicateResourceException {
        return categoryService.addCategory(category);
    }

    @PutMapping("/editCategory/{id}")
    Category editCategory(@RequestBody Category category, @PathVariable long id) throws ResourceNotFoundException {
        return categoryService.editCategory(category, id);
    }

    @DeleteMapping("/deleteCategory/{id}")
    void deleteCategory(@PathVariable long id) throws ResourceNotFoundException {
        categoryService.deleteCategory(id);
    }

    @GetMapping("/findCategoryById/{id}")
    Category findCategoryById(@PathVariable long id) {
        return categoryService.findCategoryById(id);
    }

    @GetMapping("/Categories")
    List<Category> findAllCategories() {
        return categoryService.findAllCategories();
    }

}
