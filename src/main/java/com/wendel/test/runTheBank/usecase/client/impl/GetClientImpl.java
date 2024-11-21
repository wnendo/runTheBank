package com.wendel.test.runTheBank.usecase.client.impl;

import com.wendel.test.runTheBank.adapter.controller.mapper.ClientMapper;
import com.wendel.test.runTheBank.adapter.controller.response.ClientResponse;
import com.wendel.test.runTheBank.adapter.gateway.db.DbGateway;
import com.wendel.test.runTheBank.usecase.client.GetClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetClientImpl implements GetClient {
    private final DbGateway dbGateway;
    private final ClientMapper clientMapper;

    public GetClientImpl(DbGateway dbGateway, ClientMapper clientMapper) {
        this.dbGateway = dbGateway;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientResponse execute(String id){
        try{
            log.info("Searching for account {}", id);
            return clientMapper.convertClientToClientResponse(dbGateway.getClient(id));
        }catch (Exception e){
            log.error("Error while getting client with id {} - {}", id, e.getMessage());
            return ClientResponse.builder()
                    .id(id)
                    .cpf("Error while searching for client")
                    .build();
        }
    }
}
