package edu.miu.cse.finalproject.dto.profile.response;

import edu.miu.cse.finalproject.dto.address.response.AddressResponseDTO;
import edu.miu.cse.finalproject.dto.certification.response.CertificationResponseDTO;
import edu.miu.cse.finalproject.dto.skill.response.SkillResponseDTO;

import java.util.List;

public class ProfileResponseDTO {
    private Long id;
    private String bio;
    private String phoneNumber;
    private int experienceYears;
    private AddressResponseDTO address;
    private List<SkillResponseDTO> skills;
    private List<CertificationResponseDTO> certifications;

    // Getters
    public Long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public AddressResponseDTO getAddress() {
        return address;
    }

    public List<SkillResponseDTO> getSkills() {
        return skills;
    }

    public List<CertificationResponseDTO> getCertifications() {
        return certifications;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public void setAddress(AddressResponseDTO address) {
        this.address = address;
    }

    public void setSkills(List<SkillResponseDTO> skills) {
        this.skills = skills;
    }

    public void setCertifications(List<CertificationResponseDTO> certifications) {
        this.certifications = certifications;
    }

    public ProfileResponseDTO() {
    }

    public ProfileResponseDTO(Long id, String bio, String phoneNumber, int experienceYears, AddressResponseDTO address, List<SkillResponseDTO> skills, List<CertificationResponseDTO> certifications) {
        this.id = id;
        this.bio = bio;
        this.phoneNumber = phoneNumber;
        this.experienceYears = experienceYears;
        this.address = address;
        this.skills = skills;
        this.certifications = certifications;
    }
}
