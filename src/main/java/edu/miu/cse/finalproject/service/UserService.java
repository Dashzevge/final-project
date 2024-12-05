package edu.miu.cse.finalproject.service;

import edu.miu.cse.finalproject.dto.user.request.UserRequestDTO;
import edu.miu.cse.finalproject.dto.user.response.UserResponseDTO;
import edu.miu.cse.finalproject.exception.user.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponseDTO> getAvailableProfessionals();
    Optional<UserResponseDTO> addUser(UserRequestDTO dto);
    Optional<UserResponseDTO> findUserById(Long id) throws UserNotFoundException;
    Optional<UserResponseDTO> findUserByName(String name) throws UserNotFoundException;
    List<UserResponseDTO> findAllUsers();
    Optional<UserResponseDTO> updateUser(Long id, UserRequestDTO dto) throws UserNotFoundException;
    void deleteUser(Long id) throws UserNotFoundException;
}
