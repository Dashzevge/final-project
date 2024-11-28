package edu.miu.cse.finalproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String transactionDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "payment_id") // Foreign key to Payment
    private Payment payment; // Each Transaction is linked to one Payment

}
