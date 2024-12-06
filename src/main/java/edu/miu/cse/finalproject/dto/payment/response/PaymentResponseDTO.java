package edu.miu.cse.finalproject.dto.payment.response;

import java.time.LocalDateTime;

public class PaymentResponseDTO {
    private Long id;
    private Long bookingId;
    private Double amount;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String status;

    // Getters
    public Long getId() {
        return id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PaymentResponseDTO() {
    }

    public PaymentResponseDTO(Long id, Long bookingId, Double amount, LocalDateTime paymentDate, String paymentMethod, String status) {
        this.id = id;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }
}
