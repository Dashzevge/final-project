package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.certification.request.CertificationRequestDTO;
import edu.miu.cse.finalproject.dto.certification.response.CertificationResponseDTO;
import edu.miu.cse.finalproject.model.Certification;
import org.springframework.stereotype.Component;

@Component
public class CertificationMapper {

    // Mapping CertificationRequestDTO to Certification
    public Certification toEntity(CertificationRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Certification certification = new Certification();
        certification.setName(dto.getName());
        certification.setAuthority(dto.getAuthority());
        certification.setIssueDate(dto.getIssueDate());
        certification.setExpiryDate(dto.getExpiryDate());

        return certification;
    }

    // Mapping Certification entity to CertificationResponseDTO
    public CertificationResponseDTO toResponse(Certification entity) {
        if (entity == null) {
            return null;
        }

        CertificationResponseDTO response = new CertificationResponseDTO();
        response.setName(entity.getName());
        response.setAuthority(entity.getAuthority());
        response.setIssueDate(entity.getIssueDate());
        response.setExpiryDate(entity.getExpiryDate());

        // Map profileId to the first profile's id, if available
        if (entity.getProfiles() != null && !entity.getProfiles().isEmpty()) {
            response.setProfileId(entity.getProfiles().get(0).getId());
        } else {
            response.setProfileId(null);
        }

        return response;
    }
}
