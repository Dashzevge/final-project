package edu.miu.cse.finalproject.dto.job.request;

import edu.miu.cse.finalproject.util.Category;
import edu.miu.cse.finalproject.util.Status;

public record JobRequestDTO(
         String title,
         String description,
         Double price,
         Status status,
         Category category
) {
}

