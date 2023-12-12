package ru.flamexander.spring.data.jdbc.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.flamexander.spring.data.jdbc.demo.dtos.ReviewDtoRq;
import ru.flamexander.spring.data.jdbc.demo.dtos.ReviewDtoRs;
import ru.flamexander.spring.data.jdbc.demo.entities.Review;
import ru.flamexander.spring.data.jdbc.demo.repositories.ReviewRepository;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewDtoRs> findAllReviews() {
        var reviewList = reviewRepository.findAll();
        return reviewList.stream().map(this::reviewToReviewDtoRs).toList();
    }

    public void addReview(ReviewDtoRq reviewDto) {
        var review = this.reviewDtoRqToReview(reviewDto);
        reviewRepository.save(review);
    }

    private ReviewDtoRs reviewToReviewDtoRs(Review review) {
        return new ReviewDtoRs(
                review.getId(),
                review.getAuthor(),
                review.getText(),
                review.getRating(),
                review.getAdded(),
                review.getBookId()
        );
    }

    private Review reviewDtoRqToReview(ReviewDtoRq reviewDto) {
        return new Review(
                reviewDto.getAuthor(),
                reviewDto.getText(),
                reviewDto.getRating(),
                reviewDto.getAdded(),
                reviewDto.getBookId()
        );
    }
}
