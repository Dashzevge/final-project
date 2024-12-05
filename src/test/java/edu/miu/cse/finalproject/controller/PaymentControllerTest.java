package edu.miu.cse.finalproject.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import edu.miu.cse.finalproject.dto.payment.request.PaymentRequestDTO;
import edu.miu.cse.finalproject.dto.payment.response.PaymentResponseDTO;
import edu.miu.cse.finalproject.exception.booking.BookingNotFoundException;
import edu.miu.cse.finalproject.exception.payment.PaymentNotFoundException;
import edu.miu.cse.finalproject.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    @Test
    void findAllPayment() {
        List<PaymentResponseDTO> mockPayments = List.of(
                new PaymentResponseDTO(1L, 101L, 200.0, LocalDateTime.now(), "Credit Card", "Completed"),
                new PaymentResponseDTO(2L, 102L, 150.0, LocalDateTime.now(), "PayPal", "Pending")
        );

        when(paymentService.findAllPayments()).thenReturn(mockPayments);

        ResponseEntity<?> response = paymentController.findAllPayment();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPayments, response.getBody());

        verify(paymentService, times(1)).findAllPayments();
    }

    @Test
    void createPayment() throws BookingNotFoundException {
        Long bookingId = 101L;
        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO(250.0, LocalDateTime.now(), "Credit Card", "Pending");
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO(1L, bookingId, 250.0, LocalDateTime.now(), "Credit Card", "Pending");

        when(paymentService.addPayment(bookingId, paymentRequestDTO)).thenReturn(Optional.of(paymentResponseDTO));

        ResponseEntity<PaymentResponseDTO> response = paymentController.createPayment(bookingId, paymentRequestDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(paymentResponseDTO, response.getBody());

        verify(paymentService, times(1)).addPayment(bookingId, paymentRequestDTO);
    }

    @Test
    void findPaymentById() throws PaymentNotFoundException {
        Long paymentId = 1L;
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO(1L, 101L, 200.0, LocalDateTime.now(), "Credit Card", "Completed");

        when(paymentService.findPaymentById(paymentId)).thenReturn(Optional.of(paymentResponseDTO));

        ResponseEntity<PaymentResponseDTO> response = paymentController.findPaymentById(paymentId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(paymentResponseDTO, response.getBody());

        verify(paymentService, times(1)).findPaymentById(paymentId);
    }

    @Test
    void updatePayment() throws PaymentNotFoundException {
        Long paymentId = 1L;
        PaymentRequestDTO updatedPayment = new PaymentRequestDTO(300.0, LocalDateTime.now(), "Bank Transfer", "Completed");
        PaymentResponseDTO updatedPaymentResponse = new PaymentResponseDTO(1L, 101L, 300.0, LocalDateTime.now(), "Bank Transfer", "Completed");

        when(paymentService.updatePayment(paymentId, updatedPayment)).thenReturn(Optional.of(updatedPaymentResponse));

        ResponseEntity<PaymentResponseDTO> response = paymentController.updatePayment(paymentId, updatedPayment);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedPaymentResponse, response.getBody());

        verify(paymentService, times(1)).updatePayment(paymentId, updatedPayment);
    }

    @Test
    void deletePayment() throws PaymentNotFoundException {
        Long paymentId = 1L;

        doNothing().when(paymentService).deletePayment(paymentId);

        ResponseEntity<Void> response = paymentController.deletePayment(paymentId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(paymentService, times(1)).deletePayment(paymentId);
    }

}
