package edu.miu.cse.finalproject.service;

import edu.miu.cse.finalproject.dto.review.request.ReviewRequestDTO;
import edu.miu.cse.finalproject.dto.review.response.ReviewResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Optional<ReviewResponseDTO> addReview(ReviewRequestDTO dto);
    Optional<ReviewResponseDTO> findReviewById(Long id);
    List<ReviewResponseDTO> findAllReviews();
    Optional<ReviewResponseDTO> updateReview(Long id, ReviewRequestDTO dto);
    void deleteReview(Long id);
}
