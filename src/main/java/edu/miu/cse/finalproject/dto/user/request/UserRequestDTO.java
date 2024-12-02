package edu.miu.cse.finalproject.dto.user.request;

public record UserRequestDTO(
        String firstName,
        String lastName,
        String username,
        String password,
        String email,
        String role
) {
}

