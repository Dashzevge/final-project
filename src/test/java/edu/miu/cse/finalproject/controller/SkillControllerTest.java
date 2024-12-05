package edu.miu.cse.finalproject.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.miu.cse.finalproject.dto.skill.request.SkillRequestDTO;
import edu.miu.cse.finalproject.dto.skill.response.SkillResponseDTO;
import edu.miu.cse.finalproject.exception.skill.SkillNotFoundException;
import edu.miu.cse.finalproject.service.SkillService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SkillControllerTest {

    @Mock
    private SkillService skillService;

    @InjectMocks
    private SkillController skillController;

    @Test
    void findAllSkill() {
        List<SkillResponseDTO> mockSkills = List.of(
                new SkillResponseDTO(1L, "Java", "Programming language", 101L),
                new SkillResponseDTO(2L, "Spring Boot", "Java Framework", 102L)
        );

        when(skillService.findAllSkills()).thenReturn(mockSkills);

        ResponseEntity<?> response = skillController.findAllSkill();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockSkills, response.getBody());

        verify(skillService, times(1)).findAllSkills();
    }

    @Test
    void createSkill() {
        SkillRequestDTO skillRequestDTO = new SkillRequestDTO("Java", "Programming language", 101L);
        SkillResponseDTO skillResponseDTO = new SkillResponseDTO(1L, "Java", "Programming language", 101L);

        when(skillService.addSkill(skillRequestDTO)).thenReturn(Optional.of(skillResponseDTO));

        ResponseEntity<SkillResponseDTO> response = skillController.createSkill(skillRequestDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(skillResponseDTO, response.getBody());

        verify(skillService, times(1)).addSkill(skillRequestDTO);
    }

    @Test
    void findSkillById() throws SkillNotFoundException {
        Long skillId = 1L;
        SkillResponseDTO skillResponseDTO = new SkillResponseDTO(1L, "Java", "Programming language", 101L);

        when(skillService.findSkillById(skillId)).thenReturn(Optional.of(skillResponseDTO));

        ResponseEntity<SkillResponseDTO> response = skillController.findSkillById(skillId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(skillResponseDTO, response.getBody());

        verify(skillService, times(1)).findSkillById(skillId);
    }


    @Test
    void updateSkill() throws SkillNotFoundException {
        Long skillId = 1L;
        SkillRequestDTO updatedSkill = new SkillRequestDTO("Spring Boot", "Java Framework", 102L);
        SkillResponseDTO updatedSkillResponse = new SkillResponseDTO(1L, "Spring Boot", "Java Framework", 102L);

        when(skillService.updateSkill(skillId, updatedSkill)).thenReturn(Optional.of(updatedSkillResponse));

        ResponseEntity<SkillResponseDTO> response = skillController.updateSkill(skillId, updatedSkill);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedSkillResponse, response.getBody());

        verify(skillService, times(1)).updateSkill(skillId, updatedSkill);
    }



    @Test
    void deleteSkill() throws SkillNotFoundException {
        Long skillId = 1L;

        doNothing().when(skillService).deleteSkill(skillId);

        ResponseEntity<Void> response = skillController.deleteSkill(skillId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(skillService, times(1)).deleteSkill(skillId);
    }


}
