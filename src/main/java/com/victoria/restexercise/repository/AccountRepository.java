package com.victoria.restexercise.repository;

import com.victoria.restexercise.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByAccountId(Long accountId);
    Account save(Account account);

}
