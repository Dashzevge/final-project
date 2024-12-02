package edu.miu.cse.finalproject.dto.address.request;

public record AddressRequestDTO(
        String street,
        String city,
        String state,
        String zipCode,
        String country,
        Long userId
) {
}
