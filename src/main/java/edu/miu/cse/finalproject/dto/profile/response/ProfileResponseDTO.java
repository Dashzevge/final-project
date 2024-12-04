package edu.miu.cse.finalproject.dto.profile.response;
import edu.miu.cse.finalproject.dto.address.response.AddressResponseDTO;
import edu.miu.cse.finalproject.dto.certification.response.CertificationResponseDTO;
import edu.miu.cse.finalproject.dto.skill.response.SkillResponseDTO;

import java.util.List;

public record ProfileResponseDTO(
        Long id,
        String bio,
        String phoneNumber,
        int experienceYears,
        AddressResponseDTO address,
        List<SkillResponseDTO> skills,
        List<CertificationResponseDTO> certifications
) {
}

