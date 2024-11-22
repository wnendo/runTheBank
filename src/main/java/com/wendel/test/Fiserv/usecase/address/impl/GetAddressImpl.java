package com.wendel.test.Fiserv.usecase.address.impl;

import com.wendel.test.Fiserv.adapter.controller.mapper.AddressMapper;
import com.wendel.test.Fiserv.adapter.controller.response.AddressResponse;
import com.wendel.test.Fiserv.adapter.gateway.db.DbGateway;
import com.wendel.test.Fiserv.usecase.address.GetAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetAddressImpl implements GetAddress {
    private final DbGateway dbGateway;
    private final AddressMapper addressMapper;

    public GetAddressImpl(DbGateway dbGateway, AddressMapper addressMapper) {
        this.dbGateway = dbGateway;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressResponse execute(String id){
        try{
            return addressMapper.convertAddressDataToAddress(dbGateway.getAddress(id));
        }catch (Exception e){
            log.error("Error while getting address with id {} - {}", id, e.getMessage());
            return AddressResponse.builder().message("Error while getting address").build();
        }
    }
}
