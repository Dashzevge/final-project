package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.payment.request.PaymentRequestDTO;
import edu.miu.cse.finalproject.dto.payment.response.PaymentResponseDTO;
import edu.miu.cse.finalproject.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    // Mapping PaymentRequestDTO to Payment entity
    public Payment toEntity(PaymentRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setStatus(dto.getStatus());

        return payment;
    }

    // Mapping Payment entity to PaymentResponseDTO
    public PaymentResponseDTO toResponse(Payment entity) {
        if (entity == null) {
            return null;
        }

        PaymentResponseDTO response = new PaymentResponseDTO();
        response.setAmount(entity.getAmount());
        response.setPaymentDate(entity.getPaymentDate());
        response.setPaymentMethod(entity.getPaymentMethod());
        response.setStatus(entity.getStatus());

        return response;
    }
}
