package edu.miu.cse.finalproject.dto.payment.response;

import java.time.LocalDateTime;

public record PaymentResponseDTO(
        Long id,
        Long bookingId,
        Double amount,
        LocalDateTime paymentDate,
        String paymentMethod,
        String status
) {
}

