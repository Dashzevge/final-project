package edu.miu.cse.finalproject.service;

import edu.miu.cse.finalproject.dto.payment.request.PaymentRequestDTO;
import edu.miu.cse.finalproject.dto.payment.response.PaymentResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Optional<PaymentResponseDTO> addPayment(Long bookingId, PaymentRequestDTO dto);
    Optional<PaymentResponseDTO> findPaymentById(Long id);
    List<PaymentResponseDTO> findAllPayments();
    Optional<PaymentResponseDTO> updatePayment(Long id, PaymentRequestDTO dto);
    void deletePayment(Long id);
}
