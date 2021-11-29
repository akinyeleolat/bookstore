package com.application.bookstore.service;

import com.application.bookstore.exception.DuplicateResourceException;
import com.application.bookstore.exception.ResourceNotFoundException;
import com.application.bookstore.model.Book;
import org.springframework.http.ResponseEntity;

public interface BookService {

    ResponseEntity<?> addBook(Book book) throws DuplicateResourceException;

    ResponseEntity<?> addBookToCategory(Book book, long id) throws ResourceNotFoundException;

    ResponseEntity<?> addBookToCategory(long bookId, long categoryId) throws ResourceNotFoundException;

    ResponseEntity<?> editBook(Book book, long id) throws ResourceNotFoundException;

    ResponseEntity<?> deleteBook(long id) throws ResourceNotFoundException;

    ResponseEntity<?> findBookByName(String name);

    ResponseEntity<?> findBookById(long id);

    ResponseEntity<?> findAllBooks();

    ResponseEntity<?> findBooksForCategory(long id) throws ResourceNotFoundException;
}

