package edu.miu.cse.finalproject.controller;

import edu.miu.cse.finalproject.dto.skill.request.SkillRequestDTO;
import edu.miu.cse.finalproject.dto.skill.response.SkillResponseDTO;
import edu.miu.cse.finalproject.exception.skill.SkillNotFoundException;
import edu.miu.cse.finalproject.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/skills")
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;

    @GetMapping
    public ResponseEntity<?> findAllSkill() {
        List<SkillResponseDTO> allSkill = skillService.findAllSkills();
        return ResponseEntity.status(HttpStatus.OK).body(allSkill);
    }

    @PostMapping
    public ResponseEntity<SkillResponseDTO> createSkill(@RequestBody SkillRequestDTO SkillRequestDTO) {
        SkillResponseDTO savedSkill = skillService.addSkill(SkillRequestDTO).get();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSkill);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillResponseDTO> findSkillById(@PathVariable Long id) throws SkillNotFoundException {
        return skillService.findSkillById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillResponseDTO> updateSkill(@PathVariable Long id, @RequestBody SkillRequestDTO updatedSkill) throws SkillNotFoundException {
        SkillResponseDTO newSkill = skillService.updateSkill(id, updatedSkill).get();
        return new ResponseEntity<>(newSkill, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) throws SkillNotFoundException{
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
}
