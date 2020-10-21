package com.victoria.restexercise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Currency;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private Long sourceAccountId;
    private Long destionationAccountId;
    private Money money;
    private Currency currency;
}
