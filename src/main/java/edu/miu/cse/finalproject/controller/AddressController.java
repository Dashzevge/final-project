package edu.miu.cse.finalproject.controller;

import edu.miu.cse.finalproject.dto.address.request.AddressRequestDTO;
import edu.miu.cse.finalproject.dto.address.response.AddressResponseDTO;
import edu.miu.cse.finalproject.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<?> findAllAddress() {
        List<AddressResponseDTO> allAddress = addressService.findAllAddress();
        return ResponseEntity.status(HttpStatus.OK).body(allAddress);
    }

    @PostMapping
    public ResponseEntity<AddressResponseDTO> createAddress(@RequestBody AddressRequestDTO addressRequestDTO) {
        AddressResponseDTO savedAddress = addressService.addAddress(addressRequestDTO).get();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> findAddressById(@PathVariable Long id) {
        return addressService.findAddressById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable Long id, @RequestBody AddressRequestDTO updatedAddress) {
        AddressResponseDTO newAddress = addressService.updateAddress(id, updatedAddress).get();
        return new ResponseEntity<>(newAddress, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
    
}
