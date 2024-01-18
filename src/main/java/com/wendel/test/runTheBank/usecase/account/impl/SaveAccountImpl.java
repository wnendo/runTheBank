package com.wendel.test.runTheBank.usecase.account.impl;

import com.wendel.test.runTheBank.adapter.controller.mapper.AccountMapper;
import com.wendel.test.runTheBank.adapter.controller.response.AccountResponse;
import com.wendel.test.runTheBank.adapter.gateway.db.DbGateway;
import com.wendel.test.runTheBank.domain.Account;
import com.wendel.test.runTheBank.domain.validator.ApiException;
import com.wendel.test.runTheBank.domain.validator.ExceptionMessage;
import com.wendel.test.runTheBank.usecase.account.GetAccount;
import com.wendel.test.runTheBank.usecase.account.SaveAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SaveAccountImpl implements SaveAccount {
    private final DbGateway dbGateway;

    public SaveAccountImpl(DbGateway dbGateway) {
        this.dbGateway = dbGateway;
    }

    @Override
    public void execute(Account account){
        try{
            log.info("Saving account {}", account.getId());
            dbGateway.saveAccount(account);
        }catch (Exception e){
            log.error("Error while getting account with id {} - {}", account.getId(), e.getMessage());
            throw new ApiException(ExceptionMessage.valueOf("Error while trying to save account"));
        }
    }
}
