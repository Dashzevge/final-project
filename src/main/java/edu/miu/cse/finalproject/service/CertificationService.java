package edu.miu.cse.finalproject.service;


import edu.miu.cse.finalproject.dto.certification.request.CertificationRequestDTO;
import edu.miu.cse.finalproject.dto.certification.response.CertificationResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CertificationService {
    Optional<CertificationResponseDTO> addCertification(CertificationRequestDTO dto);
    Optional<CertificationResponseDTO> findCertificationById(Long id);
    List<CertificationResponseDTO> findAllCertifications();
    Optional<CertificationResponseDTO> updateCertification(Long id, CertificationRequestDTO dto);
    void deleteCertification(Long id);
}
