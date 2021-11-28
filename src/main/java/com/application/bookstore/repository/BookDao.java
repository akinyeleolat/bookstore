package com.application.bookstore.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.application.bookstore.model.Book;

public interface BookDao extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);
}
