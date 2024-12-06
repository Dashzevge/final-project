package edu.miu.cse.finalproject.service.impl;
import edu.miu.cse.finalproject.dto.certification.request.CertificationRequestDTO;
import edu.miu.cse.finalproject.dto.certification.response.CertificationResponseDTO;
import edu.miu.cse.finalproject.exception.certification.CertificationNotFoundException;
import edu.miu.cse.finalproject.exception.profile.ProfileNotFoundException;
import edu.miu.cse.finalproject.mapper.CertificationMapper;
import edu.miu.cse.finalproject.model.Certification;
import edu.miu.cse.finalproject.model.Profile;
import edu.miu.cse.finalproject.repository.CertificationRepository;
import edu.miu.cse.finalproject.repository.ProfileRepository;
import edu.miu.cse.finalproject.service.CertificationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService {
    private final CertificationRepository certificationRepository;
    private final ProfileRepository profileRepository;
    private final CertificationMapper certificationMapper;

    @Override
    public Optional<CertificationResponseDTO> addCertification(CertificationRequestDTO dto) throws ProfileNotFoundException {
        Certification certification = certificationMapper.toEntity(dto);
        Profile profile = profileRepository.findById(dto.getProfileId())
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found with ID: " + dto.getProfileId()));
        certification.getProfiles().add(profile);
        profile.getCertifications().add(certification);
        Certification savedCertification = certificationRepository.save(certification);
        return Optional.of(certificationMapper.toResponse(savedCertification));
    }

    @Override
    public Optional<CertificationResponseDTO> findCertificationById(Long id) throws CertificationNotFoundException {
        Certification booking = certificationRepository.findById(id)
                .orElseThrow(() -> new CertificationNotFoundException("Certification not found with ID: " + id));
        return Optional.of(certificationMapper.toResponse(booking));
    }

    @Override
    public List<CertificationResponseDTO> findAllCertifications() {
        return certificationRepository.findAll()
                .stream()
                .map(certificationMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<CertificationResponseDTO> updateCertification(Long id, CertificationRequestDTO dto) throws CertificationNotFoundException{
        Certification certification = certificationRepository.findById(id)
                .orElseThrow(() -> new CertificationNotFoundException("Certification not found with ID: " + id));
        certification.setName(dto.getName());
        certification.setAuthority(dto.getAuthority());
        certification.setExpiryDate(dto.getExpiryDate());
        certification.setIssueDate(dto.getIssueDate());
        Certification updatedCertification = certificationRepository.save(certification);
        return Optional.of(certificationMapper.toResponse(updatedCertification));
    }

    @Override
    public void deleteCertification(Long id) throws CertificationNotFoundException{
        if (!certificationRepository.existsById(id)) {
            throw new CertificationNotFoundException("Certification not found with ID: " + id);
        }
        certificationRepository.deleteById(id);
    }
}

