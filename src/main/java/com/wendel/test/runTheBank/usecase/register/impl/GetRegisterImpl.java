package com.wendel.test.runTheBank.usecase.register.impl;

import com.wendel.test.runTheBank.adapter.controller.mapper.RegisterMapper;
import com.wendel.test.runTheBank.adapter.controller.request.RegisterRequest;
import com.wendel.test.runTheBank.adapter.controller.response.RegisterResponse;
import com.wendel.test.runTheBank.adapter.gateway.db.DbGateway;
import com.wendel.test.runTheBank.domain.Register;
import com.wendel.test.runTheBank.usecase.cipher.DecryptRequest;
import com.wendel.test.runTheBank.usecase.cipher.EncryptRequest;
import com.wendel.test.runTheBank.usecase.register.CreateRegister;
import com.wendel.test.runTheBank.usecase.register.GetRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetRegisterImpl implements GetRegister {
    private final DbGateway dbGateway;
    private final RegisterMapper registerMapper;

    public GetRegisterImpl(DbGateway dbGateway, RegisterMapper registerMapper) {
        this.dbGateway = dbGateway;
        this.registerMapper = registerMapper;
    }

    @Override
    public RegisterResponse execute(String id){
        try{
            return registerMapper.convertRegisterDataToRegister(dbGateway.getRegister(id));
        }catch (Exception e){
            log.error("Error while getting register with id {} - {}", id, e.getMessage());
            return RegisterResponse.builder().message("Error while registering client").build();
        }
    }
}
