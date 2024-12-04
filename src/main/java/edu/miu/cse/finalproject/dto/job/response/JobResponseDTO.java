package edu.miu.cse.finalproject.dto.job.response;

import edu.miu.cse.finalproject.util.Category;
import edu.miu.cse.finalproject.util.Status;

public record JobResponseDTO(
        Long id,
        String title,
        String description,
        Double price,
        Status status,
        Category category,
        Long clientId
) {
}

