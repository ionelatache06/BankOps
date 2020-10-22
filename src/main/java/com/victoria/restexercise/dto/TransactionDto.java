package com.victoria.restexercise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Long transactionId;
    private Currency currency;
    private Money money;
    private Long sourceAccountId;
    private Long destinationAccountId;
    private Timestamp transactionDateTime;
}
