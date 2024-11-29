package edu.miu.cse.finalproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "certifications")
@Data
@NoArgsConstructor
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String organization;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
