package edu.miu.cse.finalproject.dto.review.response;

public record ReviewResponseDTO(
        Long id,
        Long userId,
        Long jobId,
        String content,
        Integer rating
) {
}

