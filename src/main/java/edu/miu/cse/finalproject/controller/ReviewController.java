package edu.miu.cse.finalproject.controller;

import edu.miu.cse.finalproject.dto.review.request.ReviewRequestDTO;
import edu.miu.cse.finalproject.dto.review.response.ReviewResponseDTO;
import edu.miu.cse.finalproject.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<?> findAllReview() {
        List<ReviewResponseDTO> allReview = reviewService.findAllReviews();
        return ResponseEntity.status(HttpStatus.OK).body(allReview);
    }

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> createReview(@RequestBody ReviewRequestDTO ReviewRequestDTO) {
        ReviewResponseDTO savedReview = reviewService.addReview(ReviewRequestDTO).get();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> findReviewById(@PathVariable Long id) {
        return reviewService.findReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> updateReview(@PathVariable Long id, @RequestBody ReviewRequestDTO updatedReview) {
        ReviewResponseDTO newReview = reviewService.updateReview(id, updatedReview).get();
        return new ResponseEntity<>(newReview, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/submit")
    public ResponseEntity<ReviewResponseDTO> submitReview(@RequestBody ReviewRequestDTO reviewDto) {
        ReviewResponseDTO savedReview = reviewService.submitReview(reviewDto).get();
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }
}
