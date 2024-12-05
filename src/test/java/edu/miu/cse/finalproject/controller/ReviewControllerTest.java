package edu.miu.cse.finalproject.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.miu.cse.finalproject.dto.review.request.ReviewRequestDTO;
import edu.miu.cse.finalproject.dto.review.response.ReviewResponseDTO;
import edu.miu.cse.finalproject.exception.booking.BookingNotFoundException;
import edu.miu.cse.finalproject.exception.review.ReviewNotFoundException;
import edu.miu.cse.finalproject.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @Test
    void findAllReview() {
        List<ReviewResponseDTO> mockReviews = List.of(
                new ReviewResponseDTO("Great service!", 5),
                new ReviewResponseDTO("Good experience", 4)
        );

        when(reviewService.findAllReviews()).thenReturn(mockReviews);

        ResponseEntity<?> response = reviewController.findAllReview();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReviews, response.getBody());

        verify(reviewService, times(1)).findAllReviews();
    }

    @Test
    void createReview() throws BookingNotFoundException {
        ReviewRequestDTO reviewRequestDTO = new ReviewRequestDTO(1L, "Amazing experience!", 5);
        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO("Amazing experience!", 5);

        when(reviewService.addReview(reviewRequestDTO)).thenReturn(Optional.of(reviewResponseDTO));

        ResponseEntity<ReviewResponseDTO> response = reviewController.createReview(reviewRequestDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(reviewResponseDTO, response.getBody());

        verify(reviewService, times(1)).addReview(reviewRequestDTO);
    }

    @Test
    void findReviewById() throws ReviewNotFoundException {
        Long reviewId = 1L;
        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO("Excellent service", 5);

        when(reviewService.findReviewById(reviewId)).thenReturn(Optional.of(reviewResponseDTO));

        ResponseEntity<ReviewResponseDTO> response = reviewController.findReviewById(reviewId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviewResponseDTO, response.getBody());

        verify(reviewService, times(1)).findReviewById(reviewId);
    }

    @Test
    void updateReview() throws ReviewNotFoundException {
        Long reviewId = 1L;
        ReviewRequestDTO updatedReview = new ReviewRequestDTO(1L, "Updated content", 4);
        ReviewResponseDTO updatedReviewResponse = new ReviewResponseDTO("Updated content", 4);

        when(reviewService.updateReview(reviewId, updatedReview)).thenReturn(Optional.of(updatedReviewResponse));

        ResponseEntity<ReviewResponseDTO> response = reviewController.updateReview(reviewId, updatedReview);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedReviewResponse, response.getBody());

        verify(reviewService, times(1)).updateReview(reviewId, updatedReview);
    }


    @Test
    void deleteReview() throws ReviewNotFoundException {
        Long reviewId = 1L;

        doNothing().when(reviewService).deleteReview(reviewId);

        ResponseEntity<Void> response = reviewController.deleteReview(reviewId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(reviewService, times(1)).deleteReview(reviewId);
    }
}
