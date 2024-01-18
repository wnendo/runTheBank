package com.wendel.test.runTheBank.adapter.controller.mapper;

import com.wendel.test.runTheBank.adapter.controller.request.AccountRequest;
import com.wendel.test.runTheBank.adapter.controller.response.AccountResponse;
import com.wendel.test.runTheBank.domain.Account;
import com.wendel.test.runTheBank.domain.enuns.AccountStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

@Component
public class AccountMapper {

    public Account convertAccountRequestToAccount(AccountRequest accountRequest) {

        return Account.builder()
                .id(UUID.randomUUID().toString())
                .registerId(accountRequest.getRegisterId())
                .accountNumber(String.valueOf(new Random().nextInt(9999)))
                .agency(accountRequest.getAgency())
                .balance(accountRequest.getBalance()==null ? BigDecimal.valueOf(new Random().nextInt(200)) : accountRequest.getBalance())
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }
    public AccountResponse convertAccountToAccountResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .status(account.getAccountStatus())
                .accountNumber(account.getAccountNumber())
                .agency(account.getAgency())
                .balance(account.getBalance())
                .registerId(account.getRegisterId())
                .build();
    }
    public Account convertAccountResponseToAccount(AccountResponse accountResponse) {
        return Account.builder()
                .id(accountResponse.getId())
                .accountStatus(accountResponse.getStatus())
                .registerId(accountResponse.getRegisterId())
                .accountNumber(accountResponse.getAccountNumber())
                .agency(accountResponse.getAgency())
                .balance(accountResponse.getBalance())
                .build();
    }

}
