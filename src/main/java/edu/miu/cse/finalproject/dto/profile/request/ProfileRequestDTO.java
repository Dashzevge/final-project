package edu.miu.cse.finalproject.dto.profile.request;

public record ProfileRequestDTO(
        String bio,
        String profilePictureUrl,
        Long userId
) {
}