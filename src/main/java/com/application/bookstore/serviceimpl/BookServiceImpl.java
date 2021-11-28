package com.application.bookstore.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.application.bookstore.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Book addBook(Book book) {
        return bookDao.save(book);
    }

    @Override
    public Book addBookToCategory(Book book, long id) throws ResourceNotFoundException {
        Category category = categoryDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found on :: " + id));
        category.addBookToCategory(book);
        return bookDao.save(book);
    }

    @Override
    public Book editBook(Book book, long id, long idCategory) throws ResourceNotFoundException {
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
        return bookDao.save(existsBook);
    }

    @Override
    public void deleteBook(long id) throws ResourceNotFoundException {
        Book existsBook = bookDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found on :: " + id));
        bookDao.deleteById(existsBook.getId());
    }

    @Override
    public Book findBookByName(String name) {
        Optional<Book> books = bookDao.findByName(name);
        return books.orElse(null);
    }

    @Override
    public Book findBookById(long id) {
        return bookDao.findById(id).orElse(null);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> findBooksForCategory(long id) throws ResourceNotFoundException {
        Category category = categoryDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found on :: " + id));
        return category.getBooks();
    }

}
