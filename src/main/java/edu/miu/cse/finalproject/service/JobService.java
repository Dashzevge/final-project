package edu.miu.cse.finalproject.service;


import edu.miu.cse.finalproject.dto.job.request.JobRequestDTO;
import edu.miu.cse.finalproject.dto.job.response.JobResponseDTO;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Optional<JobResponseDTO> addJob(JobRequestDTO dto);
    Optional<JobResponseDTO> findJobById(Long id);
    List<JobResponseDTO> findAllJobs();
    Optional<JobResponseDTO> updateJob(Long id, JobRequestDTO dto);
    void deleteJob(Long id);
}
