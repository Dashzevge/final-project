package edu.miu.cse.finalproject.dto.transaction.request;

import java.time.LocalDateTime;

public record TransactionRequestDTO(
         Double transactionAmount,
         LocalDateTime transactionDate,
         String transactionType,
         String status
) {
}

