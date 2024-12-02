package edu.miu.cse.finalproject.dto.certification.response;

import java.time.LocalDate;

public record CertificationResponseDTO(
        Long id,
        String title,
        String description,
        LocalDate issueDate,
        Long userId
) {
}

