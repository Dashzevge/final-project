package edu.miu.cse.finalproject.service.impl;


import edu.miu.cse.finalproject.dto.payment.request.PaymentRequestDTO;
import edu.miu.cse.finalproject.dto.payment.response.PaymentResponseDTO;
import edu.miu.cse.finalproject.mapper.PaymentMapper;
import edu.miu.cse.finalproject.model.Booking;
import edu.miu.cse.finalproject.model.Payment;
import edu.miu.cse.finalproject.repository.BookingRepository;
import edu.miu.cse.finalproject.repository.PaymentRepository;
import edu.miu.cse.finalproject.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Optional<PaymentResponseDTO> addPayment(PaymentRequestDTO dto) {
        Payment payment = paymentMapper.toEntity(dto);
        Payment savedPayment = paymentRepository.save(payment);
        return Optional.of(paymentMapper.toResponse(savedPayment));
    }

    @Override
    public Optional<PaymentResponseDTO> findPaymentById(Long id) {
        Payment booking = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with ID: " + id));
        return Optional.of(paymentMapper.toResponse(booking));
    }

    @Override
    public List<PaymentResponseDTO> findAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<PaymentResponseDTO> updatePayment(Long id, PaymentRequestDTO dto) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with ID: " + id));
        payment.setAmount(dto.amount());
        payment.setPaymentMethod(dto.paymentMethod());
        payment.setPaymentDate(dto.paymentDate());
        payment.setStatus(dto.status());
        Payment updatedPayment = paymentRepository.save(payment);
        return Optional.of(paymentMapper.toResponse(updatedPayment));
    }

    @Override
    public PaymentResponseDTO finalizePayment(Long bookingId, PaymentRequestDTO paymentDto) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + bookingId));

        if (!"COMPLETED".equals(booking.getStatus())) {
            throw new IllegalStateException("Payment can only be finalized for completed bookings.");
        }

        Payment payment = new Payment();
        payment.setAmount(paymentDto.amount());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setBooking(booking);
        payment.setStatus("PAID");

        Payment savedPayment = paymentRepository.save(payment);

        return paymentMapper.toResponse(savedPayment);
    }

    @Override
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new EntityNotFoundException("Payment not found with ID: " + id);
        }
        paymentRepository.deleteById(id);
    }
}
