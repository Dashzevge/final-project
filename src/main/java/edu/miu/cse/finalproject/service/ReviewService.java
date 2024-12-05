package edu.miu.cse.finalproject.service;

import edu.miu.cse.finalproject.dto.review.request.ReviewRequestDTO;
import edu.miu.cse.finalproject.dto.review.response.ReviewResponseDTO;
import edu.miu.cse.finalproject.exception.booking.BookingNotFoundException;
import edu.miu.cse.finalproject.exception.review.ReviewNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Optional<ReviewResponseDTO> addReview(ReviewRequestDTO dto) throws BookingNotFoundException;
    Optional<ReviewResponseDTO> findReviewById(Long id) throws ReviewNotFoundException;
    List<ReviewResponseDTO> findAllReviews();
    Optional<ReviewResponseDTO> updateReview(Long id, ReviewRequestDTO dto) throws ReviewNotFoundException;
    void deleteReview(Long id) throws ReviewNotFoundException;
}
