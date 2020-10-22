package com.victoria.restexercise.controller;

import com.victoria.restexercise.model.AccountEntity;
import com.victoria.restexercise.model.TransactionEntity;
import com.victoria.restexercise.model.TransactionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface AccountController {
    @GetMapping(value="/getAccount")
    ResponseEntity<?> getAccountById(@RequestParam Optional<Long> accountId);

    @PostMapping(value="/createAccount")
    ResponseEntity<?> createAccount (@RequestBody AccountEntity account);

    @PostMapping(value="/transferMoney")
    ResponseEntity<?> transferMoney(TransactionRequest transactionRequest);
}
