package edu.miu.cse.finalproject.service;

import edu.miu.cse.finalproject.dto.user.request.UserRequestDTO;
import edu.miu.cse.finalproject.dto.user.response.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponseDTO> getAvailableProfessionals();
    Optional<UserResponseDTO> addUser(UserRequestDTO dto);
    Optional<UserResponseDTO> findUserById(Long id);
    Optional<UserResponseDTO> findUserByName(String name);
    List<UserResponseDTO> findAllUsers();
    Optional<UserResponseDTO> updateUser(Long id, UserRequestDTO dto);
    void deleteUser(Long id);
}
