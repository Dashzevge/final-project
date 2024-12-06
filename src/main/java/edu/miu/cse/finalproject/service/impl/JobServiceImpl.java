package edu.miu.cse.finalproject.service.impl;

import edu.miu.cse.finalproject.dto.job.request.JobRequestDTO;
import edu.miu.cse.finalproject.dto.job.response.JobResponseDTO;
import edu.miu.cse.finalproject.exception.job.JobNotFoundException;
import edu.miu.cse.finalproject.exception.user.UserNotFoundException;
import edu.miu.cse.finalproject.mapper.JobMapper;
import edu.miu.cse.finalproject.model.Job;
import edu.miu.cse.finalproject.model.User;
import edu.miu.cse.finalproject.repository.JobRepository;
import edu.miu.cse.finalproject.repository.UserRepository;
import edu.miu.cse.finalproject.service.JobService;
import edu.miu.cse.finalproject.util.JobStatus;
import edu.miu.cse.finalproject.util.Role;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final JobMapper jobMapper;

    @Override
    public Optional<JobResponseDTO> addJob(JobRequestDTO dto) throws UserNotFoundException{
        User client = findUserById(dto.getClientId());
        Job job = jobMapper.toEntity(dto);
        job.setClient(client);
        Job savedJob = jobRepository.save(job);
        return Optional.of(jobMapper.toResponse(savedJob));
    }

    @Override
    public Optional<JobResponseDTO> findJobById(Long id) throws JobNotFoundException {
        Job booking = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + id));
        return Optional.of(jobMapper.toResponse(booking));
    }

    @Override
    public List<JobResponseDTO> findAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(jobMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<JobResponseDTO> updateJob(Long id, JobRequestDTO dto) throws JobNotFoundException, UserNotFoundException{
        Job job = findJobEntityById(id);
        User client = findUserById(dto.getClientId());
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setPrice(dto.getPrice());
        job.setStatus(dto.getStatus());
        job.setCategory(dto.getCategory());
        job.setClient(client);
        Job updatedJob = jobRepository.save(job);
        return Optional.of(jobMapper.toResponse(updatedJob));
    }

    @Override
    @Transactional
    public Optional<JobResponseDTO> updateJobStatus(Long jobId, JobStatus status) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + jobId));
        job.setStatus(status);
        Job savedJob = jobRepository.save(job);
        return Optional.of(jobMapper.toResponse(savedJob));
    }

    @Override
    public void deleteJob(Long id) throws JobNotFoundException{
        if (!jobRepository.existsById(id)) {
            throw new JobNotFoundException("Job not found with ID: " + id);
        }
        jobRepository.deleteById(id);
    }

    private Job findJobEntityById(Long id) throws JobNotFoundException {
        return jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + id));
    }


    private User findUserById(Long clientId) throws UserNotFoundException {
        return userRepository.findById(clientId)
                .orElseThrow(() -> new UserNotFoundException("Client not found with ID: " + clientId));
    }

    @Override
    public List<JobResponseDTO> getJobsForProfessional(Long userId) throws UserNotFoundException {
        // Fetch the user to ensure they are a professional
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        // Ensure that the user has the role of PROFESSIONAL
        if (!user.getRole().equals(Role.PROFESSIONAL)) {
            throw new AccessDeniedException("Only professionals can view jobs created by clients.");
        }

        // Fetch jobs created by clients (assuming jobs have a 'client' field)
        return jobRepository.findAllByClient(user) // Assuming jobRepository has this method
                .stream()
                .map(jobMapper::toResponse)
                .collect(Collectors.toList());
    }

}
