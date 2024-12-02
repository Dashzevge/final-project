package edu.miu.cse.finalproject.dto.transaction.request;

public record TransactionRequestDTO(
        Long paymentId,
        Double amount,
        String transactionType
) {
}

