package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.skill.request.SkillRequestDTO;
import edu.miu.cse.finalproject.dto.skill.response.SkillResponseDTO;
import edu.miu.cse.finalproject.model.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    @Mapping(target = "id", ignore = true) // `id` is auto-generated
    @Mapping(target = "profiles", ignore = true) // Ignore `profiles` since it is not in DTO
    Skill toEntity(SkillRequestDTO dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(expression = "java(skill.getProfiles() != null && !skill.getProfiles().isEmpty() ? skill.getProfiles().get(0).getId() : null)", target = "profileId")
    SkillResponseDTO toResponse(Skill skill);
}
