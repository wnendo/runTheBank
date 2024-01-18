package com.wendel.test.runTheBank.usecase.account.impl;

import com.wendel.test.runTheBank.adapter.controller.mapper.AccountMapper;
import com.wendel.test.runTheBank.adapter.controller.mapper.RegisterMapper;
import com.wendel.test.runTheBank.adapter.controller.response.AccountResponse;
import com.wendel.test.runTheBank.adapter.controller.response.RegisterResponse;
import com.wendel.test.runTheBank.adapter.gateway.db.DbGateway;
import com.wendel.test.runTheBank.domain.Account;
import com.wendel.test.runTheBank.domain.enuns.AccountStatus;
import com.wendel.test.runTheBank.usecase.account.GetAccount;
import com.wendel.test.runTheBank.usecase.register.GetRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetAccountImpl implements GetAccount {
    private final DbGateway dbGateway;
    private final AccountMapper accountMapper;

    public GetAccountImpl(DbGateway dbGateway, AccountMapper accountMapper) {
        this.dbGateway = dbGateway;
        this.accountMapper = accountMapper;
    }

    @Override
    public Account execute(String id){
        try{
            log.info("Searching for account {}", id);
            return dbGateway.getAccount(id);
        }catch (Exception e){
            log.error("Error while getting account with id {} - {}", id, e.getMessage());
            return Account.builder()
                    .id(id)
                    .build();
        }
    }
}
