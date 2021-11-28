package com.application.bookstore.service;

import java.util.List;

import com.application.bookstore.exception.ResourceNotFoundException;
import com.application.bookstore.model.Book;

public interface BookService {

    Book addBook(Book book);

    Book addBookToCategory(Book book, long id) throws ResourceNotFoundException;

    Book editBook(Book book, long id, long idCategory) throws ResourceNotFoundException;

    void deleteBook(long id) throws ResourceNotFoundException;

    Book findBookByName(String name);

    Book findBookById(long id);

    List<Book> findAllBooks();

    List<Book> findBooksForCategory(long id) throws ResourceNotFoundException;
}

