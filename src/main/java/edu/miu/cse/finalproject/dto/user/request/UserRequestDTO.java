package edu.miu.cse.finalproject.dto.user.request;

import edu.miu.cse.finalproject.dto.address.request.AddressRequestDTO;
import edu.miu.cse.finalproject.dto.profile.request.ProfileRequestDTO;

public record UserRequestDTO(
        String firstName,
        String lastName,
        String username,
        String password,
        String email,
        String role,
        boolean isAvailable,
        ProfileRequestDTO profile   // Related Profile
) {
}

