package com.wendel.test.runTheBank.usecase.register.impl;

import com.wendel.test.runTheBank.adapter.controller.mapper.RegisterMapper;
import com.wendel.test.runTheBank.adapter.controller.response.RegisterResponse;
import com.wendel.test.runTheBank.adapter.gateway.db.DbGateway;
import com.wendel.test.runTheBank.domain.Register;
import com.wendel.test.runTheBank.domain.validator.ApiException;
import com.wendel.test.runTheBank.domain.validator.ExceptionMessage;
import com.wendel.test.runTheBank.usecase.register.GetRegister;
import com.wendel.test.runTheBank.usecase.register.SaveRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SaveRegisterImpl implements SaveRegister {
    private final DbGateway dbGateway;

    public SaveRegisterImpl(DbGateway dbGateway) {
        this.dbGateway = dbGateway;
    }

    @Override
    public void execute(Register register){
        try{
            log.info("Saving register {}", register.getId());
            dbGateway.saveRegister(register);
        }catch (Exception e){
            log.error("Error while saving register with id {} - {}", register.getId(), e.getMessage());
            throw new ApiException(ExceptionMessage.valueOf("Error while saving register"));
        }
    }
}
