package edu.miu.cse.finalproject.dto.skill.response;

public record SkillResponseDTO(
        Long id,
        String name,
        String proficiencyLevel,
        Long userId
) {
}

