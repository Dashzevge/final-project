package edu.miu.cse.finalproject.service.impl;

import edu.miu.cse.finalproject.dto.transaction.request.TransactionRequestDTO;
import edu.miu.cse.finalproject.dto.transaction.response.TransactionResponseDTO;
import edu.miu.cse.finalproject.mapper.TransactionMapper;
import edu.miu.cse.finalproject.model.Transaction;
import edu.miu.cse.finalproject.repository.TransactionRepository;
import edu.miu.cse.finalproject.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public Optional<TransactionResponseDTO> addTransaction(TransactionRequestDTO dto) {
        Transaction transaction = transactionMapper.toEntity(dto);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return Optional.of(transactionMapper.toResponse(savedTransaction));
    }

    @Override
    public Optional<TransactionResponseDTO> findTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with ID: " + id));
        return Optional.of(transactionMapper.toResponse(transaction));
    }

    @Override
    public List<TransactionResponseDTO> findAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(transactionMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<TransactionResponseDTO> updateTransaction(Long id, TransactionRequestDTO dto) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with ID: " + id));
        transaction.setTransactionAmount(dto.transactionAmount());
        transaction.setTransactionType(dto.transactionType());
        transaction.setTransactionDate(dto.transactionDate());
        transaction.setStatus(dto.status());
        Transaction updatedTransaction = transactionRepository.save(transaction);
        return Optional.of(transactionMapper.toResponse(updatedTransaction));
    }

    @Override
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new EntityNotFoundException("Transaction not found with ID: " + id);
        }
        transactionRepository.deleteById(id);
    }
}
