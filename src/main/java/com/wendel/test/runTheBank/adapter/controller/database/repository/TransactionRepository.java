package com.wendel.test.runTheBank.adapter.controller.database.repository;

import com.wendel.test.runTheBank.adapter.controller.database.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {

}
