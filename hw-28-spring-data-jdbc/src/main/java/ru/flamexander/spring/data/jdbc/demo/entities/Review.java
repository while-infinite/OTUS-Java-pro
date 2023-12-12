package ru.flamexander.spring.data.jdbc.demo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table("REVIEWS")
public class Review {
    @Id
    private Long id;
    private String author;
    private String text;
    private int rating;
    private LocalDate added;
    private Long bookId;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDate getAdded() {
        return added;
    }

    public void setAdded(LocalDate added) {
        this.added = added;
    }

    @PersistenceCreator
    public Review(Long id, String author, String text, int rating, LocalDate added, Long bookId) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.rating = rating;
        this.added = added;
        this.bookId = bookId;
    }

    public Review(String author, String text, int rating, LocalDate added, Long bookId) {
        this.author = author;
        this.text = text;
        this.rating = rating;
        this.added = added;
        this.bookId = bookId;
    }
}
