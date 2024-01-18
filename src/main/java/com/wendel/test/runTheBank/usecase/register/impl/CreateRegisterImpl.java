package com.wendel.test.runTheBank.usecase.register.impl;

import com.wendel.test.runTheBank.adapter.controller.mapper.RegisterMapper;
import com.wendel.test.runTheBank.adapter.controller.request.RegisterRequest;
import com.wendel.test.runTheBank.adapter.controller.response.RegisterResponse;
import com.wendel.test.runTheBank.adapter.gateway.db.DbGateway;
import com.wendel.test.runTheBank.domain.enuns.RegisterStatus;
import com.wendel.test.runTheBank.usecase.cipher.EncryptRequest;
import com.wendel.test.runTheBank.usecase.register.CreateRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateRegisterImpl implements CreateRegister {
    private final DbGateway dbGateway;
    private final RegisterMapper registerMapper;

    public CreateRegisterImpl(DbGateway dbGateway, RegisterMapper registerMapper) {
        this.dbGateway = dbGateway;
        this.registerMapper = registerMapper;
    }

    @Override
    public RegisterResponse execute(RegisterRequest registerRequest){
        try {
            log.info("Creating a new register for cpf/cnpj {}", registerRequest.getCpfOrCnpj());
            var register = registerMapper.convertRegisterRequestToRegister(registerRequest);
            dbGateway.saveRegister(register);
            return RegisterResponse.builder()
                    .id(register.getId())
                    .registerStatus(RegisterStatus.CREATED)
                    .build();
        }catch (Exception e){
            log.error("Error while trying to registering client {}", e.getMessage());
            return RegisterResponse.builder()
                    .registerStatus(RegisterStatus.CREATED)
                    .message("Error while registering client")
                    .build();
        }
    }
}
