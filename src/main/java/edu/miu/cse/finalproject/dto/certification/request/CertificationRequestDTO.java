package edu.miu.cse.finalproject.dto.certification.request;

import java.time.LocalDate;

public class CertificationRequestDTO {
    private String name;
    private String authority;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private Long profileId;

    // Getters
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

    public CertificationRequestDTO(String name, String authority, LocalDate issueDate, LocalDate expiryDate, Long profileId) {
        this.name = name;
        this.authority = authority;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.profileId = profileId;
    }
}
