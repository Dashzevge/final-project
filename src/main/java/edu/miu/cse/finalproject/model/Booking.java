package edu.miu.cse.finalproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime bookingDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;

    @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
    private List<Payment> payments;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employee;

    @ManyToMany(mappedBy = "bookings")
    private List<Job> jobs;

    @ManyToMany(mappedBy = "bookings")
    private List<User> users;

}
