package com.victoria.restexercise.service.impl;

import com.victoria.restexercise.dto.AccountDto;
import com.victoria.restexercise.dto.TransactionDto;
import com.victoria.restexercise.mapper.AccountEntityToAccountDtoMapper;
import com.victoria.restexercise.mapper.TransactionEntityToTransactionDtoMapper;
import com.victoria.restexercise.model.AccountEntity;
import com.victoria.restexercise.model.TransactionEntity;
import com.victoria.restexercise.model.TransactionRequest;
import com.victoria.restexercise.repository.AccountRepository;
import com.victoria.restexercise.repository.TransactionRepository;
import com.victoria.restexercise.service.AccountService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepo;

    @Autowired
    TransactionRepository transactionRepo;

    AccountEntityToAccountDtoMapper accountEntityToAccountDtoMapper = Mappers.getMapper(
    AccountEntityToAccountDtoMapper.class);

    TransactionEntityToTransactionDtoMapper transactionEntityToTransactionDtoMapper =
    Mappers.getMapper(TransactionEntityToTransactionDtoMapper.class);

    @Override
    public AccountDto getAccountById(Long accountId) {
        AccountEntity accountEntity= accountRepo.findAccountByAccountId(accountId);
        AccountDto accountDto = accountEntityToAccountDtoMapper.mapEntityToDto(accountEntity);
        if (!(accountDto == null)){
            return accountDto;
        }
        return null;
    }

    @Override
    public AccountDto createAccount(AccountEntity account) {
        AccountDto accountDto = null;
        Set<ConstraintViolation<AccountEntity>> constraintViolations =
                Validation.buildDefaultValidatorFactory().getValidator().validate(account);

        if (constraintViolations.size() == 0) {
            accountRepo.save(account);
            AccountEntity accountEntity =  accountRepo.findAccountByAccountId(account.getAccountId());
            accountDto = accountEntityToAccountDtoMapper.mapEntityToDto(accountEntity);
            return accountDto;
        }

        return null;
    }

    @Override
    public TransactionDto transferMoney(TransactionRequest transactionRequest) throws Exception {
        Long id =0L;
        Long sourceAccountId = transactionRequest.getSourceAccountId();
        Long destinationAccountId = transactionRequest.getDestionationAccountId();
        BigDecimal amount = transactionRequest.getMoney().getAmount();

        AccountEntity sourceAccount = accountRepo.findAccountByAccountId(sourceAccountId);
        AccountEntity destinationAccount = accountRepo.findAccountByAccountId(destinationAccountId);
        BigDecimal sourceAccountBalance = sourceAccount.getBalance().getAmount();
        int result = sourceAccountBalance.compareTo(amount);

        //if it is  a treasury account or if it is a normal account whose balance exceeds the amount then
        // perform transaction and save the transaction to the database
        if (sourceAccount.getTreasury() ||
                (!sourceAccount.getTreasury() && result >=0 )){
               performTransaction(sourceAccount, destinationAccount, transactionRequest);
               TransactionEntity transactionEntity = saveTransaction(transactionRequest, sourceAccountId,
                       destinationAccountId);
               TransactionDto transactionDto = transactionEntityToTransactionDtoMapper.mapEntityToDto(transactionEntity);
               return transactionDto;
        }
        //if it is not a treasury account and the amount exceeds the balance then throw an exception
           throw new Exception("Not enough money in source account");
    }
       /* //if it is not a treasury account
        if (!sourceAccount.getTreasury()) {
            //if result>0 then sourceAccountBallance is greater than the amount; if result<0 viceversa; result = 0 means equality
            if (result >= 0) {
                //debit and credit the accounts and save their state in the database
                performTransaction(sourceAccount, destinationAccount, transactionRequest);
                return saveTransaction(transactionRequest, sourceAccountId, destinationAccountId);
                //TO DELET AFTER TEST: Currency.getInstance(Locale.SPAIN), new Money(524767.0987))
            }
            throw new Exception("Not enough money in source account");
        }//if it is a treasury account, perform transaction
        performTransaction(sourceAccount, destinationAccount, transactionRequest);
        return saveTransaction(transactionRequest, sourceAccountId, destinationAccountId);
    }
*/

    public void performTransaction(AccountEntity sourceAccount, AccountEntity destinationAccount,
                                   TransactionRequest transactionRequest){
        withdraw(sourceAccount, transactionRequest.getMoney());
        accountRepo.save(sourceAccount);
        deposit(destinationAccount, transactionRequest.getMoney());
        accountRepo.save(destinationAccount);
    }

    public TransactionEntity saveTransaction(TransactionRequest transactionRequest, Long sourceAccountId,
                                             Long destinationAccountId){
        Long id =0L;
        TransactionEntity transactionEntity = transactionRepo.save(new TransactionEntity
                (id++, transactionRequest.getCurrency(), transactionRequest.getMoney(),
                        sourceAccountId, destinationAccountId, new Timestamp(System.currentTimeMillis())));
        return transactionEntity;
    }

    private AccountEntity withdraw(AccountEntity account, Money money) {

        BigDecimal currentBalanceBeforeWithdrawl = account.getBalance().getAmount();

        BigDecimal currentBalanceAfterWithdrawl = currentBalanceBeforeWithdrawl.subtract(money.getAmount());

        account.getBalance().setAmount(currentBalanceAfterWithdrawl);

        return account;
    }

    private AccountEntity deposit(AccountEntity account, Money money) {

        BigDecimal currentBalanceBeforeDeposit = account.getBalance().getAmount();

        BigDecimal currentBalanceAfterDeposit = currentBalanceBeforeDeposit.add(money.getAmount());

        account.getBalance().setAmount(currentBalanceAfterDeposit);

        return account;
    }
}
