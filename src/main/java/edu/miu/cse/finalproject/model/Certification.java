package edu.miu.cse.finalproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "certifications")
@Data
@NoArgsConstructor
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String authority;
    private LocalDate issueDate;
    private LocalDate expiryDate;

    @ManyToMany(mappedBy = "certifications") // Correct mapping reference
    private List<Profile> profiles;
}
