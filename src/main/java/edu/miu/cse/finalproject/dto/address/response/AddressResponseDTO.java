package edu.miu.cse.finalproject.dto.address.response;

public record AddressResponseDTO(
        Long id,
        String street,
        String city,
        String state,
        String zipCode,
        Long userId
) {
}

