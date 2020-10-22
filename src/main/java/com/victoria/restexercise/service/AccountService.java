package com.victoria.restexercise.service;

import com.victoria.restexercise.dto.AccountDto;
import com.victoria.restexercise.dto.TransactionDto;
import com.victoria.restexercise.model.AccountEntity;
import com.victoria.restexercise.model.TransactionEntity;
import com.victoria.restexercise.model.TransactionRequest;

public interface AccountService {
    AccountDto getAccountById(Long accountId);
    AccountDto createAccount (AccountEntity account);
    //BigDecimal getAccountBalance(Long accountId);
    TransactionDto transferMoney(TransactionRequest transactionRequest) throws Exception;
}
