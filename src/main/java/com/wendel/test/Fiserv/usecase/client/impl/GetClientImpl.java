package com.wendel.test.Fiserv.usecase.client.impl;

import com.wendel.test.Fiserv.adapter.controller.mapper.ClientMapper;
import com.wendel.test.Fiserv.adapter.controller.response.ClientResponse;
import com.wendel.test.Fiserv.adapter.gateway.db.DbGateway;
import com.wendel.test.Fiserv.usecase.cipher.EncryptRequest;
import com.wendel.test.Fiserv.usecase.client.GetClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetClientImpl implements GetClient {
    private final DbGateway dbGateway;
    private final ClientMapper clientMapper;
    private final EncryptRequest encryptRequest;

    public GetClientImpl(DbGateway dbGateway, ClientMapper clientMapper, EncryptRequest encryptRequest) {
        this.dbGateway = dbGateway;
        this.clientMapper = clientMapper;
        this.encryptRequest = encryptRequest;
    }

    @Override
    public ClientResponse execute(String cpf){
        try{
            log.info("Searching for client {}", cpf);
            return clientMapper.convertClientToClientResponse(dbGateway.getClient(encryptRequest.execute(cpf)));
        }catch (Exception e){
            log.error("Error while getting client with cpf {} - {}", cpf, e.getMessage());
            return ClientResponse.builder()
                    .cpf(cpf)
                    .message("Error while searching for client")
                    .build();
        }
    }
}
