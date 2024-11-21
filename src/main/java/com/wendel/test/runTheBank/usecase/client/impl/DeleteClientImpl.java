package com.wendel.test.runTheBank.usecase.client.impl;

import com.wendel.test.runTheBank.adapter.controller.mapper.ClientMapper;
import com.wendel.test.runTheBank.adapter.gateway.db.DbGateway;
import com.wendel.test.runTheBank.domain.validator.ApiException;
import com.wendel.test.runTheBank.domain.validator.ExceptionMessage;
import com.wendel.test.runTheBank.usecase.client.DeleteClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteClientImpl implements DeleteClient {
    private final DbGateway dbGateway;
    private final ClientMapper clientMapper;

    public DeleteClientImpl(DbGateway dbGateway, ClientMapper clientMapper) {
        this.dbGateway = dbGateway;
        this.clientMapper = clientMapper;
    }

    @Override
    public void execute(String id){
        try{
            log.info("Deleting client {}", id);
            dbGateway.deleteClient(id);
        }catch (Exception e){
            log.error("Error while getting account with id {} - {}", id, e.getMessage());
            throw new ApiException(ExceptionMessage.valueOf("Error while trying to delete " + id));
        }
    }
}
