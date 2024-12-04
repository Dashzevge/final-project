package edu.miu.cse.finalproject.dto.profile.request;

import edu.miu.cse.finalproject.dto.address.request.AddressRequestDTO;
import edu.miu.cse.finalproject.dto.certification.request.CertificationRequestDTO;
import edu.miu.cse.finalproject.dto.skill.request.SkillRequestDTO;
import edu.miu.cse.finalproject.model.Certification;
import edu.miu.cse.finalproject.model.Skill;

import java.util.List;

public record ProfileRequestDTO(
        String bio,
        String phoneNumber,
        int experienceYears,
        AddressRequestDTO address
) {
}