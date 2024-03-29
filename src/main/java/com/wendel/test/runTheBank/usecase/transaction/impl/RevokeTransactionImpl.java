package com.wendel.test.runTheBank.usecase.transaction.impl;

import com.wendel.test.runTheBank.adapter.controller.mapper.AccountMapper;
import com.wendel.test.runTheBank.adapter.controller.mapper.TransactionMapper;
import com.wendel.test.runTheBank.adapter.controller.response.TransactionResponse;
import com.wendel.test.runTheBank.domain.Account;
import com.wendel.test.runTheBank.domain.enuns.TransactionStatus;
import com.wendel.test.runTheBank.usecase.account.GetAccount;
import com.wendel.test.runTheBank.usecase.account.SaveAccount;
import com.wendel.test.runTheBank.usecase.notification.SendNotification;
import com.wendel.test.runTheBank.usecase.transaction.GetTransaction;
import com.wendel.test.runTheBank.usecase.transaction.RevokeTransaction;
import com.wendel.test.runTheBank.usecase.transaction.SaveTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
public class RevokeTransactionImpl implements RevokeTransaction {
    private final SaveTransaction saveTransaction;
    private final TransactionMapper transactionMapper;
    private final GetAccount getAccount;
    private final AccountMapper accountMapper;
    private final GetTransaction getTransaction;
    private final SaveAccount saveAccount;
    private final SendNotification sendNotification;

    public RevokeTransactionImpl(SaveTransaction saveTransaction, TransactionMapper transactionMapper, GetAccount getAccount, AccountMapper accountMapper, GetTransaction getTransaction, SaveAccount saveAccount, SendNotification sendNotification) {
        this.saveTransaction = saveTransaction;
        this.transactionMapper = transactionMapper;
        this.getAccount = getAccount;
        this.accountMapper = accountMapper;
        this.getTransaction = getTransaction;
        this.saveAccount = saveAccount;
        this.sendNotification = sendNotification;
    }

    @Override
    public TransactionResponse execute(String id){
        try {
            log.info("Revoking transaction {}", id);

            var transactionResponse = getTransaction.execute(id);

            var transactionFrom = accountMapper.convertAccountResponseToAccount(getAccount.execute(transactionResponse.getFromAccount()));
            var transactionTo = accountMapper.convertAccountResponseToAccount(getAccount.execute(transactionResponse.getToAccount()));

            if (ChronoUnit.MINUTES.between(transactionResponse.getDate(), LocalDateTime.now()) > 10){
                return TransactionResponse.builder()
                        .id(transactionResponse.getId())
                        .message("Unable to revoke transaction")
                        .build();
            }else{
                log.info("Date from transaction works, revoking transaction");
                transactionResponse.setStatus(TransactionStatus.REVOKED);

                var transaction = transactionMapper.convertTransactionResponseToTransaction(transactionResponse);
                saveTransaction.execute(transaction);

                updateBalanceAccount(transactionTo, transactionFrom, transaction.getAmount());

                sendNotification.execute(transaction);

                log.info("Transaction revoked successfully");

                return TransactionResponse.builder()
                        .id(transaction.getId())
                        .message("Transaction was successful revoked")
                        .build();
            }
        }catch (Exception e){
            log.error("Error while trying to revoking transaction {} - {}", id, e.getMessage());
            return TransactionResponse.builder()
                    .message("Error while trying to revoking transaction")
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
