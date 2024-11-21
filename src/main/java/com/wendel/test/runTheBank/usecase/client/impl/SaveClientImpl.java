package com.wendel.test.runTheBank.usecase.client.impl;

import com.wendel.test.runTheBank.adapter.gateway.db.DbGateway;
import com.wendel.test.runTheBank.domain.Client;
import com.wendel.test.runTheBank.domain.validator.ApiException;
import com.wendel.test.runTheBank.domain.validator.ExceptionMessage;
import com.wendel.test.runTheBank.usecase.client.SaveClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SaveClientImpl implements SaveClient {
    private final DbGateway dbGateway;

    public SaveClientImpl(DbGateway dbGateway) {
        this.dbGateway = dbGateway;
    }

    @Override
    public void execute(Client client){
        try{
            log.info("Saving client {}", client.getId());
            dbGateway.saveClient(client);
        }catch (Exception e){
            log.error("Error while getting client with id {} - {}", client.getId(), e.getMessage());
            throw new ApiException(ExceptionMessage.valueOf("Error while trying to save client"));
        }
    }
}
