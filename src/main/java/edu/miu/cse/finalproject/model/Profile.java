package edu.miu.cse.finalproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "profiles")
@NoArgsConstructor
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio; // Short description of the professional
    private String phoneNumber; // Contact number of the professional

    @Column(name = "experience_years")
    private int experienceYears; // Total years of experience

    @OneToOne(mappedBy = "profile") // Back reference to the User entity
    private User user;

    @OneToOne(cascade = CascadeType.ALL) // Add CascadeType.ALL
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @ManyToMany
    @JoinTable(
            name = "profile_certification", // Name of the join table
            joinColumns = @JoinColumn(name = "profile_id"), // Foreign key for Profile
            inverseJoinColumns = @JoinColumn(name = "certification_id") // Foreign key for Certification
    )
    private List<Certification> certifications; // List of certifications

    @ManyToMany
    @JoinTable(
            name = "profile_skill", // Name of the join table
            joinColumns = @JoinColumn(name = "profile_id"), // Foreign key for Profile
            inverseJoinColumns = @JoinColumn(name = "skill_id") // Foreign key for Skill
    )
    private List<Skill> skills; // List of skills

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
