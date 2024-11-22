package com.wendel.test.Fiserv.usecase.address.impl;

import com.wendel.test.Fiserv.adapter.gateway.db.DbGateway;
import com.wendel.test.Fiserv.domain.Address;
import com.wendel.test.Fiserv.domain.validator.ApiException;
import com.wendel.test.Fiserv.domain.validator.ExceptionMessage;
import com.wendel.test.Fiserv.usecase.address.SaveAddress;
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
    public void execute(Address address, String clientId){
        try{
            log.info("Saving address {}", address.getId());
            dbGateway.saveAddress(address, clientId);
        }catch (Exception e){
            log.error("Error while saving address with id {} - {}", address.getId(), e.getMessage());
            throw new ApiException(ExceptionMessage.valueOf("Error while saving address"));
        }
    }
}
