package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.address.request.AddressRequestDTO;
import edu.miu.cse.finalproject.dto.address.response.AddressResponseDTO;
import edu.miu.cse.finalproject.model.Address;
import edu.miu.cse.finalproject.model.Profile;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    // Mapping AddressRequestDTO to Address
    public Address toEntity(AddressRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setZipCode(dto.getZipCode());

        // Map userId to profile.id
        if (dto.getUserId() != null) {
            Profile profile = new Profile();
            profile.setId(dto.getUserId());
            address.setProfile(profile);
        }

        return address;
    }

    // Mapping Address entity to AddressResponseDTO
    public AddressResponseDTO toResponse(Address entity) {
        if (entity == null) {
            return null;
        }

        AddressResponseDTO response = new AddressResponseDTO();
        response.setId(entity.getId());
        response.setStreet(entity.getStreet());
        response.setCity(entity.getCity());
        response.setState(entity.getState());
        response.setZipCode(entity.getZipCode());

        // Map profile.id to userId in DTO
        if (entity.getProfile() != null) {
            response.setUserId(entity.getProfile().getId());
        }

        return response;
    }
}
