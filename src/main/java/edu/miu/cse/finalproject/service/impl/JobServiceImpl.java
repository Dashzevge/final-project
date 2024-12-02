package edu.miu.cse.finalproject.service.impl;

import edu.miu.cse.finalproject.dto.job.request.JobRequestDTO;
import edu.miu.cse.finalproject.dto.job.response.JobResponseDTO;
import edu.miu.cse.finalproject.mapper.JobMapper;
import edu.miu.cse.finalproject.model.Job;
import edu.miu.cse.finalproject.repository.JobRepository;
import edu.miu.cse.finalproject.service.JobService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    @Override
    public Optional<JobResponseDTO> addJob(JobRequestDTO dto) {
        Job job = jobMapper.toEntity(dto);
        Job savedJob = jobRepository.save(job);
        return Optional.of(jobMapper.toResponse(savedJob));
    }

    @Override
    public Optional<JobResponseDTO> findJobById(Long id) {
        Job booking = jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found with ID: " + id));
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
    public Optional<JobResponseDTO> updateJob(Long id, JobRequestDTO dto) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job not found with ID: " + id));
        job.setTitle(dto.title());
        job.setDescription(dto.description());
        job.setPrice(dto.price());
        job.setStatus(dto.status());
        job.setCategory(dto.category());
        Job updatedJob = jobRepository.save(job);
        return Optional.of(jobMapper.toResponse(updatedJob));
    }

    @Override
    public void deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new EntityNotFoundException("Job not found with ID: " + id);
        }
        jobRepository.deleteById(id);
    }
    
}
