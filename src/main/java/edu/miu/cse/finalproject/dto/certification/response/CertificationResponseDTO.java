package edu.miu.cse.finalproject.dto.certification.response;

import java.time.LocalDate;

public class CertificationResponseDTO {
    private Long id;
    private String name;
    private String authority;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private Long profileId;

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthority() {
        return authority;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public Long getProfileId() {
        return profileId;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public CertificationResponseDTO() {
    }

    public CertificationResponseDTO(Long id, String name, String authority, LocalDate issueDate, LocalDate expiryDate, Long profileId) {
        this.id = id;
        this.name = name;
        this.authority = authority;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.profileId = profileId;
    }
}
