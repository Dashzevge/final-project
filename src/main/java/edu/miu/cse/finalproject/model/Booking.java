package edu.miu.cse.finalproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private LocalDate bookingDate;
    private String status;
//
//    @ManyToOne
//    @JoinColumn(name = "job_id")
//    private Job job;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
