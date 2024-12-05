package edu.miu.cse.finalproject.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.miu.cse.finalproject.dto.job.request.JobRequestDTO;
import edu.miu.cse.finalproject.dto.job.response.JobResponseDTO;
import edu.miu.cse.finalproject.exception.job.JobNotFoundException;
import edu.miu.cse.finalproject.exception.user.UserNotFoundException;
import edu.miu.cse.finalproject.service.JobService;
import edu.miu.cse.finalproject.util.JobStatus;
import edu.miu.cse.finalproject.util.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class JobControllerTest {

    @Mock
    private JobService jobService;

    @InjectMocks
    private JobController jobController;

    @Test
    void findAllJob() {
        List<JobResponseDTO> mockJobs = List.of(
                new JobResponseDTO(1L, "Plumbing", "Fix the sink", 100.0, JobStatus.OPEN, Category.CLEANING, 10L),
                new JobResponseDTO(2L, "Electrical Work", "Install new lighting", 200.0, JobStatus.COMPLETED, Category.ELECTRICIAN, 12L)
        );

        when(jobService.findAllJobs()).thenReturn(mockJobs);

        ResponseEntity<?> response = jobController.findAllJob();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockJobs, response.getBody());

        verify(jobService, times(1)).findAllJobs();
    }

    @Test
    void createJob() throws UserNotFoundException {
        JobRequestDTO jobRequestDTO = new JobRequestDTO("Painting", "Paint a room", 150.0, JobStatus.OPEN, Category.MECHANIC, 10L);
        JobResponseDTO jobResponseDTO = new JobResponseDTO(1L, "Painting", "Paint a room", 150.0, JobStatus.OPEN, Category.MECHANIC, 10L);

        when(jobService.addJob(jobRequestDTO)).thenReturn(Optional.of(jobResponseDTO));

        ResponseEntity<JobResponseDTO> response = jobController.createJob(jobRequestDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(jobResponseDTO, response.getBody());

        verify(jobService, times(1)).addJob(jobRequestDTO);
    }

    @Test
    void findJobById() throws JobNotFoundException {
        Long jobId = 1L;
        JobResponseDTO jobResponseDTO = new JobResponseDTO(1L, "Plumbing", "Fix the sink", 100.0, JobStatus.OPEN, Category.PAINTER, 10L);

        when(jobService.findJobById(jobId)).thenReturn(Optional.of(jobResponseDTO));

        ResponseEntity<JobResponseDTO> response = jobController.findJobById(jobId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobResponseDTO, response.getBody());

        verify(jobService, times(1)).findJobById(jobId);
    }

    @Test
    void updateJob() throws JobNotFoundException, UserNotFoundException {
        Long jobId = 1L;
        JobRequestDTO updatedJob = new JobRequestDTO("Electrical Work", "Install new wiring", 200.0, JobStatus.OPEN, Category.ELECTRICIAN, 12L);
        JobResponseDTO updatedJobResponse = new JobResponseDTO(1L, "Electrical Work", "Install new wiring", 200.0, JobStatus.OPEN, Category.ELECTRICIAN, 12L);

        when(jobService.updateJob(jobId, updatedJob)).thenReturn(Optional.of(updatedJobResponse));

        ResponseEntity<JobResponseDTO> response = jobController.updateJob(jobId, updatedJob);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedJobResponse, response.getBody());

        verify(jobService, times(1)).updateJob(jobId, updatedJob);
    }

    @Test
    void deleteJob() throws JobNotFoundException {
        Long jobId = 1L;

        doNothing().when(jobService).deleteJob(jobId);

        ResponseEntity<Void> response = jobController.deleteJob(jobId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(jobService, times(1)).deleteJob(jobId);
    }

    @Test
    void updateBookingStatus() throws JobNotFoundException {
        Long jobId = 1L;
        JobStatus newStatus = JobStatus.IN_PROGRESS;
        JobResponseDTO updatedJobResponse = new JobResponseDTO(1L, "Plumbing", "Fix the sink", 100.0, newStatus, Category.CARPENTER, 10L);

        when(jobService.updateJobStatus(jobId, newStatus)).thenReturn(Optional.of(updatedJobResponse));

        ResponseEntity<JobResponseDTO> response = jobController.updateBookingStatus(jobId, newStatus);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedJobResponse, response.getBody());

        verify(jobService, times(1)).updateJobStatus(jobId, newStatus);
    }

    @Test
    void getJobsForProfessional() throws UserNotFoundException {
        Long userId = 10L;
        List<JobResponseDTO> mockJobs = List.of(
                new JobResponseDTO(1L, "Plumbing", "Fix the sink", 100.0, JobStatus.OPEN, Category.CLEANING, userId),
                new JobResponseDTO(2L, "Painting", "Paint a room", 150.0, JobStatus.COMPLETED, Category.CLEANING, userId)
        );

        when(jobService.getJobsForProfessional(userId)).thenReturn(mockJobs);

        ResponseEntity<?> response = jobController.getJobsForProfessional(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockJobs, response.getBody());

        verify(jobService, times(1)).getJobsForProfessional(userId);
    }
}
