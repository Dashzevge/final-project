package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.job.request.JobRequestDTO;
import edu.miu.cse.finalproject.dto.job.response.JobResponseDTO;
import edu.miu.cse.finalproject.model.Job;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobMapper {

    // Mapping JobRequestDTO to Job entity
    public Job toEntity(JobRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Job job = new Job();
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setPrice(dto.getPrice());
        job.setStatus(dto.getStatus());
        job.setCategory(dto.getCategory());

        return job;
    }

    // Mapping Job entity to JobResponseDTO
    public JobResponseDTO toResponse(Job entity) {
        if (entity == null) {
            return null;
        }

        JobResponseDTO response = new JobResponseDTO();
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setPrice(entity.getPrice());
        response.setStatus(entity.getStatus());
        response.setCategory(entity.getCategory());

        // Map client.id to clientId in response DTO
        if (entity.getClient() != null) {
            response.setClientId(entity.getClient().getId());
        } else {
            response.setClientId(null);
        }

        return response;
    }

    // Mapping a List of Job entities to a List of JobResponseDTOs
    public List<JobResponseDTO> toResponseList(List<Job> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toResponse)  // Convert each Job entity to JobResponseDTO
                .collect(Collectors.toList());
    }
}
