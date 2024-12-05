package edu.miu.cse.finalproject.dto.user.request;

import edu.miu.cse.finalproject.dto.address.request.AddressRequestDTO;
import edu.miu.cse.finalproject.dto.profile.request.ProfileRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        String firstName,
        String lastName,
        @NotBlank(message = "Not blank - null - empty")
        String username,
        @Size(min = 3, max = 10)
        String password,
        String email,
        String role,
        boolean isAvailable,
        ProfileRequestDTO profile   // Related Profile
) {
}

