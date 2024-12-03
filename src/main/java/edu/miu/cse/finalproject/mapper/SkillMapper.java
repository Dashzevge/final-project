package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.skill.request.SkillRequestDTO;
import edu.miu.cse.finalproject.dto.skill.response.SkillResponseDTO;
import edu.miu.cse.finalproject.model.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Skill toEntity(SkillRequestDTO dto);
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    SkillResponseDTO toResponse(Skill entity);
}
