package ru.flamexander.spring.data.jdbc.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.flamexander.spring.data.jdbc.demo.dtos.DetailedBookDto;
import ru.flamexander.spring.data.jdbc.demo.dtos.PageDto;
import ru.flamexander.spring.data.jdbc.demo.services.BooksService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {
    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public PageDto findAllDetailedBooks(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return booksService.findAllDetailedBooks(page, size);
    }

    @PatchMapping("/{id}/title")
    public void updateTitleById(@PathVariable Long id, @RequestParam String value) {
        booksService.updateTitleById(id, value);
    }

    @GetMapping("/top10")
    public List<DetailedBookDto> findAllTop10DetailedBooks() {
       return booksService.findAllTop10DetailedBooks();
    }
}
