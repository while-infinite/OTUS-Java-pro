package ru.flamexander.spring.data.jdbc.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.flamexander.spring.data.jdbc.demo.entities.Author;
import ru.flamexander.spring.data.jdbc.demo.services.AuthorsService;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorsController {
    private final AuthorsService authorsService;

    @Autowired
    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable Long id) {
        return authorsService.findById(id).get();
    }
}
