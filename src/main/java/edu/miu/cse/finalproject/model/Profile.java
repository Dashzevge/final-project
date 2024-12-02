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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
    private Address address;

    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
    private List<Certification> certifications;

    @OneToMany(mappedBy = "profile", fetch = FetchType.LAZY)
    private List<Skill> skills;
}
