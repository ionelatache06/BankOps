package com.victoria.restexercise.repository;

import com.victoria.restexercise.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}
