package edu.miu.cse.finalproject.service;

import edu.miu.cse.finalproject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> findUserById(Long id);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
    User updateUser(Long id, User updatedUser);
    void deleteUser(Long id);
    List<User> findAllUsers();
}
