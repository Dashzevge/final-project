package edu.miu.cse.finalproject.dto.profile.request;

public record ProfileRequestDTO(
        String bio,
        String phoneNumber,
        int experienceYears
) {
}