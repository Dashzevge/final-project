package edu.miu.cse.finalproject.dto.payment.request;

public record PaymentRequestDTO(
        Long bookingId,
        Double amount,
        String method
) {
}

