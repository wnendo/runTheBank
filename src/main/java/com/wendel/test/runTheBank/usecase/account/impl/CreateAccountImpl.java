package com.wendel.test.runTheBank.usecase.account.impl;

import com.wendel.test.runTheBank.adapter.controller.mapper.AccountMapper;
import com.wendel.test.runTheBank.adapter.controller.mapper.RegisterMapper;
import com.wendel.test.runTheBank.adapter.controller.request.AccountRequest;
import com.wendel.test.runTheBank.adapter.controller.request.RegisterRequest;
import com.wendel.test.runTheBank.adapter.controller.response.AccountResponse;
import com.wendel.test.runTheBank.adapter.controller.response.RegisterResponse;
import com.wendel.test.runTheBank.adapter.gateway.db.DbGateway;
import com.wendel.test.runTheBank.domain.enuns.AccountStatus;
import com.wendel.test.runTheBank.domain.enuns.RegisterStatus;
import com.wendel.test.runTheBank.usecase.account.CreateAccount;
import com.wendel.test.runTheBank.usecase.cipher.EncryptRequest;
import com.wendel.test.runTheBank.usecase.register.CreateRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateAccountImpl implements CreateAccount {
    private final DbGateway dbGateway;
    private final AccountMapper accountMapper;

    public CreateAccountImpl(DbGateway dbGateway, AccountMapper accountMapper) {
        this.dbGateway = dbGateway;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountResponse execute(AccountRequest accountRequest) {
        try {
            var account = accountMapper.convertAccountRequestToAccount(accountRequest);
            log.info("Creating account with id {}", account.getId());
            dbGateway.saveAccount(account);
            return AccountResponse.builder()
                    .id(account.getId())
                    .accountStatus(AccountStatus.ACTIVE)
                    .build();
        } catch (Exception e) {
            log.error("Error while trying to CREATE account {}", e.getMessage());
            return AccountResponse.builder()
                    .message("[Error] while trying to CREATE account")
                    .build();
        }
    }
}
