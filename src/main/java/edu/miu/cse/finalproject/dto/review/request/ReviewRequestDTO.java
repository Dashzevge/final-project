package edu.miu.cse.finalproject.dto.review.request;

public record ReviewRequestDTO(
        Long userId,
        Long jobId,
        String content,
        Integer rating
) {
}

