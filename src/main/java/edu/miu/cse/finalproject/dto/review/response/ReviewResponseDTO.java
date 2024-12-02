package edu.miu.cse.finalproject.dto.review.response;

public record ReviewResponseDTO(
        Long id,
        Long bookingId,
        Long userId,
        String comment,
        Integer rating
) {
}

