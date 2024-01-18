package com.wendel.test.runTheBank.adapter.controller.mapper;

import com.wendel.test.runTheBank.adapter.controller.request.TransactionRequest;
import com.wendel.test.runTheBank.adapter.controller.response.TransactionResponse;
import com.wendel.test.runTheBank.domain.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TransactionMapper {

    public Transaction convertTransactionRequestToTransaction(TransactionRequest transactionRequest) {
        return Transaction.builder()
                .id(UUID.randomUUID().toString())
                .fromAccount(transactionRequest.getFromAccount())
                .toAccount(transactionRequest.getToAccount())
                .amount(transactionRequest.getAmount())
                .date(LocalDateTime.now())
                .build();
    }
    public TransactionResponse convertTransactionToTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .build();
    }

}
