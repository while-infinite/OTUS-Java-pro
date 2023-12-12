package ru.flamexander.spring.data.jdbc.demo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("BOOKS")
public class Book {
    @Id
    private Long id;
    private String title;
    private Long authorId;
    private Genre genre;

    @MappedCollection(idColumn = "BOOK_ID")
    private BookDetails bookDetails;

    @MappedCollection(idColumn = "BOOK_ID")
    private Set<Review> review;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public BookDetails getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(BookDetails bookDetails) {
        this.bookDetails = bookDetails;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<Review> getReview() {
        return review;
    }

    public void setReview(Set<Review> review) {
        this.review = review;
    }

    @PersistenceCreator
    public Book(Long id, String title, Long authorId, Genre genre, BookDetails bookDetails, Set<Review> review) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.genre = genre;
        this.bookDetails = bookDetails;
        this.review = review;
    }
}
