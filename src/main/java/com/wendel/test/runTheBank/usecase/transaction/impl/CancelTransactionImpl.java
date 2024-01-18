package com.wendel.test.runTheBank.usecase.transaction.impl;

import com.wendel.test.runTheBank.adapter.controller.mapper.AccountMapper;
import com.wendel.test.runTheBank.adapter.controller.mapper.TransactionMapper;
import com.wendel.test.runTheBank.adapter.controller.response.TransactionResponse;
import com.wendel.test.runTheBank.domain.Account;
import com.wendel.test.runTheBank.domain.enuns.TransactionStatus;
import com.wendel.test.runTheBank.usecase.account.GetAccount;
import com.wendel.test.runTheBank.usecase.account.SaveAccount;
import com.wendel.test.runTheBank.usecase.notification.SendNotification;
import com.wendel.test.runTheBank.usecase.transaction.CancelTransaction;
import com.wendel.test.runTheBank.usecase.transaction.GetTransaction;
import com.wendel.test.runTheBank.usecase.transaction.SaveTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
public class CancelTransactionImpl implements CancelTransaction {
    private final SaveTransaction saveTransaction;
    private final TransactionMapper transactionMapper;
    private final AccountMapper accountMapper;
    private final GetAccount getAccount;
    private final GetTransaction getTransaction;
    private final SaveAccount saveAccount;
    private final SendNotification sendNotification;

    public CancelTransactionImpl(SaveTransaction saveTransaction, TransactionMapper transactionMapper, AccountMapper accountMapper, GetAccount getAccount, GetTransaction getTransaction, SaveAccount saveAccount, SendNotification sendNotification) {
        this.saveTransaction = saveTransaction;
        this.transactionMapper = transactionMapper;
        this.accountMapper = accountMapper;
        this.getAccount = getAccount;
        this.getTransaction = getTransaction;
        this.saveAccount = saveAccount;
        this.sendNotification = sendNotification;
    }

    @Override
    public TransactionResponse execute(String id){
        try {
            log.info("Canceling transaction {}", id);

            var transactionResponse = getTransaction.execute(id);

            var transactionFrom = accountMapper.convertAccountResponseToAccount(getAccount.execute(transactionResponse.getFromAccount()));
            var transactionTo = accountMapper.convertAccountResponseToAccount(getAccount.execute(transactionResponse.getToAccount()));

            if (ChronoUnit.MINUTES.between(transactionResponse.getDate(), LocalDateTime.now()) > 5){
                return TransactionResponse.builder()
                        .id(transactionResponse.getId())
                        .message("Unable to cancel transaction")
                        .build();
            }else{
                log.info("Date from transaction works, canceling transaction");
                transactionResponse.setStatus(TransactionStatus.REVOKED);

                var transaction = transactionMapper.convertTransactionResponseToTransaction(transactionResponse);
                saveTransaction.execute(transaction);

                updateBalanceAccount(transactionTo, transactionFrom, transaction.getAmount());

                sendNotification.execute(transaction);

                return TransactionResponse.builder()
                        .id(transaction.getId())
                        .message("Transaction was successful canceled")
                        .build();
            }
        }catch (Exception e){
            log.error("Error while trying to canceling transaction {} - {}", id, e.getMessage());
            return TransactionResponse.builder()
                    .message("Error while trying to canceling transaction")
                    .build();
        }
    }

    private void updateBalanceAccount(Account accountFrom, Account accountTo, BigDecimal amount){
        log.info("Setting new balance for accounts");
        var balanceAccountFrom = accountFrom.getBalance().subtract(amount);
        var balanceAccountTo = accountTo.getBalance().add(amount);
        accountFrom.setBalance(balanceAccountFrom);
        accountTo.setBalance(balanceAccountTo);
        saveAccount.execute(accountFrom);
        saveAccount.execute(accountTo);
    }
}
