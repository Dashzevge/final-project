package edu.miu.cse.finalproject.service.impl;

import edu.miu.cse.finalproject.dto.job.response.JobResponseDTO;
import edu.miu.cse.finalproject.dto.user.request.UserRequestDTO;
import edu.miu.cse.finalproject.dto.user.response.UserResponseDTO;
import edu.miu.cse.finalproject.exception.user.UserNotFoundException;
import edu.miu.cse.finalproject.mapper.AddressMapper;
import edu.miu.cse.finalproject.mapper.JobMapper;
import edu.miu.cse.finalproject.mapper.ProfileMapper;
import edu.miu.cse.finalproject.mapper.UserMapper;
import edu.miu.cse.finalproject.model.Address;
import edu.miu.cse.finalproject.model.Profile;
import edu.miu.cse.finalproject.model.User;
import edu.miu.cse.finalproject.repository.AddressRepository;
import edu.miu.cse.finalproject.repository.JobRepository;
import edu.miu.cse.finalproject.repository.ProfileRepository;
import edu.miu.cse.finalproject.repository.UserRepository;
import edu.miu.cse.finalproject.service.UserService;
import edu.miu.cse.finalproject.util.Role;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final UserMapper userMapper;
    private final ProfileMapper profileMapper;
    private final AddressMapper addressMapper;

    @Override
    public Optional<UserResponseDTO> addUser(UserRequestDTO dto) {
        // Map the User DTO to User entity
        User user = userMapper.toEntity(dto);

        Profile profile = profileMapper.toEntity(dto.profile());

        Address address = addressMapper.toEntity(dto.profile().address());

        address.setProfile(profile);
        addressRepository.save(address);

        profile.setAddress(address);

        profileRepository.save(profile);

        user.setProfile(profile);

        User savedUser = userRepository.save(user);

        return Optional.of(userMapper.toResponse(savedUser));
    }



    @Override
    public Optional<UserResponseDTO> findUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        return Optional.of(userMapper.toResponse(user));
    }

    @Override
    public Optional<UserResponseDTO> findUserByName(String name) throws UserNotFoundException {
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new UserNotFoundException("User not found with Name: " + name));
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
    public Optional<UserResponseDTO> updateUser(Long id, UserRequestDTO dto) throws UserNotFoundException{
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setRole(Role.valueOf(dto.role()));
        User updatedUser = userRepository.save(user);
        return Optional.of(userMapper.toResponse(updatedUser));
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException{
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
    @Override
    public List<UserResponseDTO> getAvailableProfessionals() {

        List<User> professionals = userRepository.findAllByRoleAndAvailability(Role.PROFESSIONAL, true);
        if (professionals.size() < 0) {
            throw new EntityNotFoundException("Professionals not found");
        }

        return professionals.stream()
                .map(userMapper::toResponse) // Map User entity to DTO
                .collect(Collectors.toList());
    }
}

