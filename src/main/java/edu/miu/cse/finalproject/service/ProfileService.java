package edu.miu.cse.finalproject.service;

import edu.miu.cse.finalproject.dto.profile.request.ProfileRequestDTO;
import edu.miu.cse.finalproject.dto.profile.response.ProfileResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    Optional<ProfileResponseDTO> addProfile(ProfileRequestDTO dto);
    Optional<ProfileResponseDTO> findProfileById(Long id);
    List<ProfileResponseDTO> findAllProfiles();
    Optional<ProfileResponseDTO> updateProfile(Long id, ProfileRequestDTO dto);
    void deleteProfile(Long id);
}
