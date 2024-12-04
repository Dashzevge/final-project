package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.job.request.JobRequestDTO;
import edu.miu.cse.finalproject.dto.job.response.JobResponseDTO;
import edu.miu.cse.finalproject.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobMapper {
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "category", target = "category")
    Job toEntity(JobRequestDTO dto);

    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "client.id", target = "clientId")
    JobResponseDTO toResponse(Job entity);

    List<JobResponseDTO> toResponseList(List<Job> entities);
}
