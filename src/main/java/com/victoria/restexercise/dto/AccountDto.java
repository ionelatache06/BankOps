package com.victoria.restexercise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long accountId;
    private String name;
    private Currency currency;
    private Money balance;
    private Boolean treasury;
}
