package edu.miu.cse.finalproject.service.impl;

import edu.miu.cse.finalproject.dto.user.request.UserRequestDTO;
import edu.miu.cse.finalproject.dto.user.response.UserResponseDTO;
import edu.miu.cse.finalproject.mapper.UserMapper;
import edu.miu.cse.finalproject.model.User;
import edu.miu.cse.finalproject.repository.UserRepository;
import edu.miu.cse.finalproject.service.UserService;
import edu.miu.cse.finalproject.util.Role;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<UserResponseDTO> addUser(UserRequestDTO dto) {
        User user = userMapper.toEntity(dto);
        User savedUser = userRepository.save(user);
        return Optional.of(userMapper.toResponse(savedUser));
    }

    @Override
    public Optional<UserResponseDTO> findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        return Optional.of(userMapper.toResponse(user));
    }

    @Override
    public Optional<UserResponseDTO> findUserByName(String name) {
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new EntityNotFoundException("User not found with Name: " + name));
        return Optional.of(userMapper.toResponse(user));
    }

    @Override
    public List<UserResponseDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<UserResponseDTO> updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setRole(Role.valueOf(dto.role()));
        User updatedUser = userRepository.save(user);
        return Optional.of(userMapper.toResponse(updatedUser));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

}

