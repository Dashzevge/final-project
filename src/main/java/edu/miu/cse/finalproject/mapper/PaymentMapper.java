package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.payment.request.PaymentRequestDTO;
import edu.miu.cse.finalproject.dto.payment.response.PaymentResponseDTO;
import edu.miu.cse.finalproject.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "paymentDate", target = "paymentDate")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    @Mapping(source = "status", target = "status")
    Payment toEntity(PaymentRequestDTO dto);
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "paymentDate", target = "paymentDate")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    @Mapping(source = "status", target = "status")
    PaymentResponseDTO toResponse(Payment entity);
}
