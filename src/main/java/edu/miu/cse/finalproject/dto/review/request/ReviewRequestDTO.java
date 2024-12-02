package edu.miu.cse.finalproject.dto.review.request;

public record ReviewRequestDTO(
        Long bookingId,
        Long userId,
        String comment,
        Integer rating
) {
}

