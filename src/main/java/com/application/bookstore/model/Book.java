package com.application.bookstore.model;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, nullable = false, columnDefinition="varchar(20)" )
    private String name;
    private String photo;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;
    @Column(unique = true, nullable = false, columnDefinition="varchar(20)" )
    private String writer;
    @Column(unique = true, nullable = false )
    private double price;
    private String language;
    @Column(unique = true, nullable = false)
    private String publisher;
    private int page;
    private float size;

    @ManyToOne
    @JsonBackReference(value = "category")
    private Category category;

    public Book() {
        super();
    }

    public Book(String name, String photo, String description, Date publishDate, String writer, double price,
                String language, String publisher, int page, float size, Category category) {
        super();
        this.name = name;
        this.photo = photo;
        this.description = description;
        this.publishDate = publishDate;
        this.writer = writer;
        this.price = price;
        this.language = language;
        this.publisher = publisher;
        this.page = page;
        this.size = size;
        this.category = category;
    }
}
