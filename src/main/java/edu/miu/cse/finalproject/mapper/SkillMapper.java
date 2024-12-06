package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.skill.request.SkillRequestDTO;
import edu.miu.cse.finalproject.dto.skill.response.SkillResponseDTO;
import edu.miu.cse.finalproject.model.Skill;
import org.springframework.stereotype.Component;

@Component
public class SkillMapper {

    // Mapping SkillRequestDTO to Skill entity
    public Skill toEntity(SkillRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Skill skill = new Skill();
        skill.setName(dto.getName());
        skill.setDescription(dto.getDescription());
        // The 'id' is ignored and profiles are not set in the entity as it is not required in the DTO
        return skill;
    }

    // Mapping Skill entity to SkillResponseDTO
    public SkillResponseDTO toResponse(Skill skill) {
        if (skill == null) {
            return null;
        }

        SkillResponseDTO response = new SkillResponseDTO();
        response.setId(skill.getId());
        response.setName(skill.getName());
        response.setDescription(skill.getDescription());
        // If profiles are not null and not empty, set the first profile's ID as profileId
        response.setProfileId(skill.getProfiles() != null && !skill.getProfiles().isEmpty()
                ? skill.getProfiles().get(0).getId()
                : null);

        return response;
    }
}
