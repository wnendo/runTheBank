package com.wendel.test.runTheBank.usecase.transaction.impl;

import com.wendel.test.runTheBank.adapter.controller.mapper.TransactionMapper;
import com.wendel.test.runTheBank.adapter.controller.response.TransactionResponse;
import com.wendel.test.runTheBank.adapter.gateway.db.DbGateway;
import com.wendel.test.runTheBank.domain.validator.ApiException;
import com.wendel.test.runTheBank.domain.validator.ExceptionMessage;
import com.wendel.test.runTheBank.usecase.transaction.GetTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetTransactionImpl implements GetTransaction {
    private final DbGateway dbGateway;
    private final TransactionMapper transactionMapper;

    public GetTransactionImpl(DbGateway dbGateway, TransactionMapper transactionMapper) {
        this.dbGateway = dbGateway;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionResponse execute(String id) {
        try {
            return transactionMapper.convertTransactionToTransactionResponse(dbGateway.getTransaction(id));
        } catch (Exception e) {
            log.error("Error while trying to get transaction from id {} - {}", id, e.getMessage());
            throw new ApiException(ExceptionMessage.valueOf("Error while trying to get transaction"));
        }
    }
}
