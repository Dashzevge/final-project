package edu.miu.cse.finalproject.controller;

import edu.miu.cse.finalproject.dto.payment.request.PaymentRequestDTO;
import edu.miu.cse.finalproject.dto.payment.response.PaymentResponseDTO;
import edu.miu.cse.finalproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<?> findAllPayment() {
        List<PaymentResponseDTO> allPayment = paymentService.findAllPayments();
        return ResponseEntity.status(HttpStatus.OK).body(allPayment);
    }

    @PostMapping("/{bookingId}")
    public ResponseEntity<PaymentResponseDTO> createPayment(@PathVariable Long bookingId, @RequestBody PaymentRequestDTO paymentRequestDTO) {
        PaymentResponseDTO savedPayment = paymentService.addPayment(bookingId, paymentRequestDTO).get();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> findPaymentById(@PathVariable Long id) {
        return paymentService.findPaymentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> updatePayment(@PathVariable Long id, @RequestBody PaymentRequestDTO updatedPayment) {
        PaymentResponseDTO newPayment = paymentService.updatePayment(id, updatedPayment).get();
        return new ResponseEntity<>(newPayment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

}
