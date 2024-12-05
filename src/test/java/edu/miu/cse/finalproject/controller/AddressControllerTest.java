package edu.miu.cse.finalproject.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.miu.cse.finalproject.dto.address.request.AddressRequestDTO;
import edu.miu.cse.finalproject.dto.address.response.AddressResponseDTO;
import edu.miu.cse.finalproject.exception.address.AddressNotFoundException;
import edu.miu.cse.finalproject.service.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @Test
    void findAllAddress() {
        List<AddressResponseDTO> mockAddresses = List.of(
                new AddressResponseDTO(1L, "123 Main St", "Springfield", "IL", "62701", 1L),
                new AddressResponseDTO(2L, "456 Elm St", "Chicago", "IL", "60601", 2L)
        );

        when(addressService.findAllAddress()).thenReturn(mockAddresses);

        ResponseEntity<?> response = addressController.findAllAddress();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAddresses, response.getBody());

        verify(addressService, times(1)).findAllAddress();
    }

    @Test
    void createAddress() {
        AddressRequestDTO addressRequestDTO = new AddressRequestDTO("123 Main St", "Springfield", "IL", "62701", 1L);
        AddressResponseDTO addressResponseDTO = new AddressResponseDTO(1L, "123 Main St", "Springfield", "IL", "62701", 1L);

        when(addressService.addAddress(addressRequestDTO)).thenReturn(Optional.of(addressResponseDTO));

        ResponseEntity<AddressResponseDTO> response = addressController.createAddress(addressRequestDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(addressResponseDTO, response.getBody());

        verify(addressService, times(1)).addAddress(addressRequestDTO);
    }

    @Test
    void findAddressById() throws AddressNotFoundException {
        Long addressId = 1L;
        AddressResponseDTO addressResponseDTO = new AddressResponseDTO(1L, "123 Main St", "Springfield", "IL", "62701", 1L);

        when(addressService.findAddressById(addressId)).thenReturn(Optional.of(addressResponseDTO));

        ResponseEntity<AddressResponseDTO> response = addressController.findAddressById(addressId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(addressResponseDTO, response.getBody());

        verify(addressService, times(1)).findAddressById(addressId);
    }

    @Test
    void updateAddress() throws AddressNotFoundException {
        Long addressId = 1L;
        AddressRequestDTO updatedAddress = new AddressRequestDTO("789 Oak St", "Chicago", "IL", "60605", 1L);
        AddressResponseDTO updatedAddressResponse = new AddressResponseDTO(1L, "789 Oak St", "Chicago", "IL", "60605", 1L);

        when(addressService.updateAddress(addressId, updatedAddress)).thenReturn(Optional.of(updatedAddressResponse));

        ResponseEntity<AddressResponseDTO> response = addressController.updateAddress(addressId, updatedAddress);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedAddressResponse, response.getBody());

        verify(addressService, times(1)).updateAddress(addressId, updatedAddress);
    }

    @Test
    void deleteAddress() throws AddressNotFoundException {
        Long addressId = 1L;

        doNothing().when(addressService).deleteAddress(addressId);

        ResponseEntity<Void> response = addressController.deleteAddress(addressId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(addressService, times(1)).deleteAddress(addressId);
    }
}
