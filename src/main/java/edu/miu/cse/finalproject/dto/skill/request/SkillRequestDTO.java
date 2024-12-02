package edu.miu.cse.finalproject.dto.skill.request;

public record SkillRequestDTO(
        String name,
        String proficiencyLevel,
        Long userId
) {
}

