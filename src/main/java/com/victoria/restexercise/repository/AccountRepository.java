package com.victoria.restexercise.repository;

import com.victoria.restexercise.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findAccountByAccountId(Long accountId);
    AccountEntity save(AccountEntity account);

}
