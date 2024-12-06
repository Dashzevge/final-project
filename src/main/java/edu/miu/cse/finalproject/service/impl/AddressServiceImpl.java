package edu.miu.cse.finalproject.service.impl;

import edu.miu.cse.finalproject.dto.address.request.AddressRequestDTO;
import edu.miu.cse.finalproject.dto.address.response.AddressResponseDTO;
import edu.miu.cse.finalproject.exception.address.AddressNotFoundException;
import edu.miu.cse.finalproject.mapper.AddressMapper;
import edu.miu.cse.finalproject.model.Address;
import edu.miu.cse.finalproject.repository.AddressRepository;
import edu.miu.cse.finalproject.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public Optional<AddressResponseDTO> addAddress(AddressRequestDTO dto) {
        Address booking = addressMapper.toEntity(dto);
        Address savedAddress = addressRepository.save(booking);
        return Optional.of(addressMapper.toResponse(savedAddress));
    }

    @Override
    public Optional<AddressResponseDTO> findAddressById(Long id) throws AddressNotFoundException {
        Address booking = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address not found with ID: " + id));
        return Optional.of(addressMapper.toResponse(booking));
    }

    @Override
    public List<AddressResponseDTO> findAllAddress() {
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<AddressResponseDTO> updateAddress(Long id, AddressRequestDTO dto) throws AddressNotFoundException {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address not found with ID: " + id));
        address.setState(dto.getState());
        address.setCity(dto.getCity());
        address.setStreet(dto.getStreet());
        address.setZipCode(dto.getZipCode());
        Address updatedAddress = addressRepository.save(address);
        return Optional.of(addressMapper.toResponse(updatedAddress));
    }

    @Override
    public void deleteAddress(Long id) throws AddressNotFoundException {
        if (!addressRepository.existsById(id)) {
            throw new AddressNotFoundException("Address not found with ID: " + id);
        }
        addressRepository.deleteById(id);
    }
}
