package edu.miu.cse.finalproject.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.miu.cse.finalproject.dto.profile.request.ProfileRequestDTO;
import edu.miu.cse.finalproject.dto.profile.response.ProfileResponseDTO;
import edu.miu.cse.finalproject.exception.profile.ProfileNotFoundException;
import edu.miu.cse.finalproject.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProfileControllerTest {

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    @Test
    void findAllProfile() {
        List<ProfileResponseDTO> mockProfiles = List.of(
                new ProfileResponseDTO(1L, "Software Engineer", "123456789", 5, null, List.of(), List.of()),
                new ProfileResponseDTO(2L, "Backend Developer", "987654321", 3, null, List.of(), List.of())
        );

        when(profileService.findAllProfiles()).thenReturn(mockProfiles);

        ResponseEntity<?> response = profileController.findAllProfile();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockProfiles, response.getBody());

        verify(profileService, times(1)).findAllProfiles();
    }

    @Test
    void createProfile() {
        ProfileRequestDTO profileRequestDTO = new ProfileRequestDTO("Software Engineer", "123456789", 5, null);
        ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO(1L, "Software Engineer", "123456789", 5, null, List.of(), List.of());

        when(profileService.addProfile(profileRequestDTO)).thenReturn(Optional.of(profileResponseDTO));

        ResponseEntity<ProfileResponseDTO> response = profileController.createProfile(profileRequestDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(profileResponseDTO, response.getBody());

        verify(profileService, times(1)).addProfile(profileRequestDTO);
    }

    @Test
    void findProfileById() throws ProfileNotFoundException {
        Long profileId = 1L;
        ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO(1L, "Software Engineer", "123456789", 5, null, List.of(), List.of());

        when(profileService.findProfileById(profileId)).thenReturn(Optional.of(profileResponseDTO));

        ResponseEntity<ProfileResponseDTO> response = profileController.findProfileById(profileId);
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(profileResponseDTO, response.getBody());

        verify(profileService, times(1)).findProfileById(profileId);
    }

    @Test
    void updateProfile() throws ProfileNotFoundException {
        Long profileId = 1L;
        ProfileRequestDTO updatedProfile = new ProfileRequestDTO("Updated Bio", "987654321", 6, null);
        ProfileResponseDTO updatedProfileResponse = new ProfileResponseDTO(1L, "Updated Bio", "987654321", 6, null, List.of(), List.of());

        when(profileService.updateProfile(profileId, updatedProfile)).thenReturn(Optional.of(updatedProfileResponse));

        ResponseEntity<ProfileResponseDTO> response = profileController.updateProfile(profileId, updatedProfile);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProfileResponse, response.getBody());

        verify(profileService, times(1)).updateProfile(profileId, updatedProfile);
    }


    @Test
    void deleteProfile() throws ProfileNotFoundException {
        Long profileId = 1L;

        doNothing().when(profileService).deleteProfile(profileId);

        ResponseEntity<Void> response = profileController.deleteProfile(profileId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(profileService, times(1)).deleteProfile(profileId);
    }

}
