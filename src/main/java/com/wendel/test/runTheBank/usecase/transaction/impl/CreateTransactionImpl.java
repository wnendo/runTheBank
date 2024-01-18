package com.wendel.test.runTheBank.usecase.transaction.impl;

import com.wendel.test.runTheBank.adapter.controller.mapper.TransactionMapper;
import com.wendel.test.runTheBank.adapter.controller.request.TransactionRequest;
import com.wendel.test.runTheBank.adapter.controller.response.TransactionResponse;
import com.wendel.test.runTheBank.domain.Account;
import com.wendel.test.runTheBank.domain.enuns.AccountStatus;
import com.wendel.test.runTheBank.domain.enuns.TransactionStatus;
import com.wendel.test.runTheBank.usecase.account.GetAccount;
import com.wendel.test.runTheBank.usecase.account.SaveAccount;
import com.wendel.test.runTheBank.usecase.transaction.CreateTransaction;
import com.wendel.test.runTheBank.usecase.transaction.SaveTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class CreateTransactionImpl implements CreateTransaction {
    private final SaveTransaction saveTransaction;
    private final TransactionMapper transactionMapper;
    private final GetAccount getAccount;
    private final SaveAccount saveAccount;

    public CreateTransactionImpl(SaveTransaction saveTransaction, TransactionMapper transactionMapper, GetAccount getAccount, SaveAccount saveAccount) {
        this.saveTransaction = saveTransaction;
        this.transactionMapper = transactionMapper;
        this.getAccount = getAccount;
        this.saveAccount = saveAccount;
    }

    @Override
    public TransactionResponse execute(TransactionRequest transactionRequest){
        try {
            var transaction = transactionMapper.convertTransactionRequestToTransaction(transactionRequest);

            var transactionFrom = getAccount.execute(transaction.getFromAccount());
            var transactionTo = getAccount.execute(transaction.getToAccount());

            if (transactionFrom.getAccountStatus() != AccountStatus.ACTIVE || transactionFrom.getBalance().compareTo(transaction.getAmount()) < 0 ){
                return TransactionResponse.builder()
                        .id(transaction.getId())
                        .message("Unable to make transaction")
                        .build();
            }else{
                transaction.setStatus(TransactionStatus.EXECUTED);
                saveTransaction.execute(transaction);

                updateFirstBalanceAccount(transactionFrom, transaction.getAmount());
                updateSecondBalanceAccount(transactionTo, transaction.getAmount());

                return TransactionResponse.builder()
                        .id(transaction.getId())
                        .message("Transaction was successful")
                        .build();
            }
        }catch (Exception e){
            log.error("Error while trying to making transaction from account {} - {}", transactionRequest.getFromAccount(), e.getMessage());
            return TransactionResponse.builder()
                    .message("Error while trying to making transaction from account")
                    .build();
        }
    }

    private void updateFirstBalanceAccount(Account account, BigDecimal amount){
        var newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);
        saveAccount.execute(account);
    }
    private void updateSecondBalanceAccount(Account account, BigDecimal amount){
        var newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        saveAccount.execute(account);
    }
}
