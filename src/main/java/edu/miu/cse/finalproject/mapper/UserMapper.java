package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.user.request.UserRequestDTO;
import edu.miu.cse.finalproject.dto.user.response.UserResponseDTO;
import edu.miu.cse.finalproject.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // Convert a UserRequestDTO object to a User object
    public User toEntity(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) {
            return null;
        }

        User user = new User();
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(userRequestDTO.getPassword());
        user.setEmail(userRequestDTO.getEmail());
        user.setRole(userRequestDTO.getRole());

        // If you want to set the profile, you can manually map the profile here
        // Assuming profileRequestDTO is a nested object in UserRequestDTO
        // If required, you can use ProfileMapper to map the profile

        // user.setProfile(profileMapper.toEntity(userRequestDTO.profileRequestDTO));

        return user;
    }

    // Convert a User object to a UserResponseDTO object
    public UserResponseDTO toResponse(User user) {
        if (user == null) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRole(user.getRole());

        // If you need to map the profile into the response, you can manually map it here
        // If required, you can use ProfileMapper to map the profile
        // userResponseDTO.setProfile(profileMapper.toResponse(user.getProfile()));

        return userResponseDTO;
    }
}
