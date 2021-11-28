package com.application.bookstore.serviceimpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.application.bookstore.exception.DuplicateResourceException;
import com.application.bookstore.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.application.bookstore.repository.CategoryDao;
import com.application.bookstore.model.Category;
import com.application.bookstore.service.CategoryService;

@Transactional
@Component
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }


    @Override
    public ResponseEntity<?> addCategory(Category category) throws DuplicateResourceException {

        Optional<Category> nCategory = categoryDao.findByName(category.getName());
        if(nCategory.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryDao.save(category));
        }
        throw new DuplicateResourceException("book named "+category.getName()+" exist");

    }

    @Override
    public ResponseEntity<?> editCategory(Category category, long id) throws ResourceNotFoundException {
        Category existCategory = categoryDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found on :: " + id));
        existCategory.setName(category.getName());
        return ResponseEntity.status(HttpStatus.OK).body(categoryDao.save(existCategory));
    }

    @Override
    public ResponseEntity<?> deleteCategory(long id) throws ResourceNotFoundException {
        Category existCategory = categoryDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found on :: " + id));
        categoryDao.deleteById(existCategory.getId());
        Map<String, String> response = new HashMap<>();
        response.put("deleted", String.valueOf(Boolean.TRUE));
        response.put("category name", existCategory.getName());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<?> findCategoryById(long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryDao.findById(id).orElse(null));
    }

    @Override
    public ResponseEntity<?> findAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryDao.findAll());
    }

}
