package com.wendel.test.Fiserv.usecase.address;

import com.wendel.test.Fiserv.domain.Address;

public interface SaveAddress {
    void execute(Address address, String clientId);
}
