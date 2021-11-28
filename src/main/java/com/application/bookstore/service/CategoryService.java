package com.application.bookstore.service;

import java.util.List;

import com.application.bookstore.exception.ResourceNotFoundException;
import com.application.bookstore.model.Category;

public interface CategoryService {

    Category addCategory(Category category);

    Category editCategory(Category category, long id) throws ResourceNotFoundException;

    void deleteCategory(long id) throws ResourceNotFoundException;

    Category findCategoryById(long id);

    List<Category> findAllCategories();

}

