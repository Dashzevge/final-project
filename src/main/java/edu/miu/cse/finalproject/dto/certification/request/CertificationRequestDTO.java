package edu.miu.cse.finalproject.dto.certification.request;

import java.time.LocalDate;

public record CertificationRequestDTO(
        String title,
        String description,
        LocalDate issueDate,
        Long userId
) {
}
