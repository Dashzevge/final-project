package edu.miu.cse.finalproject.service.impl;


import edu.miu.cse.finalproject.dto.skill.request.SkillRequestDTO;
import edu.miu.cse.finalproject.dto.skill.response.SkillResponseDTO;
import edu.miu.cse.finalproject.mapper.SkillMapper;
import edu.miu.cse.finalproject.model.Skill;
import edu.miu.cse.finalproject.repository.SkillRepository;
import edu.miu.cse.finalproject.service.SkillService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    @Override
    public Optional<SkillResponseDTO> addSkill(SkillRequestDTO dto) {
        Skill skill = skillMapper.toEntity(dto);
        Skill savedSkill = skillRepository.save(skill);
        return Optional.of(skillMapper.toResponse(savedSkill));
    }

    @Override
    public Optional<SkillResponseDTO> findSkillById(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found with ID: " + id));
        return Optional.of(skillMapper.toResponse(skill));
    }

    @Override
    public List<SkillResponseDTO> findAllSkills() {
        return skillRepository.findAll()
                .stream()
                .map(skillMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<SkillResponseDTO> updateSkill(Long id, SkillRequestDTO dto) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found with ID: " + id));
        skill.setName(dto.name());
        skill.setDescription(dto.description());
        Skill updatedSkill = skillRepository.save(skill);
        return Optional.of(skillMapper.toResponse(updatedSkill));
    }

    @Override
    public void deleteSkill(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new EntityNotFoundException("Skill not found with ID: " + id);
        }
        skillRepository.deleteById(id);
    }
}
