package edu.miu.cse.finalproject.dto.job.request;

public record JobRequestDTO(
        String title,
        String description,
        Double price,
        String category
) {
}

