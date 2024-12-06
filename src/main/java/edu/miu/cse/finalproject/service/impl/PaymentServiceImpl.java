package edu.miu.cse.finalproject.service.impl;


import edu.miu.cse.finalproject.dto.payment.request.PaymentRequestDTO;
import edu.miu.cse.finalproject.dto.payment.response.PaymentResponseDTO;
import edu.miu.cse.finalproject.exception.booking.BookingNotFoundException;
import edu.miu.cse.finalproject.exception.payment.PaymentNotFoundException;
import edu.miu.cse.finalproject.mapper.PaymentMapper;
import edu.miu.cse.finalproject.model.Booking;
import edu.miu.cse.finalproject.model.Payment;
import edu.miu.cse.finalproject.repository.BookingRepository;
import edu.miu.cse.finalproject.repository.PaymentRepository;
import edu.miu.cse.finalproject.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public Optional<PaymentResponseDTO> addPayment(Long bookingId, PaymentRequestDTO dto) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with ID: " + bookingId));

        if (!"COMPLETED".equals(booking.getStatus())) {
            throw new IllegalStateException("Payment can only be finalized for completed bookings.");
        }

        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setBooking(booking);
        payment.setStatus("PAID");

        Payment savedPayment = paymentRepository.save(payment);

        return Optional.of(paymentMapper.toResponse(savedPayment));
    }

    @Override
    public Optional<PaymentResponseDTO> findPaymentById(Long id) throws PaymentNotFoundException {
        Payment booking = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + id));
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
    public Optional<PaymentResponseDTO> updatePayment(Long id, PaymentRequestDTO dto) throws PaymentNotFoundException{
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with ID: " + id));
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setStatus(dto.getStatus());
        Payment updatedPayment = paymentRepository.save(payment);
        return Optional.of(paymentMapper.toResponse(updatedPayment));
    }


    @Override
    public void deletePayment(Long id) throws  PaymentNotFoundException {
        if (!paymentRepository.existsById(id)) {
            throw new PaymentNotFoundException("Payment not found with ID: " + id);
        }
        paymentRepository.deleteById(id);
    }
}
