package ru.flamexander.spring.data.jdbc.demo.dtos;

import jakarta.annotation.Nonnull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReviewDtoRq {
    @Nonnull
    private String author;
    @Nonnull
    private String text;
    @Nonnull
    private int rating;
    @Nonnull
    private LocalDate added;
    @Nonnull
    private Long bookId;

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

    public ReviewDtoRq(String author, String text, int rating, LocalDate added, Long bookId) {
        this.author = author;
        this.text = text;
        this.rating = rating;
        this.added = added;
        this.bookId = bookId;
    }
}
