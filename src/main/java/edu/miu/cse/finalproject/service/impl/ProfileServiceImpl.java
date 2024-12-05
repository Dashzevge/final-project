package edu.miu.cse.finalproject.service.impl;

import edu.miu.cse.finalproject.dto.profile.request.ProfileRequestDTO;
import edu.miu.cse.finalproject.dto.profile.response.ProfileResponseDTO;
import edu.miu.cse.finalproject.exception.profile.ProfileNotFoundException;
import edu.miu.cse.finalproject.mapper.ProfileMapper;
import edu.miu.cse.finalproject.model.Profile;
import edu.miu.cse.finalproject.repository.ProfileRepository;
import edu.miu.cse.finalproject.service.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public Optional<ProfileResponseDTO> addProfile(ProfileRequestDTO dto) {
        Profile profile = profileMapper.toEntity(dto);
        Profile savedProfile = profileRepository.save(profile);
        return Optional.of(profileMapper.toResponse(savedProfile));
    }

    @Override
    public Optional<ProfileResponseDTO> findProfileById(Long id) throws ProfileNotFoundException {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with ID: " + id));
        return Optional.of(profileMapper.toResponse(profile));
    }

    @Override
    public List<ProfileResponseDTO> findAllProfiles() {
        return profileRepository.findAll()
                .stream()
                .map(profileMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<ProfileResponseDTO> updateProfile(Long id, ProfileRequestDTO dto) throws ProfileNotFoundException {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with ID: " + id));
        profile.setBio(dto.bio());
        profile.setPhoneNumber(dto.phoneNumber());
        profile.setExperienceYears(dto.experienceYears());
        Profile updatedProfile = profileRepository.save(profile);
        return Optional.of(profileMapper.toResponse(updatedProfile));
    }

    @Override
    public void deleteProfile(Long id) throws ProfileNotFoundException{
        if (!profileRepository.existsById(id)) {
            throw new ProfileNotFoundException("Profile not found with ID: " + id);
        }
        profileRepository.deleteById(id);
    }
}
