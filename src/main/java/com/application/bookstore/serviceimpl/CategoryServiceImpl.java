package com.application.bookstore.serviceimpl;

import java.util.List;

import com.application.bookstore.exception.ResourceNotFoundException;
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
    public Category addCategory(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public Category editCategory(Category category, long id) throws ResourceNotFoundException {
        Category existCategory = categoryDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found on :: " + id));
        existCategory.setName(category.getName());
        return categoryDao.save(existCategory);
    }

    @Override
    public void deleteCategory(long id) throws ResourceNotFoundException {
        Category existCategory = categoryDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found on :: " + id));
        categoryDao.deleteById(existCategory.getId());
    }

    @Override
    public Category findCategoryById(long id) {
        return categoryDao.findById(id).orElse(null);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryDao.findAll();
    }

}
