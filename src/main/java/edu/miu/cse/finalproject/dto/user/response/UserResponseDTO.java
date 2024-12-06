package edu.miu.cse.finalproject.dto.user.response;

import edu.miu.cse.finalproject.util.Role;

public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Role role;
    private boolean isAvailable;


    // Getters
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long id, String firstName, String lastName, String username, String email, Role role, boolean isAvailable) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.role = role;
        this.isAvailable = isAvailable;
    }
}
