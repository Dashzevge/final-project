package edu.miu.cse.finalproject.dto.skill.response;

public record SkillResponseDTO(
        Long id,
        String name,
        String description,
        Long profileId
) {
}

