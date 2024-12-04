package edu.miu.cse.finalproject.dto.job.response;

import edu.miu.cse.finalproject.util.Category;
import edu.miu.cse.finalproject.util.JobStatus;

public record JobResponseDTO(
        Long id,
        String title,
        String description,
        Double price,
        JobStatus status,
        Category category,
        Long clientId
) {
}

