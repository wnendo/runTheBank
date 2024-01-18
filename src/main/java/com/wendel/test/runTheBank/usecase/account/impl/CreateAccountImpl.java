package com.wendel.test.runTheBank.usecase.account.impl;

import com.wendel.test.runTheBank.adapter.controller.mapper.AccountMapper;
import com.wendel.test.runTheBank.adapter.controller.request.AccountRequest;
import com.wendel.test.runTheBank.adapter.controller.response.AccountResponse;
import com.wendel.test.runTheBank.domain.enuns.AccountStatus;
import com.wendel.test.runTheBank.usecase.account.CreateAccount;
import com.wendel.test.runTheBank.usecase.account.SaveAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateAccountImpl implements CreateAccount {
    private final SaveAccount saveAccount;
    private final AccountMapper accountMapper;

    public CreateAccountImpl(SaveAccount saveAccount, AccountMapper accountMapper) {
        this.saveAccount = saveAccount;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountResponse execute(AccountRequest accountRequest) {
        try {
            var account = accountMapper.convertAccountRequestToAccount(accountRequest);
            log.info("Creating account with id {}", account.getId());
            saveAccount.execute(account);
            log.info("Account created successfully");
            return AccountResponse.builder()
                    .id(account.getId())
                    .status(AccountStatus.ACTIVE)
                    .build();
        } catch (Exception e) {
            log.error("Error while trying to CREATE account {}", e.getMessage());
            return AccountResponse.builder()
                    .message("[Error] while trying to CREATE account")
                    .build();
        }
    }
}
