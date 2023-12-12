package ru.flamexander.spring.data.jdbc.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.flamexander.spring.data.jdbc.demo.dtos.DetailedBookDto;
import ru.flamexander.spring.data.jdbc.demo.services.BooksService;

import java.util.List;

@Controller
public class ThymeleafController {
    private final BooksService booksService;

    @Autowired
    public ThymeleafController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/list")
    public String showAllBooksPage(Model model) {
        List<DetailedBookDto> list = booksService.findAllDetailedBooks();
        model.addAttribute("books", list);
        return "books";
    }
}
