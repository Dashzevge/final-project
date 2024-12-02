package edu.miu.cse.finalproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "profiles")
@Data
@NoArgsConstructor
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;
    private String phoneNumber;

    @Column(name = "experience_years")
    private int experienceYears;

    @OneToOne
    @JoinColumn(name = "address_id") // Specify the foreign key column for the address
    private Address address;

    @ManyToMany
    @JoinTable(
            name = "profile_certification", // Name of the join table
            joinColumns = @JoinColumn(name = "profile_id"), // Foreign key for Profile
            inverseJoinColumns = @JoinColumn(name = "certification_id") // Foreign key for Certification
    )
    private List<Certification> certifications;

    @ManyToMany
    @JoinTable(
            name = "profile_skill", // Name of the join table
            joinColumns = @JoinColumn(name = "profile_id"), // Foreign key for Profile
            inverseJoinColumns = @JoinColumn(name = "skill_id") // Foreign key for Skill
    )
    private List<Skill> skills;
}
