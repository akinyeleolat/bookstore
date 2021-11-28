package com.application.bookstore.service;

import com.application.bookstore.exception.DuplicateResourceException;
import com.application.bookstore.exception.ResourceNotFoundException;
import com.application.bookstore.model.Category;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity<?> addCategory(Category category) throws DuplicateResourceException;

    ResponseEntity<?> editCategory(Category category, long id) throws ResourceNotFoundException;

    ResponseEntity<?> deleteCategory(long id) throws ResourceNotFoundException;

    ResponseEntity<?> findCategoryById(long id);

    ResponseEntity<?> findAllCategories();

}

