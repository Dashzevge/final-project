package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.review.request.ReviewRequestDTO;
import edu.miu.cse.finalproject.dto.review.response.ReviewResponseDTO;
import edu.miu.cse.finalproject.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    // Mapping ReviewRequestDTO to Review entity
    public Review toEntity(ReviewRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Review review = new Review();
        review.setContent(dto.getContent());
        review.setRating(dto.getRating());

        return review;
    }

    // Mapping Review entity to ReviewResponseDTO
    public ReviewResponseDTO toResponse(Review entity) {
        if (entity == null) {
            return null;
        }

        ReviewResponseDTO response = new ReviewResponseDTO();
        response.setContent(entity.getContent());
        response.setRating(entity.getRating());

        return response;
    }
}
