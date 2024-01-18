package com.wendel.test.runTheBank.usecase.transaction.impl;

import com.wendel.test.runTheBank.adapter.controller.mapper.AccountMapper;
import com.wendel.test.runTheBank.adapter.controller.mapper.TransactionMapper;
import com.wendel.test.runTheBank.adapter.controller.request.TransactionRequest;
import com.wendel.test.runTheBank.adapter.controller.response.TransactionResponse;
import com.wendel.test.runTheBank.domain.Account;
import com.wendel.test.runTheBank.domain.enuns.AccountStatus;
import com.wendel.test.runTheBank.domain.enuns.TransactionStatus;
import com.wendel.test.runTheBank.usecase.account.GetAccount;
import com.wendel.test.runTheBank.usecase.account.SaveAccount;
import com.wendel.test.runTheBank.usecase.notification.SendNotification;
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
    private final AccountMapper accountMapper;
    private final GetAccount getAccount;
    private final SaveAccount saveAccount;
    private final SendNotification sendNotification;

    public CreateTransactionImpl(SaveTransaction saveTransaction, TransactionMapper transactionMapper, AccountMapper accountMapper, GetAccount getAccount, SaveAccount saveAccount, SendNotification sendNotification) {
        this.saveTransaction = saveTransaction;
        this.transactionMapper = transactionMapper;
        this.accountMapper = accountMapper;
        this.getAccount = getAccount;
        this.saveAccount = saveAccount;
        this.sendNotification = sendNotification;
    }

    @Override
    public TransactionResponse execute(TransactionRequest transactionRequest){
        try {
            log.info("Creating transaction from account {} to account {}", transactionRequest.getFromAccount(), transactionRequest.getToAccount());
            var transaction = transactionMapper.convertTransactionRequestToTransaction(transactionRequest);

            var transactionFrom = accountMapper.convertAccountResponseToAccount(getAccount.execute(transaction.getFromAccount()));
            var transactionTo = accountMapper.convertAccountResponseToAccount(getAccount.execute(transaction.getToAccount()));

            if (transactionFrom.getAccountStatus() != AccountStatus.ACTIVE || transactionFrom.getBalance().compareTo(transaction.getAmount()) < 0 ){
                return TransactionResponse.builder()
                        .id(transaction.getId())
                        .message("Unable to make transaction")
                        .build();
            }else{
                log.info("Accounts found and there are balance to make transaction!");
                transaction.setStatus(TransactionStatus.EXECUTED);
                saveTransaction.execute(transaction);

                updateBalanceAccount(transactionFrom, transactionTo, transaction.getAmount());

                sendNotification.execute(transaction);

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

    private void updateBalanceAccount(Account accountFrom, Account accountTo, BigDecimal amount){
        log.info("Setting new balance for accounts");
        var balanceAccountFrom = accountFrom.getBalance().subtract(amount);
        var balanceAccountTo = accountTo.getBalance().add(amount);
        accountFrom.setBalance(balanceAccountFrom);
        accountTo.setBalance(balanceAccountTo);
        saveAccount.execute(accountFrom);
        saveAccount.execute(accountTo);
    }
    private void updateSecondBalanceAccount(Account account, BigDecimal amount){
        var newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        saveAccount.execute(account);
    }
}
