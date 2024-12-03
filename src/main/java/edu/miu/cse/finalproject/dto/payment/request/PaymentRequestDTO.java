package edu.miu.cse.finalproject.dto.payment.request;

import edu.miu.cse.finalproject.util.Status;

import java.time.LocalDateTime;

public record PaymentRequestDTO(
         Double amount,
         LocalDateTime paymentDate,
         String paymentMethod,
         String status
) {
}

