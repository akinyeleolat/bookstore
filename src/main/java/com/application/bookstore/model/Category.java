package com.application.bookstore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false, columnDefinition="varchar(20)" )
    private String name;

    private boolean expanded;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Book> books;

    public Category() {
        super();
    }


    public Category(String name, boolean expanded, List<Book> books) {
        super();
        this.name = name;
        this.expanded = expanded;
        this.books = books;
    }

    public void addBookToCategory(Book book) {
        if (getBooks() == null) {
            this.books = new ArrayList<>();
        }
        getBooks().add(book);
        book.setCategory(this);
    }

//    public List<Book> getBooks() {
//        return books;
//    }
//
//    public void setBooks(List<Book> books) {
//        this.books = books;
//    }


}
