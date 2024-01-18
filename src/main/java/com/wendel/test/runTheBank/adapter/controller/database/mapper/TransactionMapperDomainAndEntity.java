package com.wendel.test.runTheBank.adapter.controller.database.mapper;

import com.wendel.test.runTheBank.adapter.controller.database.entity.TransactionEntity;
import com.wendel.test.runTheBank.domain.Transaction;
import com.wendel.test.runTheBank.domain.enuns.TransactionStatus;
import com.wendel.test.runTheBank.usecase.cipher.DecryptRequest;
import com.wendel.test.runTheBank.usecase.cipher.EncryptRequest;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapperDomainAndEntity {
    private final EncryptRequest encryptRequest;
    private final DecryptRequest decryptRequest;

    public TransactionMapperDomainAndEntity(EncryptRequest encryptRequest, DecryptRequest decryptRequest) {
        this.encryptRequest = encryptRequest;
        this.decryptRequest = decryptRequest;
    }

    public TransactionEntity convertTransactionToTransactionEntity(Transaction transaction) {
        return TransactionEntity.builder()
                .id(transaction.getId())
                .fromAccount(transaction.getFromAccount())
                .toAccount(transaction.getToAccount())
                .status(transaction.getStatus().name())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .build();
    }
    public Transaction convertTransactionEntityToTransaction(TransactionEntity transactionEntity) {
        return Transaction.builder()
                .id(transactionEntity.getId())
                .fromAccount(transactionEntity.getFromAccount())
                .toAccount(transactionEntity.getToAccount())
                .status(TransactionStatus.getTransactionStatusByString(transactionEntity.getStatus()))
                .amount(transactionEntity.getAmount())
                .date(transactionEntity.getDate())
                .build();
    }

}
