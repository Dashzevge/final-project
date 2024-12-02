package edu.miu.cse.finalproject.dto.transaction.response;

import java.time.LocalDateTime;

public record TransactionResponseDTO(
        Long id,
        Long paymentId,
        Double amount,
        String transactionType,
        LocalDateTime timestamp
) {
}

