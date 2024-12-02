package edu.miu.cse.finalproject.dto.job.response;

public record JobResponseDTO(
        Long id,
        String title,
        String description,
        Double price,
        String category
) {
}

