package edu.miu.cse.finalproject.service.impl;

import edu.miu.cse.finalproject.model.User;
import edu.miu.cse.finalproject.repository.UserRepository;
import edu.miu.cse.finalproject.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    user.setUsername(updatedUser.getUsername());
                    user.setPassword(updatedUser.getPassword());
                    user.setEmail(updatedUser.getEmail());
                    user.setRole(updatedUser.getRole());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("User not found with id " + id);
        }
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}

