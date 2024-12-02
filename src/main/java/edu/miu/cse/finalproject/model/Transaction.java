package edu.miu.cse.finalproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double transactionAmount;
    private LocalDateTime transactionDate;
    private String transactionType;
    private String status;

    @ManyToOne
    @JoinColumn(name = "payment_id") // Foreign key to Payment
    private Payment payment; // Each Transaction is linked to one Payment

}
