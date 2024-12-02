package edu.miu.cse.finalproject.service;

import edu.miu.cse.finalproject.dto.transaction.request.TransactionRequestDTO;
import edu.miu.cse.finalproject.dto.transaction.response.TransactionResponseDTO;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Optional<TransactionResponseDTO> addTransaction(TransactionRequestDTO dto);
    Optional<TransactionResponseDTO> findTransactionById(Long id);
    List<TransactionResponseDTO> findAllTransactions();
    Optional<TransactionResponseDTO> updateTransaction(Long id, TransactionRequestDTO dto);
    void deleteTransaction(Long id);
}
