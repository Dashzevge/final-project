package edu.miu.cse.finalproject.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.miu.cse.finalproject.dto.certification.request.CertificationRequestDTO;
import edu.miu.cse.finalproject.dto.certification.response.CertificationResponseDTO;
import edu.miu.cse.finalproject.exception.certification.CertificationNotFoundException;
import edu.miu.cse.finalproject.exception.profile.ProfileNotFoundException;
import edu.miu.cse.finalproject.service.CertificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CertificationControllerTest {

    @Mock
    private CertificationService certificationService;

    @InjectMocks
    private CertificationController certificationController;

    @Test
    void findAllCertification() {
        List<CertificationResponseDTO> mockCertifications = List.of(
                new CertificationResponseDTO(1L, "Java Developer", "Oracle", LocalDate.of(2021, 5, 10), LocalDate.of(2023, 5, 10), 1L),
                new CertificationResponseDTO(2L, "AWS Certified", "Amazon", LocalDate.of(2020, 6, 15), LocalDate.of(2023, 6, 15), 2L)
        );

        when(certificationService.findAllCertifications()).thenReturn(mockCertifications);

        ResponseEntity<?> response = certificationController.findAllCertification();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCertifications, response.getBody());

        verify(certificationService, times(1)).findAllCertifications();
    }

    @Test
    void createCertification() throws ProfileNotFoundException {
        CertificationRequestDTO certificationRequestDTO = new CertificationRequestDTO("AWS Certified", "Amazon", LocalDate.of(2023, 6, 15), LocalDate.of(2026, 6, 15), 1L);
        CertificationResponseDTO certificationResponseDTO = new CertificationResponseDTO(1L, "AWS Certified", "Amazon", LocalDate.of(2023, 6, 15), LocalDate.of(2026, 6, 15), 1L);

        when(certificationService.addCertification(certificationRequestDTO)).thenReturn(Optional.of(certificationResponseDTO));

        ResponseEntity<CertificationResponseDTO> response = certificationController.createCertification(certificationRequestDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(certificationResponseDTO, response.getBody());

        verify(certificationService, times(1)).addCertification(certificationRequestDTO);
    }

    @Test
    void findCertificationById() throws CertificationNotFoundException {
        Long certificationId = 1L;
        CertificationResponseDTO certificationResponseDTO = new CertificationResponseDTO(1L, "Java Developer", "Oracle", LocalDate.of(2021, 5, 10), LocalDate.of(2023, 5, 10), 1L);

        when(certificationService.findCertificationById(certificationId)).thenReturn(Optional.of(certificationResponseDTO));

        ResponseEntity<CertificationResponseDTO> response = certificationController.findCertificationById(certificationId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(certificationResponseDTO, response.getBody());

        verify(certificationService, times(1)).findCertificationById(certificationId);
    }

    @Test
    void updateCertification() throws CertificationNotFoundException {
        Long certificationId = 1L;
        CertificationRequestDTO updatedCertification = new CertificationRequestDTO("AWS Certified", "Amazon", LocalDate.of(2023, 6, 15), LocalDate.of(2026, 6, 15), 1L);
        CertificationResponseDTO updatedCertificationResponse = new CertificationResponseDTO(1L, "AWS Certified", "Amazon", LocalDate.of(2023, 6, 15), LocalDate.of(2026, 6, 15), 1L);

        when(certificationService.updateCertification(certificationId, updatedCertification)).thenReturn(Optional.of(updatedCertificationResponse));

        ResponseEntity<CertificationResponseDTO> response = certificationController.updateCertification(certificationId, updatedCertification);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCertificationResponse, response.getBody());

        verify(certificationService, times(1)).updateCertification(certificationId, updatedCertification);
    }

    @Test
    void deleteCertification() throws CertificationNotFoundException {
        Long certificationId = 1L;

        doNothing().when(certificationService).deleteCertification(certificationId);

        ResponseEntity<Void> response = certificationController.deleteCertification(certificationId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(certificationService, times(1)).deleteCertification(certificationId);
    }
}
