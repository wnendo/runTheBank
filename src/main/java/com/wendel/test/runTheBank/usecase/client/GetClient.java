package com.wendel.test.runTheBank.usecase.client;

import com.wendel.test.runTheBank.adapter.controller.response.ClientResponse;

public interface GetClient {
    ClientResponse execute(String id);
}
