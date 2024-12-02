package edu.miu.cse.finalproject.service.impl;
import edu.miu.cse.finalproject.dto.certification.request.CertificationRequestDTO;
import edu.miu.cse.finalproject.dto.certification.response.CertificationResponseDTO;
import edu.miu.cse.finalproject.mapper.CertificationMapper;
import edu.miu.cse.finalproject.model.Certification;
import edu.miu.cse.finalproject.repository.CertificationRepository;
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
    private final CertificationMapper certificationMapper;

    @Override
    public Optional<CertificationResponseDTO> addCertification(CertificationRequestDTO dto) {
        Certification certification = certificationMapper.toEntity(dto);
        Certification savedCertification = certificationRepository.save(certification);
        return Optional.of(certificationMapper.toResponse(savedCertification));
    }

    @Override
    public Optional<CertificationResponseDTO> findCertificationById(Long id) {
        Certification booking = certificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certification not found with ID: " + id));
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
    public Optional<CertificationResponseDTO> updateCertification(Long id, CertificationRequestDTO dto) {
        Certification certification = certificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certification not found with ID: " + id));
        certification.setName(dto.name());
        certification.setAuthority(dto.authority());
        certification.setExpiryDate(dto.expiryDate());
        certification.setIssueDate(dto.issueDate());
        Certification updatedCertification = certificationRepository.save(certification);
        return Optional.of(certificationMapper.toResponse(updatedCertification));
    }

    @Override
    public void deleteCertification(Long id) {
        if (!certificationRepository.existsById(id)) {
            throw new EntityNotFoundException("Certification not found with ID: " + id);
        }
        certificationRepository.deleteById(id);
    }
}

