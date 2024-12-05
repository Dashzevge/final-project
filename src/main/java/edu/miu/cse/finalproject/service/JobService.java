package edu.miu.cse.finalproject.service;


import edu.miu.cse.finalproject.dto.job.request.JobRequestDTO;
import edu.miu.cse.finalproject.dto.job.response.JobResponseDTO;
import edu.miu.cse.finalproject.exception.job.JobNotFoundException;
import edu.miu.cse.finalproject.exception.user.UserNotFoundException;
import edu.miu.cse.finalproject.util.JobStatus;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Optional<JobResponseDTO> addJob(JobRequestDTO dto) throws UserNotFoundException;
    Optional<JobResponseDTO> findJobById(Long id) throws JobNotFoundException;
    List<JobResponseDTO> findAllJobs();
    Optional<JobResponseDTO> updateJob(Long id, JobRequestDTO dto) throws JobNotFoundException, UserNotFoundException;
    Optional<JobResponseDTO> updateJobStatus(Long jobId, JobStatus status) throws JobNotFoundException;
    void deleteJob(Long id) throws JobNotFoundException;
    List<JobResponseDTO> getJobsForProfessional(Long userId) throws UserNotFoundException;
}
