package edu.miu.cse.finalproject.controller;

import edu.miu.cse.finalproject.dto.profile.request.ProfileRequestDTO;
import edu.miu.cse.finalproject.dto.profile.response.ProfileResponseDTO;
import edu.miu.cse.finalproject.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<?> findAllProfile() {
        List<ProfileResponseDTO> allProfile = profileService.findAllProfiles();
        return ResponseEntity.status(HttpStatus.OK).body(allProfile);
    }

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> createProfile(@RequestBody ProfileRequestDTO ProfileRequestDTO) {
        ProfileResponseDTO savedProfile = profileService.addProfile(ProfileRequestDTO).get();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> findProfileById(@PathVariable Long id) {
        return profileService.findProfileById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> updateProfile(@PathVariable Long id, @RequestBody ProfileRequestDTO updatedProfile) {
        ProfileResponseDTO newProfile = profileService.updateProfile(id, updatedProfile).get();
        return new ResponseEntity<>(newProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
