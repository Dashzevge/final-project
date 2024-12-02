package edu.miu.cse.finalproject.mapper;

import edu.miu.cse.finalproject.dto.transaction.request.TransactionRequestDTO;
import edu.miu.cse.finalproject.dto.transaction.response.TransactionResponseDTO;
import edu.miu.cse.finalproject.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction toEntity(TransactionRequestDTO dto);
    TransactionResponseDTO toResponse(Transaction entity);
}
