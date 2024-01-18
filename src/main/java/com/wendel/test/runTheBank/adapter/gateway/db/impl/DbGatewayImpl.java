package com.wendel.test.runTheBank.adapter.gateway.db.impl;

import com.wendel.test.runTheBank.adapter.controller.database.mapper.AccountMapperDomainAndEntity;
import com.wendel.test.runTheBank.adapter.controller.database.mapper.RegisterMapperDomainAndEntity;
import com.wendel.test.runTheBank.adapter.controller.database.mapper.TransactionMapperDomainAndEntity;
import com.wendel.test.runTheBank.adapter.controller.database.repository.AccountRepository;
import com.wendel.test.runTheBank.adapter.controller.database.repository.RegisterRepository;
import com.wendel.test.runTheBank.adapter.controller.database.repository.TransactionRepository;
import com.wendel.test.runTheBank.adapter.gateway.db.DbGateway;
import com.wendel.test.runTheBank.domain.Account;
import com.wendel.test.runTheBank.domain.Register;
import com.wendel.test.runTheBank.domain.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DbGatewayImpl implements DbGateway {

    private final RegisterRepository registerRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final RegisterMapperDomainAndEntity registerMapperDomainAndEntity;
    private final AccountMapperDomainAndEntity accountMapperDomainAndEntity;
    private final TransactionMapperDomainAndEntity transactionMapperDomainAndEntity;

    public DbGatewayImpl(RegisterRepository registerRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, RegisterMapperDomainAndEntity registerMapperDomainAndEntity, AccountMapperDomainAndEntity accountMapperDomainAndEntity, TransactionMapperDomainAndEntity transactionMapperDomainAndEntity) {
        this.registerRepository = registerRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.registerMapperDomainAndEntity = registerMapperDomainAndEntity;
        this.accountMapperDomainAndEntity = accountMapperDomainAndEntity;
        this.transactionMapperDomainAndEntity = transactionMapperDomainAndEntity;
    }

    @Override
    public void saveRegister(Register register) {
        try {
            log.info("Saving register {}", register.getId());
            registerRepository.save(registerMapperDomainAndEntity.convertRegisterToRegisterEntity(register));
        }catch (Exception e){
            log.error("Error while trying to save register with id {}", register.getId());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Register getRegister(String id) {
        try {
            return registerMapperDomainAndEntity.convertRegisterEntityToRegister(
                    registerRepository.findById(id)
                            .orElseThrow());
        }catch (Exception e){
            log.error("Error while trying to get register with id {}", id);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            accountRepository.save(accountMapperDomainAndEntity.convertAccountToAccountEntity(account));
        }catch (Exception e){
            log.error("Error while trying to save account with id {} - {}", account.getId(), e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public Account getAccount(String id) {
        try {
            return accountMapperDomainAndEntity.convertAcccountEntityToAccount(
                    accountRepository.findById(id)
                            .orElseThrow());
        }catch (Exception e){
            log.error("Error while trying to get account with id {}", id);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        try {
            transactionRepository.save(transactionMapperDomainAndEntity.convertTransactionToTransactionEntity(transaction));
        }catch (Exception e){
            log.error("Error while trying to save transaction with id {} - {}", transaction.getId(), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Transaction getTransaction(String id) {
        try {
            return transactionMapperDomainAndEntity.convertTransactionEntityToTransaction(
                    transactionRepository.findById(id)
                            .orElseThrow());
        }catch (Exception e){
            log.error("Error while trying to get transaction with id {}", id);
            throw new RuntimeException(e);
        }
    }
}
