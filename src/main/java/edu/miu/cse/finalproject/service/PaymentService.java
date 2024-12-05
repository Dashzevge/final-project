package edu.miu.cse.finalproject.service;

import edu.miu.cse.finalproject.dto.payment.request.PaymentRequestDTO;
import edu.miu.cse.finalproject.dto.payment.response.PaymentResponseDTO;
import edu.miu.cse.finalproject.exception.booking.BookingNotFoundException;
import edu.miu.cse.finalproject.exception.payment.PaymentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Optional<PaymentResponseDTO> addPayment(Long bookingId, PaymentRequestDTO dto) throws BookingNotFoundException;
    Optional<PaymentResponseDTO> findPaymentById(Long id) throws PaymentNotFoundException;
    List<PaymentResponseDTO> findAllPayments();
    Optional<PaymentResponseDTO> updatePayment(Long id, PaymentRequestDTO dto) throws PaymentNotFoundException;
    void deletePayment(Long id) throws PaymentNotFoundException;
}
