package edu.miu.cse.finalproject.service.impl;

import edu.miu.cse.finalproject.dto.review.request.ReviewRequestDTO;
import edu.miu.cse.finalproject.dto.review.response.ReviewResponseDTO;
import edu.miu.cse.finalproject.mapper.ReviewMapper;
import edu.miu.cse.finalproject.model.Booking;
import edu.miu.cse.finalproject.model.Review;
import edu.miu.cse.finalproject.repository.BookingRepository;
import edu.miu.cse.finalproject.repository.ReviewRepository;
import edu.miu.cse.finalproject.repository.UserRepository;
import edu.miu.cse.finalproject.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public Optional<ReviewResponseDTO> addReview(ReviewRequestDTO dto) {
        Review review = reviewMapper.toEntity(dto);
        Review savedReview = reviewRepository.save(review);
        return Optional.of(reviewMapper.toResponse(savedReview));
    }

    @Override
    public Optional<ReviewResponseDTO> findReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with ID: " + id));
        return Optional.of(reviewMapper.toResponse(review));
    }

    @Override
    public List<ReviewResponseDTO> findAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<ReviewResponseDTO> updateReview(Long id, ReviewRequestDTO dto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with ID: " + id));
        review.setContent(dto.content());
        review.setRating(dto.rating());
        Review updatedReview = reviewRepository.save(review);
        return Optional.of(reviewMapper.toResponse(updatedReview));
    }

    @Override
    public Optional<ReviewResponseDTO> submitReview(ReviewRequestDTO dto) {
        Booking booking = bookingRepository.findById(dto.bookingId())
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + dto.bookingId()));

        if (!"COMPLETED".equals(booking.getStatus())) {
            throw new IllegalStateException("Cannot submit a review for a booking that is not completed.");
        }

        Review review = new Review();
        review.setContent(dto.content());
        review.setRating(dto.rating());
        review.setJob(booking.getJob());
        review.setClient(booking.getJob().getClient());
        review.setProfessional(booking.getProfessional());

        Review savedReview = reviewRepository.save(review);

        return Optional.of(reviewMapper.toResponse(savedReview));
    }


    @Override
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new EntityNotFoundException("Review not found with ID: " + id);
        }
        reviewRepository.deleteById(id);
    }
}
