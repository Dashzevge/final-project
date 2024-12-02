package edu.miu.cse.finalproject.dto.profile.response;

public record ProfileResponseDTO(
        Long id,
        String bio,
        String phoneNumber,
        int experienceYears
) {
}

