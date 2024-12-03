package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.transaction.request.TransactionRequestDTO;
import edu.miu.cse.finalproject.dto.transaction.response.TransactionResponseDTO;
import edu.miu.cse.finalproject.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(source = "transactionAmount", target = "transactionAmount")
    @Mapping(source = "transactionDate", target = "transactionDate")
    @Mapping(source = "transactionType", target = "transactionType")
    @Mapping(source = "status", target = "status")
    Transaction toEntity(TransactionRequestDTO dto);
    @Mapping(source = "transactionAmount", target = "transactionAmount")
    @Mapping(source = "transactionDate", target = "transactionDate")
    @Mapping(source = "transactionType", target = "transactionType")
    @Mapping(source = "status", target = "status")
    TransactionResponseDTO toResponse(Transaction entity);
}
