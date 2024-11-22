package com.wendel.test.Fiserv.usecase.client.impl;

import com.wendel.test.Fiserv.adapter.gateway.db.DbGateway;
import com.wendel.test.Fiserv.domain.Client;
import com.wendel.test.Fiserv.domain.validator.ApiException;
import com.wendel.test.Fiserv.domain.validator.ExceptionMessage;
import com.wendel.test.Fiserv.usecase.client.SaveClient;
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
            log.info("Saving client");
            dbGateway.saveClient(client);
        }catch (Exception e){
            log.error("Error while getting client with id {} - {}", client.getId(), e.getMessage());
            throw new ApiException(ExceptionMessage.valueOf("Error while trying to save client"));
        }
    }
}
