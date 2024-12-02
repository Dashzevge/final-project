package edu.miu.cse.finalproject.controller;

import edu.miu.cse.finalproject.dto.transaction.request.TransactionRequestDTO;
import edu.miu.cse.finalproject.dto.transaction.response.TransactionResponseDTO;
import edu.miu.cse.finalproject.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<?> findAllTransaction() {
        List<TransactionResponseDTO> allTransaction = transactionService.findAllTransactions();
        return ResponseEntity.status(HttpStatus.OK).body(allTransaction);
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO TransactionRequestDTO) {
        TransactionResponseDTO savedTransaction = transactionService.addTransaction(TransactionRequestDTO).get();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> findTransactionById(@PathVariable Long id) {
        return transactionService.findTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> updateTransaction(@PathVariable Long id, @RequestBody TransactionRequestDTO updatedTransaction) {
        TransactionResponseDTO newTransaction = transactionService.updateTransaction(id, updatedTransaction).get();
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
