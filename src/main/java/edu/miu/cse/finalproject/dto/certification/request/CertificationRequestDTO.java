package edu.miu.cse.finalproject.dto.certification.request;

import java.time.LocalDate;

public record CertificationRequestDTO(
        String name,
        String authority,
        LocalDate issueDate,
        LocalDate expiryDate,
        Long profileId
) {
}
