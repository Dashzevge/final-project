package edu.miu.cse.finalproject.dto.certification.response;

import java.time.LocalDate;

public record CertificationResponseDTO(
        Long id,
        String name,
        String authority,
        LocalDate issueDate,
        LocalDate expiryDate,
        Long userId
) {
}

