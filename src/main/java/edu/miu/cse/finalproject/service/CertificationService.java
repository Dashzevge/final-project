package edu.miu.cse.finalproject.service;


import edu.miu.cse.finalproject.dto.certification.request.CertificationRequestDTO;
import edu.miu.cse.finalproject.dto.certification.response.CertificationResponseDTO;
import edu.miu.cse.finalproject.exception.certification.CertificationNotFoundException;
import edu.miu.cse.finalproject.exception.profile.ProfileNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CertificationService {
    Optional<CertificationResponseDTO> addCertification(CertificationRequestDTO dto) throws ProfileNotFoundException;
    Optional<CertificationResponseDTO> findCertificationById(Long id) throws CertificationNotFoundException;
    List<CertificationResponseDTO> findAllCertifications();
    Optional<CertificationResponseDTO> updateCertification(Long id, CertificationRequestDTO dto) throws CertificationNotFoundException;
    void deleteCertification(Long id) throws CertificationNotFoundException;
}
