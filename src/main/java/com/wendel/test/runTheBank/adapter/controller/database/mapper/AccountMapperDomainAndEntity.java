package com.wendel.test.runTheBank.adapter.controller.database.mapper;

import com.wendel.test.runTheBank.adapter.controller.database.entity.AccountEntity;
import com.wendel.test.runTheBank.adapter.controller.database.entity.RegisterEntity;
import com.wendel.test.runTheBank.domain.Account;
import com.wendel.test.runTheBank.domain.Register;
import com.wendel.test.runTheBank.domain.enuns.AccountStatus;
import com.wendel.test.runTheBank.usecase.cipher.DecryptRequest;
import com.wendel.test.runTheBank.usecase.cipher.EncryptRequest;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AccountMapperDomainAndEntity {
    private final EncryptRequest encryptRequest;
    private final DecryptRequest decryptRequest;

    public AccountMapperDomainAndEntity(EncryptRequest encryptRequest, DecryptRequest decryptRequest) {
        this.encryptRequest = encryptRequest;
        this.decryptRequest = decryptRequest;
    }

    public AccountEntity convertAccountToAccountEntity(Account account) {
        return AccountEntity.builder()
                .id(account.getId())
                .agency(encryptRequest.execute(account.getAgency()))
                .balance(account.getBalance())
                .status(account.getAccountStatus().name())
                .accountNumber(account.getAccountNumber())
                .registerEntity(RegisterEntity.builder().id(account.getRegisterId()).build())
                .build();
    }
    public Account convertAcccountEntityToAccount(AccountEntity accountEntity) {
        return Account.builder()
                .id(accountEntity.getId())
                .agency(decryptRequest.execute(accountEntity.getAgency()))
                .balance(accountEntity.getBalance())
                .accountNumber(accountEntity.getAccountNumber())
                .accountStatus(AccountStatus.getAccountStatusByString(accountEntity.getStatus()))
                .registerId(accountEntity.getRegisterEntity().getId())
                .build();
    }

}
