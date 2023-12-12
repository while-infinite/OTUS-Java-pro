package ru.flamexander.spring.data.jdbc.demo.dtos;

import java.time.LocalDate;

public class ReviewDtoRs {
    private Long id;
    private String author;
    private String text;
    private int rating;
    private LocalDate added;
    private Long bookId;

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

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public ReviewDtoRs(Long id, String author, String text, int rating, LocalDate added, Long bookId) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.rating = rating;
        this.added = added;
        this.bookId = bookId;
    }
}
