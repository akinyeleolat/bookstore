package com.application.bookstore.controller;

import java.util.List;

import com.application.bookstore.exception.DuplicateResourceException;
import com.application.bookstore.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.application.bookstore.model.Book;
import com.application.bookstore.service.BookService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin(origins = "*")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    ResponseEntity<?> createBook(@Valid @RequestBody Book book) throws DuplicateResourceException {
        return bookService.addBook(book);
    }

    @PostMapping("/addBookToCategory/{id}")
    ResponseEntity<?> addBookToCategory(@Valid @RequestBody Book book, @PathVariable long id) throws ResourceNotFoundException {
        return bookService.addBookToCategory(book, id);
    }

    @PutMapping("/editBook/{id}")
    ResponseEntity<?> editBook(@RequestBody Book book, @PathVariable long id) throws ResourceNotFoundException {
        return bookService.editBook(book, id);
    }

    @DeleteMapping("/deleteBook/{id}")
    void deleteBook(@PathVariable long id) throws ResourceNotFoundException {
        bookService.deleteBook(id);
    }

    @GetMapping("/findBookByName/{name}")
    ResponseEntity<?> findBookByName(@PathVariable String name) {
        return bookService.findBookByName(name);
    }

    @GetMapping("/findBookById/{id}")
    ResponseEntity<?>  findBookById(@PathVariable long id) {
        return bookService.findBookById(id);
    }

    @GetMapping("/books")
    ResponseEntity<?>  findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/findBooksForCategory/{id}")
    ResponseEntity<?>  findBooksForCategory(@PathVariable long id) throws ResourceNotFoundException {
        return bookService.findBooksForCategory(id);
    }

}