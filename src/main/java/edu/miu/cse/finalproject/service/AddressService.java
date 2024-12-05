package edu.miu.cse.finalproject.service;

import edu.miu.cse.finalproject.dto.address.request.AddressRequestDTO;
import edu.miu.cse.finalproject.dto.address.response.AddressResponseDTO;
import edu.miu.cse.finalproject.dto.booking.request.BookingRequestDTO;
import edu.miu.cse.finalproject.dto.booking.response.BookingResponseDTO;
import edu.miu.cse.finalproject.exception.address.AddressNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Optional<AddressResponseDTO> addAddress(AddressRequestDTO dto);
    Optional<AddressResponseDTO> findAddressById(Long id) throws AddressNotFoundException;
    List<AddressResponseDTO> findAllAddress();
    Optional<AddressResponseDTO> updateAddress(Long id, AddressRequestDTO dto) throws AddressNotFoundException;
    void deleteAddress(Long id) throws AddressNotFoundException;
}
