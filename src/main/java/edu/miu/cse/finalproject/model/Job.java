package edu.miu.cse.finalproject.model;

import edu.miu.cse.finalproject.util.Category;
import edu.miu.cse.finalproject.util.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    private String location;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double budget;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToMany(mappedBy = "jobs")
    private List<User> users;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Booking> bookings;

}
