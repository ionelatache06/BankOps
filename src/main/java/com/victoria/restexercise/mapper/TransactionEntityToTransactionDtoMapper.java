package com.victoria.restexercise.mapper;

import com.victoria.restexercise.dto.TransactionDto;
import com.victoria.restexercise.model.TransactionEntity;

public abstract class TransactionEntityToTransactionDtoMapper {
    public abstract TransactionDto mapEntityToDto(TransactionEntity transactionEntity);
}
