package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.address.request.AddressRequestDTO;
import edu.miu.cse.finalproject.dto.address.response.AddressResponseDTO;
import edu.miu.cse.finalproject.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(source = "state", target = "state")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "zipCode", target = "zipCode")
    Address toEntity(AddressRequestDTO dto);
    @Mapping(source = "state", target = "state")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "zipCode", target = "zipCode")
    AddressResponseDTO toResponse(Address entity);
}
