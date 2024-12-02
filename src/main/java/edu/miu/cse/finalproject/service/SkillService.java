package edu.miu.cse.finalproject.service;

import edu.miu.cse.finalproject.dto.skill.request.SkillRequestDTO;
import edu.miu.cse.finalproject.dto.skill.response.SkillResponseDTO;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    Optional<SkillResponseDTO> addSkill(SkillRequestDTO dto);
    Optional<SkillResponseDTO> findSkillById(Long id);
    List<SkillResponseDTO> findAllSkills();
    Optional<SkillResponseDTO> updateSkill(Long id, SkillRequestDTO dto);
    void deleteSkill(Long id);
}
