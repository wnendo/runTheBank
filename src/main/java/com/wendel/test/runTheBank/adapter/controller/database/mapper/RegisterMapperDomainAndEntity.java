package com.wendel.test.runTheBank.adapter.controller.database.mapper;

import com.wendel.test.runTheBank.adapter.controller.database.entity.AccountEntity;
import com.wendel.test.runTheBank.adapter.controller.database.entity.RegisterEntity;
import com.wendel.test.runTheBank.adapter.controller.request.RegisterRequest;
import com.wendel.test.runTheBank.domain.Account;
import com.wendel.test.runTheBank.domain.Register;
import com.wendel.test.runTheBank.domain.enuns.AccountStatus;
import com.wendel.test.runTheBank.usecase.cipher.DecryptRequest;
import com.wendel.test.runTheBank.usecase.cipher.EncryptRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RegisterMapperDomainAndEntity {
    private final EncryptRequest encryptRequest;
    private final DecryptRequest decryptRequest;

    public RegisterMapperDomainAndEntity(EncryptRequest encryptRequest, DecryptRequest decryptRequest) {
        this.encryptRequest = encryptRequest;
        this.decryptRequest = decryptRequest;
    }

    public RegisterEntity convertRegisterToRegisterEntity(Register register) {
        return RegisterEntity.builder()
                .id(register.getId())
                .name(register.getName())
                .cpfOrCnpj(encryptRequest.execute(register.getCpfOrCnpj()))
                .address(register.getAddress())
                .password(encryptRequest.execute(register.getPassword()))
                .build();
    }
    public Register convertRegisterEntityToRegister(RegisterEntity registerEntity) {
        return Register.builder()
                .id(registerEntity.getId())
                .name(registerEntity.getName())
                .cpfOrCnpj(decryptRequest.execute(registerEntity.getCpfOrCnpj()))
                .account(convertAccountListEntityToAccountList(registerEntity.getAccountEntity()))
                .address(registerEntity.getAddress())
                .password(decryptRequest.execute(registerEntity.getPassword()))
                .build();
    }

    public List<Account> convertAccountListEntityToAccountList(List<AccountEntity> accountEntities){
        return accountEntities.stream().map(accountEntity ->
                Account.builder()
                        .id(accountEntity.getId())
                        .registerId(accountEntity.getRegisterEntity().getId())
                        .accountStatus(AccountStatus.getAccountStatusByString(accountEntity.getStatus()))
                        .balance(accountEntity.getBalance())
                        .agency(accountEntity.getAgency())
                        .build()
        ).collect(Collectors.toList());
    }

}
