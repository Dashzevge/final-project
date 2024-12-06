package edu.miu.cse.finalproject.dto.payment.request;

import java.time.LocalDateTime;

public class PaymentRequestDTO {
    private Double amount;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String status;

    // Getters
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

    public PaymentRequestDTO(Double amount, LocalDateTime paymentDate, String paymentMethod, String status) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }
}
