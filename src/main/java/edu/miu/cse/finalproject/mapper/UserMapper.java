package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.user.request.UserRequestDTO;
import edu.miu.cse.finalproject.dto.user.response.UserResponseDTO;
import edu.miu.cse.finalproject.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Convert a UserRequestDTO object to a User object
//    @Mapping(source = "username", target = "username")
//    @Mapping(source = "password", target = "password")
    //@Mapping(source = "userRequestDTO.profileRequestDTO", target = "profile")
    User toEntity(UserRequestDTO userRequestDTO);

    // Convert a User object to a UserResponseDTO object
//    @Mapping(source = "username", target = "username")
//    @Mapping(source = "profile", target = "profileRequestDTO")
    UserResponseDTO toResponse(User user);
}


