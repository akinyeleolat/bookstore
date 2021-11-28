package com.application.bookstore.serviceimpl;

import java.util.*;

import com.application.bookstore.exception.DuplicateResourceException;
import com.application.bookstore.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.application.bookstore.repository.BookDao;
import com.application.bookstore.repository.CategoryDao;
import com.application.bookstore.model.Book;
import com.application.bookstore.model.Category;

import com.application.bookstore.service.BookService;

@Transactional
@Component
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final CategoryDao categoryDao;

    public BookServiceImpl(BookDao bookDao, CategoryDao categoryDao) {
        this.bookDao = bookDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public ResponseEntity<?> addBook(Book book) throws DuplicateResourceException {

        Optional<Book> nBook = bookDao.findByName(book.getName());
        if(nBook.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(bookDao.save(book));
        }
        throw new DuplicateResourceException("book named "+book.getName()+" exist");

    }

    @Override
    public ResponseEntity<?> addBookToCategory(Book book, long id) throws ResourceNotFoundException {
        Category category = categoryDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found on :: " + id));
        category.addBookToCategory(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookDao.save(book));
    }

    @Override
    public ResponseEntity<?> editBook(Book book, long id) throws ResourceNotFoundException {
        Book existsBook = bookDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found on :: " + id));
        existsBook.setName(book.getName());
        existsBook.setDescription(book.getDescription());
        existsBook.setPrice(book.getPrice());
        existsBook.setPhoto(book.getPhoto());
        existsBook.setLanguage(book.getLanguage());
        existsBook.setPublishDate(new Date());
        existsBook.setSize(book.getSize());
        existsBook.setPage(book.getPage());
        existsBook.setWriter(book.getWriter());
        existsBook.setPublisher(book.getPublisher());
        return ResponseEntity.status(HttpStatus.OK).body(bookDao.save(book));
    }

    @Override
    public ResponseEntity<?> deleteBook(long id) throws ResourceNotFoundException {
        Book existsBook = bookDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found on :: " + id));
        bookDao.deleteById(existsBook.getId());
        Map<String, String> response = new HashMap<>();
        response.put("deleted", String.valueOf(Boolean.TRUE));
        response.put("book Name", existsBook.getName());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<?> findBookByName(String name) {
        Optional<Book> books = bookDao.findByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(books.orElse(null));
    }

    @Override
    public ResponseEntity<?> findBookById(long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookDao.findById(id).orElse(null));
    }

    @Override
    public ResponseEntity<?> findAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookDao.findAll());
    }

    @Override
    public ResponseEntity<?> findBooksForCategory(long id) throws ResourceNotFoundException {
        Category category = categoryDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found on :: " + id));
        return ResponseEntity.status(HttpStatus.OK).body(category.getBooks());
    }

}
