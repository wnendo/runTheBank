package com.wendel.test.runTheBank.usecase.address.impl;

import com.wendel.test.runTheBank.adapter.gateway.db.DbGateway;
import com.wendel.test.runTheBank.domain.Address;
import com.wendel.test.runTheBank.domain.validator.ApiException;
import com.wendel.test.runTheBank.domain.validator.ExceptionMessage;
import com.wendel.test.runTheBank.usecase.address.SaveAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SaveAddressImpl implements SaveAddress {
    private final DbGateway dbGateway;

    public SaveAddressImpl(DbGateway dbGateway) {
        this.dbGateway = dbGateway;
    }

    @Override
    public void execute(Address address){
        try{
            log.info("Saving address {}", address.getId());
            dbGateway.saveAddress(address);
        }catch (Exception e){
            log.error("Error while saving address with id {} - {}", address.getId(), e.getMessage());
            throw new ApiException(ExceptionMessage.valueOf("Error while saving address"));
        }
    }
}
