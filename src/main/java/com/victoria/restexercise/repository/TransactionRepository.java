package com.victoria.restexercise.repository;

import com.victoria.restexercise.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
