package com.application.bookstore.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.application.bookstore.model.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

}
