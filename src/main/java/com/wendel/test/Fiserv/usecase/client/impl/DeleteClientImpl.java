package com.wendel.test.Fiserv.usecase.client.impl;

import com.wendel.test.Fiserv.adapter.gateway.db.DbGateway;
import com.wendel.test.Fiserv.domain.validator.ApiException;
import com.wendel.test.Fiserv.domain.validator.ExceptionMessage;
import com.wendel.test.Fiserv.usecase.cipher.EncryptRequest;
import com.wendel.test.Fiserv.usecase.client.DeleteClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteClientImpl implements DeleteClient {
    private final DbGateway dbGateway;
    private final EncryptRequest encryptRequest;

    public DeleteClientImpl(DbGateway dbGateway, EncryptRequest encryptRequest) {
        this.dbGateway = dbGateway;
        this.encryptRequest = encryptRequest;
    }

    @Override
    public void execute(String cpf){
        try{
            log.info("Deleting client {}", cpf);
            dbGateway.deleteClient(encryptRequest.execute(cpf));
        }catch (Exception e){
            log.error("Error while deleting client {} - {}", cpf, e.getMessage());
            throw new ApiException(ExceptionMessage.valueOf("Error while trying to delete " + cpf));
        }
    }
}
