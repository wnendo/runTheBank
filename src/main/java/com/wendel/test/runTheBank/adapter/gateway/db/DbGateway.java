package com.wendel.test.runTheBank.adapter.gateway.db;

import com.wendel.test.runTheBank.domain.Client;
import com.wendel.test.runTheBank.domain.Address;

public interface DbGateway {

    void saveAddress(Address address, String clientId);
    Address getAddress(String id);
    void saveClient(Client client);
    void deleteAddress(String id);
    Client getClient(String id);
    void deleteClient(String id);
}
