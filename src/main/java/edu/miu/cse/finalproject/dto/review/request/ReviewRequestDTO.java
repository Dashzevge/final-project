package edu.miu.cse.finalproject.dto.review.request;

public record ReviewRequestDTO(
        Long bookingId,
        String content,
        Integer rating
) {
}

