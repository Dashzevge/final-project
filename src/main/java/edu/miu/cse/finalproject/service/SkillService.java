package edu.miu.cse.finalproject.service;

import edu.miu.cse.finalproject.dto.skill.request.SkillRequestDTO;
import edu.miu.cse.finalproject.dto.skill.response.SkillResponseDTO;
import edu.miu.cse.finalproject.exception.skill.SkillNotFoundException;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    Optional<SkillResponseDTO> addSkill(SkillRequestDTO dto);
    Optional<SkillResponseDTO> findSkillById(Long id) throws SkillNotFoundException;
    List<SkillResponseDTO> findAllSkills();
    Optional<SkillResponseDTO> updateSkill(Long id, SkillRequestDTO dto) throws SkillNotFoundException;
    void deleteSkill(Long id) throws SkillNotFoundException;
}
