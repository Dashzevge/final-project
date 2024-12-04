package edu.miu.cse.finalproject.dto.job.request;

import edu.miu.cse.finalproject.util.Category;
import edu.miu.cse.finalproject.util.JobStatus;

public record JobRequestDTO(
         String title,
         String description,
         Double price,
         JobStatus status,
         Category category,
         Long clientId
) {
}

