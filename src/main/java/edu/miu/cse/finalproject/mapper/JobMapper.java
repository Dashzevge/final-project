package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.job.request.JobRequestDTO;
import edu.miu.cse.finalproject.dto.job.response.JobResponseDTO;
import edu.miu.cse.finalproject.model.Job;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {
    Job toEntity(JobRequestDTO dto);
    JobResponseDTO toResponse(Job entity);
}
