package com.victoria.restexercise.controller.impl;
import com.victoria.restexercise.controller.AccountController;
import com.victoria.restexercise.dto.AccountDto;
import com.victoria.restexercise.dto.response.Response;
import com.victoria.restexercise.dto.TransactionDto;
import com.victoria.restexercise.model.AccountEntity;
import com.victoria.restexercise.model.TransactionRequest;
import com.victoria.restexercise.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

@RestController
public class AccountControllerImpl implements AccountController {

    @Autowired
    private AccountService accountService;

    @Override
    public ResponseEntity<?> getAccountById(Optional<Long> accountId) {
        if (accountId.isPresent()){
        AccountDto accountDto = accountService.getAccountById(accountId.get());
            if (accountDto !=null){
                return ResponseEntity.status(HttpStatus.OK).body(accountDto);
            }else{
                //accountId was passed but it is not found in the db
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response
                        (404, "AccountId not found"));
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response
                    (400, "Invalid request"));
        }
    }

    @Override
    public ResponseEntity<?> createAccount(AccountEntity account) {
       AccountDto newAccount = accountService.createAccount(account);
       if (newAccount!=null){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response
                   (404, "Invalid account body"));
       }
       return ResponseEntity.status(HttpStatus.OK).body(newAccount);
    }

    @Override
    public ResponseEntity<?> transferMoney(TransactionRequest transactionRequest){
        try{
            TransactionDto transactionDto = accountService.transferMoney(transactionRequest);
            if (transactionDto!=null){
                return ResponseEntity.status(HttpStatus.OK).body(transactionDto);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(500,
                    "Error"
            ));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(404, "Not found"));
    }
}
