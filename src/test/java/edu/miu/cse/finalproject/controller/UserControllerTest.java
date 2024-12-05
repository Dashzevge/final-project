package edu.miu.cse.finalproject.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.miu.cse.finalproject.dto.user.request.UserRequestDTO;
import edu.miu.cse.finalproject.dto.user.response.UserResponseDTO;
import edu.miu.cse.finalproject.exception.user.UserNotFoundException;
import edu.miu.cse.finalproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void findAllUsers() {
        List<UserResponseDTO> mockUsers = List.of(
                new UserResponseDTO(1L, "John", "Doe", "john_doe", "john@example.com", "MEMBER", true),
                new UserResponseDTO(2L, "Jane", "Doe", "jane_doe", "jane@example.com", "PROFESSIONAL", false)
        );

        when(userService.findAllUsers()).thenReturn(mockUsers);

        ResponseEntity<?> response = userController.findAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsers, response.getBody());

        verify(userService, times(1)).findAllUsers();
    }

    @Test
    void createUser() {
        UserRequestDTO userRequestDTO = new UserRequestDTO(
                "John", "Doe", "john_doe", "password123", "john@example.com", "MEMBER", true, null);

        UserResponseDTO userResponseDTO = new UserResponseDTO(
                1L, "John", "Doe", "john_doe", "john@example.com", "MEMBER", true);

        when(userService.addUser(userRequestDTO)).thenReturn(Optional.of(userResponseDTO));

        ResponseEntity<UserResponseDTO> response = userController.createUser(userRequestDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userResponseDTO, response.getBody());

        verify(userService, times(1)).addUser(userRequestDTO);
    }

    @Test
    void findUserById() throws UserNotFoundException {
        Long userId = 1L;
        UserResponseDTO userResponseDTO = new UserResponseDTO(
                1L, "John", "Doe", "john_doe", "john@example.com", "MEMBER", true);

        when(userService.findUserById(userId)).thenReturn(Optional.of(userResponseDTO));

        ResponseEntity<UserResponseDTO> response = userController.findUserById(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponseDTO, response.getBody());

        verify(userService, times(1)).findUserById(userId);
    }

    @Test
    void updateUser() throws UserNotFoundException {
        Long userId = 1L;
        UserRequestDTO updatedUser = new UserRequestDTO(
                "John", "Doe", "john_doe_updated", "password123", "john_updated@example.com", "MEMBER", true, null);

        UserResponseDTO updatedUserResponse = new UserResponseDTO(
                1L, "John", "Doe", "john_doe_updated", "john_updated@example.com", "MEMBER", true);

        when(userService.updateUser(userId, updatedUser)).thenReturn(Optional.of(updatedUserResponse));

        ResponseEntity<UserResponseDTO> response = userController.updateUser(userId, updatedUser);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUserResponse, response.getBody());

        verify(userService, times(1)).updateUser(userId, updatedUser);
    }

    @Test
    void deleteUser() throws UserNotFoundException {
        Long userId = 1L;

        doNothing().when(userService).deleteUser(userId);

        ResponseEntity<Void> response = userController.deleteUser(userId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(userService, times(1)).deleteUser(userId);
    }
}
