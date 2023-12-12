package ru.flamexander.spring.data.jdbc.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.flamexander.spring.data.jdbc.demo.dtos.ReviewDtoRq;
import ru.flamexander.spring.data.jdbc.demo.dtos.ReviewDtoRs;
import ru.flamexander.spring.data.jdbc.demo.services.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDtoRs> findAllReviews() {
        return reviewService.findAllReviews();
    }

    @PostMapping("/add")
    public void addReview(@RequestBody ReviewDtoRq review) {
        reviewService.addReview(review);
    }
}
