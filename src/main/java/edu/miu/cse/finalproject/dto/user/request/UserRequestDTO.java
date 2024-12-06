package edu.miu.cse.finalproject.dto.user.request;

import edu.miu.cse.finalproject.dto.address.request.AddressRequestDTO;
import edu.miu.cse.finalproject.dto.profile.request.ProfileRequestDTO;
import edu.miu.cse.finalproject.util.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {
        private String firstName;
        private String lastName;

        @NotBlank(message = "Not blank - null - empty")
        private String username;

        @Size(min = 3, max = 10)
        private String password;

        private String email;
        private Role role;
        private boolean isAvailable;
        private ProfileRequestDTO profile; // Related Profile

        // Getters
        public String getFirstName() {
                return firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public String getUsername() {
                return username;
        }

        public String getPassword() {
                return password;
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

        public ProfileRequestDTO getProfile() {
                return profile;
        }

        // Setters
        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public void setPassword(String password) {
                this.password = password;
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

        public void setProfile(ProfileRequestDTO profile) {
                this.profile = profile;
        }


        public UserRequestDTO(String firstName, String lastName, String username, String password,String email, Role role, boolean isAvailable, ProfileRequestDTO profile) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.username = username;
                this.password = password;
                this.email = email;
                this.role = role;
                this.isAvailable = isAvailable;
                this.profile = profile;
        }
}
