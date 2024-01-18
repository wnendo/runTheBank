package com.wendel.test.runTheBank.usecase.transaction.impl;

import com.wendel.test.runTheBank.adapter.controller.mapper.TransactionMapper;
import com.wendel.test.runTheBank.adapter.controller.request.TransactionRequest;
import com.wendel.test.runTheBank.adapter.controller.response.TransactionResponse;
import com.wendel.test.runTheBank.adapter.gateway.db.DbGateway;
import com.wendel.test.runTheBank.domain.Account;
import com.wendel.test.runTheBank.domain.Transaction;
import com.wendel.test.runTheBank.domain.enuns.AccountStatus;
import com.wendel.test.runTheBank.domain.validator.ApiException;
import com.wendel.test.runTheBank.domain.validator.ExceptionMessage;
import com.wendel.test.runTheBank.usecase.transaction.CreateTransaction;
import com.wendel.test.runTheBank.usecase.transaction.SaveTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class SaveTransactionImpl implements SaveTransaction {
    private final DbGateway dbGateway;

    public SaveTransactionImpl(DbGateway dbGateway) {
        this.dbGateway = dbGateway;
    }

    @Override
    public void execute(Transaction transaction) {
        try {
            dbGateway.saveTransaction(transaction);
        } catch (Exception e) {
            log.error("Error while trying to save transaction from account {} - {}", transaction.getFromAccount(), e.getMessage());
            throw new ApiException(ExceptionMessage.valueOf("Error while trying to save transaction"));
        }
    }
}
