package edu.miu.cse.finalproject.controller;

import edu.miu.cse.finalproject.dto.booking.response.BookingResponseDTO;
import edu.miu.cse.finalproject.dto.job.request.JobRequestDTO;
import edu.miu.cse.finalproject.dto.job.response.JobResponseDTO;
import edu.miu.cse.finalproject.exception.job.JobNotFoundException;
import edu.miu.cse.finalproject.exception.user.UserNotFoundException;
import edu.miu.cse.finalproject.service.JobService;
import edu.miu.cse.finalproject.util.BookingStatus;
import edu.miu.cse.finalproject.util.JobStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping
    public ResponseEntity<?> findAllJob() {
        List<JobResponseDTO> allJob = jobService.findAllJobs();
        return ResponseEntity.status(HttpStatus.OK).body(allJob);
    }

    @PostMapping
    public ResponseEntity<JobResponseDTO> createJob(@RequestBody JobRequestDTO JobRequestDTO) throws UserNotFoundException {
        JobResponseDTO savedJob = jobService.addJob(JobRequestDTO).get();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedJob);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDTO> findJobById(@PathVariable Long id) throws JobNotFoundException {
        return jobService.findJobById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponseDTO> updateJob(@PathVariable Long id, @RequestBody JobRequestDTO updatedJob) throws JobNotFoundException, UserNotFoundException {
        JobResponseDTO newJob = jobService.updateJob(id, updatedJob).get();
        return new ResponseEntity<>(newJob, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) throws JobNotFoundException {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{jobId}/status")
    public ResponseEntity<JobResponseDTO> updateBookingStatus(@PathVariable Long jobId, @RequestParam JobStatus status) throws JobNotFoundException {
        JobResponseDTO updatedJob = jobService.updateJobStatus(jobId, status).get();
        return ResponseEntity.status(HttpStatus.OK).body(updatedJob);
    }

    @GetMapping("/status")
    public ResponseEntity<?> getJobsForProfessional(@PathVariable Long userId) throws UserNotFoundException {
        List<JobResponseDTO> jobs = jobService.getJobsForProfessional(userId);
        return ResponseEntity.status(HttpStatus.OK).body(jobs);
    }
}
