package edu.miu.cse.finalproject.controller;


import edu.miu.cse.finalproject.dto.certification.request.CertificationRequestDTO;
import edu.miu.cse.finalproject.dto.certification.response.CertificationResponseDTO;
import edu.miu.cse.finalproject.exception.certification.CertificationNotFoundException;
import edu.miu.cse.finalproject.exception.profile.ProfileNotFoundException;
import edu.miu.cse.finalproject.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/certifications")
@RequiredArgsConstructor
public class CertificationController {
    private final CertificationService certificationService;

    @GetMapping
    public ResponseEntity<?> findAllCertification() {
        List<CertificationResponseDTO> allCertification = certificationService.findAllCertifications();
        return ResponseEntity.status(HttpStatus.OK).body(allCertification);
    }

    @PostMapping
    public ResponseEntity<CertificationResponseDTO> createCertification(@RequestBody CertificationRequestDTO CertificationRequestDTO) throws ProfileNotFoundException {
        CertificationResponseDTO savedCertification = certificationService.addCertification(CertificationRequestDTO).get();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCertification);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificationResponseDTO> findCertificationById(@PathVariable Long id) throws CertificationNotFoundException {
        return certificationService.findCertificationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificationResponseDTO> updateCertification(@PathVariable Long id, @RequestBody CertificationRequestDTO updatedCertification) throws CertificationNotFoundException {
        CertificationResponseDTO newCertification = certificationService.updateCertification(id, updatedCertification).get();
        return new ResponseEntity<>(newCertification, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertification(@PathVariable Long id) throws CertificationNotFoundException{
        certificationService.deleteCertification(id);
        return ResponseEntity.noContent().build();
    }
}
