package edu.miu.cse.finalproject.dto.user.response;

public record UserResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String username,
        String email,
        String role,
        boolean isAvailable
) {
}
