package edu.miu.cse.finalproject.service;

import edu.miu.cse.finalproject.dto.address.request.AddressRequestDTO;
import edu.miu.cse.finalproject.dto.address.response.AddressResponseDTO;
import edu.miu.cse.finalproject.dto.booking.request.BookingRequestDTO;
import edu.miu.cse.finalproject.dto.booking.response.BookingResponseDTO;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Optional<AddressResponseDTO> addAddress(AddressRequestDTO dto);
    Optional<AddressResponseDTO> findAddressById(Long id);
    List<AddressResponseDTO> findAllAddress();
    Optional<AddressResponseDTO> updateAddress(Long id, AddressRequestDTO dto);
    void deleteAddress(Long id);
}
