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
    private Double price;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToMany(mappedBy = "jobs")
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "job_booking", // Name of the join table
            joinColumns = @JoinColumn(name = "job_id"), // Foreign key for User
            inverseJoinColumns = @JoinColumn(name = "booking_id") // Foreign key for Job
    )
    private List<Booking> bookings;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Review> reviews;


}
