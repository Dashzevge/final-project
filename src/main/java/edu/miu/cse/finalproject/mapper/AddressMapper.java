package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.address.request.AddressRequestDTO;
import edu.miu.cse.finalproject.dto.address.response.AddressResponseDTO;
import edu.miu.cse.finalproject.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    // Mapping AddressRequestDTO to Address
    @Mapping(source = "userId", target = "profile.id") // Map userId to profile.id
    @Mapping(source = "street", target = "street")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "zipCode", target = "zipCode")
    Address toEntity(AddressRequestDTO dto);

    // Mapping Address entity to AddressResponseDTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "zipCode", target = "zipCode")
    @Mapping(source = "profile.id", target = "userId") // Map profile.id to userId in DTO
    AddressResponseDTO toResponse(Address entity);
}
